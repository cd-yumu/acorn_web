package com.example.spring07.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class FileController {

	@GetMapping("/file/new")
	public String newForm() {
		return "file/new";
	}
	
	/*
	 * <input type="file" name="myFile" >
	 * 은 MultipartFile myFile 로 해서 업로드된 파일의 정보를 얻어내야 한다.
	 */
	@PostMapping("/file/upload")
	public String upload(String title, MultipartFile myFile) {
		
		System.out.println("title: " + title);
		System.out.println("원본 파일명: " + myFile.getOriginalFilename());
		System.out.println("파일의 크기: " + myFile.getSize());
		
		return "file/upload";
	}
}
