package com.example.spring07.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FileDto {
	private long id;				// primary Key
	private String uploader;		// 누가 저장
	private String title;			// 저장 제목
	private String orgFileName;		// 원본 파일 이름
	private String saveFileName;	// 저장 파일 이름
	private long fileSize;			// 파일 크기
	private String uploadedAt;		// 언제 저장
	// 파일 업로드 폼에 있는 input type="file" 의 name 속성의 value 와 필드명이 일치해야 한다.
	// <input type="file" name="myFile">
	private MultipartFile myFile;	// 이것까지 추가하면 편리하다
}
