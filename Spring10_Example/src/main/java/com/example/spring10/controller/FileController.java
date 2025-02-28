package com.example.spring10.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring10.dto.FileDto;
import com.example.spring10.dto.FileListDto;
import com.example.spring10.service.FileService;

@Controller
public class FileController {
	
	@Autowired FileService service;
	
	
	@PostMapping("/file/upload")
	public String uploadFile(FileDto dto, RedirectAttributes ra) {
		System.out.println("111");
		service.uploadFile(dto);
		System.out.println("222");
		ra.addFlashAttribute("msg", "파일을 성공적으로 업로드 했습니다.");
		
		return "redirect:/file/list";
	}
	
	@PostMapping("/file/upload2")
	public String uploadFile2(FileDto dto, RedirectAttributes ra) {
		// dto 에는 title 과 myFile 정보가 들어있다.
		service.uploadFile2(dto);
		ra.addFlashAttribute("msg", "파일을 성공적으로 업로드 했습니다.");
		
		return "redirect:/file/list";
	}
	
	@GetMapping("/file/upload-form")
	public String fileUploadForm() {
		return "file/upload-form";
	}
	
	@GetMapping("/file/list")
	public String fileList(FileListDto fileListDto, Model model) {
		// 처음에 비어있는 listDto 를 매개변수로 받아 (검색 조건은 들어있을 수 있음)
		// 서비스에 요청해서 여러 데이터가 담긴 listDto 가되어 돌아온다
		fileListDto = service.getDatas(fileListDto);
		
		// 응답에 필요한 데이터를 Model 객체에 담는다.
		model.addAttribute("dto", fileListDto);
		// template 페이지에서 응답하기
		return "file/list";
	}
	
	
	
	@GetMapping("/file/download")
	public ResponseEntity<InputStreamResource> downloadFile(long num) {
		return service.getResponse(num);
	}
	
	@GetMapping("/file/delete")
	public String deleteFile(long num) {
		service.deleteFile(num);
		return "redirect:/file/list";
	}
	
}
