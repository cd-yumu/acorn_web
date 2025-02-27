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

import com.example.spring10.dto.FileDto;
import com.example.spring10.dto.FileListDto;
import com.example.spring10.service.FileService;

@Controller
public class FileController {
	
	@Autowired FileService service;
	
	@GetMapping("/file/list")
	public String fileList(FileListDto fileListDto, Model model) {
		System.out.println("2222");
		System.out.println("리스트 가져오기 전 리스트DTO: "+fileListDto);
		// 처음에 비어있는 listDto 를 받아오면 (검색 조건은 들어있을 수 있음)
		// 서비스에 요청해서 여러 데이터가 담긴 listDto 가되어 돌아온다
		fileListDto = service.getDatas(fileListDto);
		model.addAttribute("dto", fileListDto);
		System.out.println("리스트 가져온 후 리스트DTO: "+fileListDto);
		
		return "file/list";
	}
	
	@PostMapping("/file/upload")
	public String uploadFile(FileDto dto) {
		service.uploadFile(dto);
		return "redirect:/file/list";
	}
	
	@GetMapping("/file/download")
	public ResponseEntity<InputStreamResource> downloadFile(long num) {
		return service.downloadFile(num);
	}
	
	@GetMapping("/file/delete")
	public String deleteFile(long num) {
		service.deleteFile(num);
		return "redirect:/file/list";
	}
	
}
