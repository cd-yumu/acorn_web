package com.example.spring10.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Alias("fileDto")
@Data
public class FileDto {
	private long num;				// primary Key
	private String uploader;		// 누가 저장
	private String originFileName;		// 원본 파일 이름		(다운로드 할 때는 원래의 파일 이름으로 다운로드 되어야 하니깐)
	private String saveFileName;	// 저장 파일 이름		(저장할 때는 파일명이 달라져서 저장되지만)
	private long fileSize;			// 파일 크기			(다운로드 할 때 파일 크기 필요하다..
	private String uploadedAt;		// 언제 저장
	private MultipartFile myFile;	// 이것까지 추가하면 편리하다
	
}
