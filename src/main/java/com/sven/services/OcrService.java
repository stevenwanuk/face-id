package com.sven.services;

import java.awt.Rectangle;
import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.TesseractException;

@Service
public class OcrService {

	@Autowired
	private ITesseract instance;

	public String ocr(File imageFile, Rectangle rect) throws TesseractException {

		String str = instance.doOCR(imageFile, rect);
		return str;
	}
}
