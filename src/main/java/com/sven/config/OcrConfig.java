package com.sven.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

@Configuration
public class OcrConfig {
    
    @Value("${ocr.tesseract.data.path}")
    private String tessdataPath;
    
	@Bean
	public ITesseract getTesseract() throws IOException {
	    
		ITesseract tessInst = new Tesseract();
		tessInst.setDatapath(tessdataPath);
		tessInst.setLanguage("eng");
		return tessInst;
	}
}
