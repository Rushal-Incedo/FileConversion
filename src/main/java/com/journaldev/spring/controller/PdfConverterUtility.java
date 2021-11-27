package com.journaldev.spring.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import fr.opensagres.poi.xwpf.converter.core.XWPFConverterException;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;

public class PdfConverterUtility extends PdfConverter {
	 @Override
	    protected void doConvert(XWPFDocument document, OutputStream out,
	                             Writer writer, PdfOptions options) throws XWPFConverterException,
	            IOException {
	        super.doConvert(document,out,writer,options);
	    }
}
