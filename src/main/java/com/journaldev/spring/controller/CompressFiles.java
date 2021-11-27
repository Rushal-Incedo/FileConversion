package com.journaldev.spring.controller;



import java.io.*;
import java.nio.file.*;
import java.util.zip.*;

import org.springframework.stereotype.Component;

@Component
public class CompressFiles {
	public static void main(String[] args) throws IOException {
// TODO Auto-generated method stub
		String sourceFile = "C:\\Users\\TEENA 1\\Desktop\\sample.pdf";
		//zipFile(sourceFile);
	}

	public  File zipFile(File file) {
		File outputFIle=null;
		try {
			//File f = new File(sourceFile);
			System.out.println(file.getName());
			String extensionRemoved = file.getName().split("\\.")[0];
			FileOutputStream fos = new FileOutputStream("D:\\Test\\" + extensionRemoved + "_Convert.zip");
			ZipOutputStream zipOut = new ZipOutputStream(fos);
		//	File fileToZip = new File(sourceFile);
			FileInputStream fis = new FileInputStream(file);
			ZipEntry zipEntry = new ZipEntry(file.getName());
			zipOut.putNextEntry(zipEntry);
			byte[] bytes = new byte[1024];
			int length;
			while ((length = fis.read(bytes)) >= 0) {
				zipOut.write(bytes, 0, length);
			}
			zipOut.close();
			fis.close();
			fos.close();
			 outputFIle=new File("D:\\Test\\" + extensionRemoved + "_Convert.zip");
			System.out.println("Done");
		} catch (FileNotFoundException ex) {
			System.err.format("The file %s does not exist", file.getName());
		} catch (IOException ex) {
			System.err.println("I/O error: " + ex);
		}
		return outputFIle;
	}
}


