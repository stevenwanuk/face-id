package com.sven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.sven.config.StorageProperties;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class FaceRecognitionApplication {

	public static void main(final String[] args) {
		SpringApplication.run(FaceRecognitionApplication.class, args);
	}
}
