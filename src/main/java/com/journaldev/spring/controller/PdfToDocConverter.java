package com.journaldev.spring.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

@Component
public class PdfToDocConverter {

	 // private static final String PDF_FILE_PATH = "D:\\Test\\Knowledge_Article.pdf";
public static void main(String arg[])
{
	String abc="samit.pdf";
	     System.out.println(abc.split("\\.")[0]+"_Convert" + ".pdf");
	 //   abc.split("\\.")[0];
}

		public File PdfToDocConverter(File file) {
			
			File file1 = new File("D:\\Test\\"+file.getName().split("\\.")[0]+"_Convert" + ".doc");
			try {
				System.out.println("In main");
				String content = pdftoText(file);

				// if file doesnt exists, then create it
				if (!file1.exists()) {
					file1.createNewFile();
				}

				FileWriter fw = new FileWriter(file1.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(content);
				bw.close();

				System.out.println("Done");

			} catch (IOException e) {
				e.printStackTrace();
			}
			return file1;
		}

	    static String pdftoText(File file) {
	        PDFParser parser;
	        String parsedText = null;
	        PDFTextStripper pdfStripper = null;
	        PDDocument pdDoc = null;
	        COSDocument cosDoc = null;
	        //File file = new File(fileName);
	        if (!file.isFile()) {
	            System.err.println("File " + file.getName() + " does not exist.");
	            return null;
	        }
	        try {
	            parser = new PDFParser( new RandomAccessBufferedFileInputStream(file));
	        } catch (IOException e) {
	            System.err.println("Unable to open PDF Parser. " + e.getMessage());
	            return null;
	        }
	        try {
	            parser.parse();
	            cosDoc = parser.getDocument();
	            pdfStripper = new PDFTextStripper();
	            pdDoc = new PDDocument(cosDoc);
	            parsedText = pdfStripper.getText(pdDoc);
	        } catch (Exception e) {
	            System.err
	                    .println("An exception occured in parsing the PDF Document."
	                            + e.getMessage());
	        } finally {
	            try {
	                if (cosDoc != null)
	                    cosDoc.close();
	                if (pdDoc != null)
	                    pdDoc.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return parsedText;
	    }


	}
