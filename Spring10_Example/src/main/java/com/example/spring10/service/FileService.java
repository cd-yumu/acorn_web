package com.example.spring10.service;

import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import com.example.spring10.dto.FileDto;
import com.example.spring10.dto.FileListDto;

public interface FileService {
	
	public void uploadFile(FileDto dto);
	public void updateFile(FileDto dto);
	public void deleteFile(long num);
	public void uploadFile2(FileDto dto);
	
	public ResponseEntity<InputStreamResource> getResponse(long num);
	public FileListDto getDatas(FileListDto fileListDto);
	
	
	

}
