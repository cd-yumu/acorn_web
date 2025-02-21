package com.example.mytest05.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.mytest05.dto.FileDto;

@Controller
public class FileController {
	
	@Value("${file.location}")
	private String fileLocation;
	
	@GetMapping("/file/new")
	public String newForm() {
		
		return "file/new";
	}
	
	@PostMapping("/file/upload")
	public String upload(FileDto dto, Model model) {
		
		MultipartFile myFile = dto.getMyFile();
		
		if(myFile.isEmpty()) {
			throw new RuntimeException("파일이 업로드 되지 않음");
		}
		
		String orgFileName = myFile.getOriginalFilename();
		long fileSize = myFile.getSize();
		String saveFileName = UUID.randomUUID().toString();
		String filePath = fileLocation + File.separator + saveFileName;
		
		try {
			File saveFile = new File(filePath);
			myFile.transferTo(saveFile);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("title", dto.getTitle());
		model.addAttribute("orgFileName", orgFileName);
		model.addAttribute("fileSize", fileSize);
		model.addAttribute("saveFileName", saveFileName);
		//원래는 DB 에 저장해야함
		
		return "file/upload";
	}
	
	@GetMapping("/file/download")
	public ResponseEntity<InputStreamResource> download(String orgFileName, String saveFileName, long fileSize){
		// 원래는 DB 에서 정보를 가져와야 함 (지금은 그냥 파라미터로 받음)
		try {
			// 파일명 다듬기: 한글 인코딩, 공백처리
			String encodedName=URLEncoder.encode(orgFileName, "utf-8");
			encodedName=encodedName.replaceAll("\\+"," ");

			// 헤더 생성
			HttpHeaders headers=new HttpHeaders();
			headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream");
			headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename="+encodedName);
			headers.setContentLength(fileSize);			

			// 파일 경로
			String filePath=fileLocation + File.separator + saveFileName;
			
			// 리턴 타입 객체 생성
			InputStream is=new FileInputStream(filePath);
			InputStreamResource isr=new InputStreamResource(is);
			ResponseEntity<InputStreamResource> resEntity=ResponseEntity.ok()
					.headers(headers)
					.body(isr);

			return resEntity;
			// ResponseEntity<InputStreamResource> 타입을 리턴해주면 파일을 다운로드 받는다!
			
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("파일을 다운로드 하는 중에 에러 발생!");
		}
	}
	
	@PostMapping("/file/upload-image")
	public String upload_image(FileDto dto, Model model) {
		
		// 업로드 파일은 MultipartFile 타입 객체로
		MultipartFile image = dto.getMyFile();
		if(image.isEmpty()) {
			throw new RuntimeException("이미지가 업로드 되지 않았습니다.");
		}
		
		// 파일 업로드에 필요한 준비물: 원본 파일명, 저장 파일명, 저장 경로, 파일 크기
		String orgFileName = image.getOriginalFilename();
		String saveFileName = UUID.randomUUID().toString()+orgFileName;
		String filePath = fileLocation + File.separator + saveFileName;
		long fileSize = image.getSize();
		
		// 파일 저장
		try {
			File saveFile = new File(filePath);
			image.transferTo(saveFile);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("title", dto.getTitle());
		model.addAttribute("orgFileName", orgFileName);
		model.addAttribute("fileSize", fileSize);
		model.addAttribute("saveFileName", saveFileName);
		
		return "file/upload-image";
	}
}
