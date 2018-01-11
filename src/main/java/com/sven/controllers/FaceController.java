package com.sven.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.io.ByteStreams;
import com.megvii.cloud.http.Response;
import com.sven.model.UserId;
import com.sven.services.StorageService;
import com.sven.services.faceplusplus.FaceService;
import com.sven.services.faceplusplus.model.FaceAttachUserIdResponse;
import com.sven.services.faceplusplus.model.FaceDetectResponse;
import com.sven.services.faceplusplus.model.FaceGetDetailResponse;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/face")
@Api(value = "face api", tags = { "face" })
public class FaceController
{

    @Autowired
    private StorageService storageService;

    @Autowired
    private FaceService faceService;

    @PostMapping
    public FaceDetectResponse recognize(@RequestParam final String filename)
            throws IOException, Exception
    {

        Resource file = storageService.loadAsResource(filename);

        return faceService.detect(ByteStreams.toByteArray(file.getInputStream()));
    }

    @PostMapping("/user")
    public FaceAttachUserIdResponse recognize(@RequestParam final String faceToken,
            @RequestParam final String userName) throws IOException, Exception
    {

        UserId userId = new UserId(userName);
        return faceService.attachUserIdToFacialId(faceToken, userId);
    }
    
    @GetMapping("/{faceToken}")
    public FaceGetDetailResponse getDetail(@PathVariable final String faceToken) throws IOException, Exception
    {
        return faceService.getFacialIdDetail(faceToken);
    }

    @PostMapping("/search")
    public String search(@RequestParam final String outerId,
            @RequestParam final String filename) throws IOException, Exception
    {

        Resource file = storageService.loadAsResource(filename);
        Response response = faceService.search(
                ByteStreams.toByteArray(file.getInputStream()), outerId);
        return new String(response.getContent());
    }

}
