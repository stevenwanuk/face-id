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
import com.sven.services.StorageService;
import com.sven.services.faceplusplus.FaceService;

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
    public String recognize(@RequestParam final String filename) throws IOException, Exception {
        
        Resource file = storageService.loadAsResource(filename);
        
        Response response =  faceService.detect(ByteStreams.toByteArray(file.getInputStream()));
        return new String(response.getContent()); 
    }
    
    @PostMapping("/user")
    public String recognize(@RequestParam final String faceToken, @RequestParam final String userId) throws IOException, Exception {
        
        
        Response response =  faceService.faceSetUserId(faceToken, userId);
        return new String(response.getContent()); 
    }
    
    @PostMapping("/search")
    public String search(@RequestParam final String outerId, @RequestParam final String filename) throws IOException, Exception {
        
        Resource file = storageService.loadAsResource(filename);
        Response response =  faceService.search(ByteStreams.toByteArray(file.getInputStream()), outerId);
        return new String(response.getContent()); 
    }
    
}
