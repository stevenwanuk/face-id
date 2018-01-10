package com.sven.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.megvii.cloud.http.Response;
import com.sven.services.ImageService;
import com.sven.services.StorageService;
import com.sven.services.faceplusplus.FaceService;

@Controller
@RequestMapping("/camera")
public class CameraController {

	@Autowired
	private FaceService faceService;

	@Autowired
	private ImageService imageService;

	@Autowired
	private StorageService storageService;

	@GetMapping()
	public String get() {

		return "camera-index";
	}

	@PostMapping(value = "/saveCanvasImage")
	@ResponseBody
	public String saveCanvasImage(@RequestParam(value = "imageBase64", defaultValue = "") String data)
			throws Exception {

		String base64Image = imageService.getBase64Image(data);

		byte[] bytes = imageService.base64ToImageBytes(base64Image);
		storageService.store(bytes, "tempfile.png");

		Response resonse = faceService.search(base64Image, "test");

		String json = new String(resonse.getContent());
		return json;
	}

}
