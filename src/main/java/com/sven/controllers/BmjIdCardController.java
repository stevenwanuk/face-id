package com.sven.controllers;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.io.ByteStreams;
import com.sven.exceptions.BmaIdNotFoundException;
import com.sven.exceptions.FacialIdApplicationException;
import com.sven.exceptions.FacialIdNotFoundException;
import com.sven.model.BmaUserId;
import com.sven.model.FacialId;
import com.sven.services.OcrService;
import com.sven.services.StorageService;
import com.sven.services.faceplusplus.FaceService;
import com.sven.services.faceplusplus.model.Face;
import com.sven.services.faceplusplus.model.FaceAttachUserIdResponse;
import com.sven.services.faceplusplus.model.FaceDetectResponse;
import com.sven.services.faceplusplus.model.FaceSetAddFaceTokenResponse;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/bmjId")
@Api(value = "bmj id api", tags = { "bmj" })
public class BmjIdCardController
{

    @Value("${face.faceset.outerId}")
    private String bmaFacesetOuterId;

    @Autowired
    private StorageService storageService;

    @Autowired
    private FaceService faceService;

    @Autowired
    private OcrService ocrService;

    @PostMapping
    public FacialId recognize(@RequestParam final String filename)
            throws IOException, Exception
    {

        Resource resource = storageService.loadAsResource(filename);
        byte[] bytes = ByteStreams.toByteArray(resource.getInputStream());

        // detect face from image
        FaceDetectResponse faceDetectResponse = faceService.detect(bytes);

        if (faceDetectResponse.getFaces() == null
                || faceDetectResponse.getFaces().length == 0)
        {

            throw new FacialIdNotFoundException("No facial id detected from giving image");
        }

        if (faceDetectResponse.getFaces().length > 1)
        {

            throw new FacialIdNotFoundException(
                    "Multiple facial ids detected from giving image");
        }

        Face face = faceDetectResponse.getFaces()[0];
        String faceToken = face.getFaceToken();

        // ocr for bma number and user's name
        String ocrString = ocrService.ocr(resource.getFile(), null);

        String[] raw = ocrString.split("\n");

        String[] infos =
                Arrays.stream(raw).filter(s -> !StringUtils.isEmpty(s)).filter(
                        s -> s.trim().length() > 3).toArray(String[]::new);

        int bmaNumberIndex = infos.length - 1;
        for (int i = bmaNumberIndex; i >= 0; i--)
        {
            if (infos[bmaNumberIndex].matches("^[0-9]*$"))
            {
                bmaNumberIndex = i;
                break;
            }
        }

        if (bmaNumberIndex <= 0)
        {
            throw new BmaIdNotFoundException("Failt to detect bma id from giving image");
        }

        String bmaNumber = infos[bmaNumberIndex];
        String userName = infos[bmaNumberIndex - 1];

        BmaUserId bmaUserId = new BmaUserId(bmaNumber, userName);
        FacialId bmaFacialId = new FacialId(bmaUserId, faceToken);

        // attach userid to face image
        FaceAttachUserIdResponse faceAttachUserIdResponse =
                faceService.attachUserIdToFacialId(faceToken, bmaUserId);

        if (StringUtils.isEmpty(faceAttachUserIdResponse.getFaceToken()))
        {
            throw new FacialIdApplicationException(
                    "Failed to attach user's detail to detected facial id");
        }

        // put face image to faceSet
        FaceSetAddFaceTokenResponse faceSetAddFaceTokenResponse =
                faceService.addFaceByOuterId(faceToken, bmaFacesetOuterId);
        if (faceSetAddFaceTokenResponse.getFaceAdded() != 1)
        {
            throw new FacialIdApplicationException("Failed to add facial id to faceSet");
        }

        return bmaFacialId;
    };
}
