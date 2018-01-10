package com.sven.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.io.ByteStreams;
import com.megvii.cloud.http.Response;
import com.sven.services.OcrService;
import com.sven.services.StorageService;
import com.sven.services.faceplusplus.FaceService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/bmjId")
@Api(value = "bmj id api", tags = { "bmj" })
public class BmjIdCardController {

	@Autowired
	private StorageService storageService;

	@Autowired
	private FaceService faceService;

	@Autowired
	private OcrService ocrService;

	@PostMapping
	public String recognize(@RequestParam final String filename) throws IOException, Exception {

		Resource resource = storageService.loadAsResource(filename);
		byte[] bytes = ByteStreams.toByteArray(resource.getInputStream());

		System.out.println(ocrService.ocr(resource.getFile(), null));

		Response response = faceService.detect(bytes);

		return new String(response.getContent());
	}

}
