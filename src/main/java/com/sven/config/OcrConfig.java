package com.sven.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

@Configuration
public class OcrConfig {

	@Bean
	public ITesseract getTesseract() {
		ITesseract tessInst = new Tesseract();
		tessInst.setDatapath("/darwin/tessdata/");
		tessInst.setLanguage("eng");
		return tessInst;
	}
}
