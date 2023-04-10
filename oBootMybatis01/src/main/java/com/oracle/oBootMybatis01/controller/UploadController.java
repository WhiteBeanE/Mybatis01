package com.oracle.oBootMybatis01.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UploadController {
	
	@GetMapping("/uploadFormStart")
	public String uploadFormStart(Model model) {
		System.out.println("UploadController.uploadFormStart Start");
		
		return "upLoadFormStart";
	}
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		System.out.println("UploadController.uploadForm Start");
		
	}
	
	@PostMapping("/uploadForm")
	public String uploadForm(HttpServletRequest request, MultipartFile file1, Model model) throws Exception {
		// Servlet 상속 받지 못했을 때 realPath 불러 오는 방법
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		
		System.out.println("UploadController.uploadForm Start");
		log.info("OriginalFilename -> " + file1.getOriginalFilename());
		log.info("Size -> " + file1.getSize());
		log.info("ContentType -> " + file1.getContentType());
		log.info("uploadPath -> " + uploadPath);
		String savedName = uploadPath(file1.getOriginalFilename(), file1.getBytes(), uploadPath);
		// SerVice -> DB CRUD
		
		log.info("savedName -> " + savedName);
		model.addAttribute("savedName", savedName);
		
		return "uploadResult";
		
	}

	private String uploadPath(String originalFilename, byte[] fileData, String uploadPath) throws IOException {
		// universally unique identifier (UUID).
		UUID uid = UUID.randomUUID();
		// requestPath = requestPath + "/resources/image";
		System.out.println("uploadPath -> " + uploadPath);
		// Directory 생성
		File fileDirectory = new File(uploadPath);
		if(!fileDirectory.exists()) {
			// 신규 폴더(Directory)생성
			fileDirectory.mkdirs();
			System.out.println("업로드용 폴더 생성: " + uploadPath);
		}
		String savedName = uid.toString() + "_" + originalFilename;
		log.info("savedName -> " + savedName);
		File target = new File(uploadPath, savedName);
		// File target = new File(requestPath, savedName);
		// File UpLoad -> uploadPath / UUID+_+originalFilename
		FileCopyUtils.copy(fileData, target); // org.springframework.util.FileCopyUtils;
		return savedName;
	}
}
