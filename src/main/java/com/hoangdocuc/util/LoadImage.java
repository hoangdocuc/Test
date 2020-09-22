package com.hoangdocuc.util;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = "/image/*")
public class LoadImage extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private String imagePath;
	
	
	@Override
	public void init() throws ServletException {
		imagePath = System.getProperty("catalina.home")+File.separator+"assets/user/img";
	}



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get requested Image by pathInfo
		String requestImage = request.getPathInfo();
		
		//
		if(requestImage == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		// Decode the file name
		File image = new File(imagePath, URLDecoder.decode(requestImage, "UTF-8"));
		// Check if file actually exists in file system.
		if( !image.exists()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		//Get content type bu file name
		String contentType = getServletContext().getMimeType(image.getName());
		
		// Check if file is actually an image (avoid download of other files by hacker)
		if(contentType == null || !contentType.startsWith("image")) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		response.reset();
		response.setContentType(contentType);
		response.setHeader("Content-Length", String.valueOf(image.length()));
		
		// write image content to response
		Files.copy(image.toPath(), response.getOutputStream());
	}


}
