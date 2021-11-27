package com.journaldev.spring.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application file upload requests
 */
@Controller
public class FileUploadController {

	@Autowired
	DocToPdfconverter docToPdfconverter;
	@Autowired
	PdfToDocConverter pdfToDocConverter;
	
	@Autowired
	CompressFiles compressFiles;
	private static final Logger logger = LoggerFactory
			.getLogger(FileUploadController.class);
	String folderPath="D:/MyDoc";
	/**
	 * Upload single file using Spring Controller
	 * @throws IOException 
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody
	ModelAndView uploadFileHandler(@RequestParam("file") MultipartFile file,@RequestParam("opt") String value,@RequestParam("files") MultipartFile[] files) throws IOException {
		logger.info("dropdown value is "+value);
		ModelAndView model=new ModelAndView("download");
		if (!file.isEmpty()) {
			File dir = new File(folderPath);
			if (!dir.exists())
				dir.mkdirs();
			byte[] bytes = file.getBytes();
			File serverFile = new File(dir.getAbsolutePath()
					+ File.separator + file.getOriginalFilename());
			logger.info("FileName is "+file.getOriginalFilename());
			BufferedOutputStream stream = new BufferedOutputStream(
					new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
			if (value.equalsIgnoreCase("PdfToDoc") && file.getOriginalFilename().endsWith(".pdf")) {
				
				File newFile=pdfToDocConverter.PdfToDocConverter(serverFile);
				model.addObject("fileName", newFile.getName());
				logger.info("Server File Location="
						+ newFile.getAbsolutePath());
			}
			else if (value.equalsIgnoreCase("DocToPdf") && (file.getOriginalFilename().toLowerCase().endsWith(".doc")||file.getOriginalFilename().toLowerCase().endsWith(".docx"))) {
				
				File newFile=docToPdfconverter.docTopdf(serverFile);
				model.addObject("fileName", newFile.getName());
				logger.info("Server File Location="
						+ newFile.getAbsolutePath());
			}
          else if (value.equalsIgnoreCase("Compress")) {
				
        	    File newFile=compressFiles.zipFile(serverFile);
				model.addObject("fileName", newFile.getName());
				logger.info("Server File Location="
						+ newFile.getAbsolutePath());
			}
			try {
				
				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				
				//return "You successfully uploaded file=" + name;
			} catch (Exception e) {
				//return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			//return "You failed to upload " + name
			//		+ " because the file was empty.";
		}
		return model;
	}

	 @RequestMapping("/pdf/{fileName:.+}")
	    public void downloadPDFResource( HttpServletRequest request, 
	                                     HttpServletResponse response, 
	                                     @PathVariable("fileName") String fileName) 
	    {
	        //If user is not authorized - he should be thrown out from here itself
	         
	        //Authorized user will download the file
	        String dataDirectory = "D:/Test/";/*request.getServletContext().getRealPath("/WEB-INF/downloads/pdf/");*/
	        Path file = Paths.get(dataDirectory, fileName);
	        if (Files.exists(file)) 
	        {
	            response.setContentType("application/pdf");
	            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
	            try
	            {
	                Files.copy(file, response.getOutputStream());
	                response.getOutputStream().flush();
	            } 
	            catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
	/**
	 * Upload multiple file using Spring Controller
	 */
	@RequestMapping(value = "/uploadMultipleFile", method = RequestMethod.POST)
	public @ResponseBody
	String uploadMultipleFileHandler(@RequestParam("name") String[] names,
			@RequestParam("file") MultipartFile[] files) {

		if (files.length != names.length)
			return "Mandatory information missing";

		String message = "";
		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String name = names[i];
			try {
				byte[] bytes = file.getBytes();

				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();

				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath()
						+ File.separator + name);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();

				logger.info("Server File Location="
						+ serverFile.getAbsolutePath());

				message = message + "You successfully uploaded file=" + name
						+ "<br />";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		}
		return message;
	}

	  @RequestMapping(value = "/register", method = RequestMethod.GET)
	  public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
       System.out.println("In Get Method");
		ModelAndView model=new ModelAndView("upload");
		return model;
	}
}
