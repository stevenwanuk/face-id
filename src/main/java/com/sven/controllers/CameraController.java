package com.sven.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.megvii.cloud.http.Response;
import com.sven.services.faceplusplus.FaceService;

@Controller
@RequestMapping("/camera")
public class CameraController {

	@Autowired
	private FaceService faceService;

	@GetMapping()
	public String get() {

		return "camera-index";
	}

	@PostMapping(value = "/saveCanvasImage")
	@ResponseBody
	public String saveCanvasImage(@RequestParam(value = "imageBase64", defaultValue = "") String imageBase64)
			throws Exception {
		System.out.println(imageBase64);
		// String imageBase64 = imageDataUrl.substring(10);

		Response resonse = faceService.search(imageBase64.substring("data:image/png;base64,".length()), "test");

		String json = new String(resonse.getContent());
		return json;
	}

}
