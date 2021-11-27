package com.journaldev.spring.controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;

import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;

//import org.apache.poi.xwpf.converter.pdf.PdfConverter;
//import org.apache.poi.xwpf.converter.pdf.PdfOptions;
//import org.apache.poi.xwpf.usermodel.XWPFDocument;
@Component
public class DocToPdfconverter {



  public static void main(String args[]) throws IOException {
     // docTopdf();
  }

  public  File docTopdf(File file) throws IOException {
      System.out.println("In doc to pdf");
      File file1 = new File("D:\\Test\\"+file.getName().split("\\.")[0]+"_Convert" + ".pdf");


      InputStream in = new FileInputStream(file);
      XWPFDocument document = new XWPFDocument(in);
      PdfOptions options = PdfOptions.create();
      OutputStream out = new FileOutputStream(file1);
      //   PdfConverter.getInstance().convert(document, out, options);
      PdfConverterUtility pdfConv=new PdfConverterUtility();
      Writer writer = new PrintWriter(file1);

      pdfConv.doConvert(document, out,writer, options);

      System.out.println("Create file");
      document.close();
      out.close();
      return file1;
  }

  private static void pdfToDoc(){

  }

}



