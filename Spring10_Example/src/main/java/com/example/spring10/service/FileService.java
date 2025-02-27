package com.example.spring10.service;

import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import com.example.spring10.dto.FileDto;
import com.example.spring10.dto.FileListDto;

public interface FileService {
	
	public FileListDto getDatas(FileListDto fileListDto);
	public void uploadFile(FileDto dto);
	public ResponseEntity<InputStreamResource> downloadFile(long num);
	public void deleteFile(long num);

}
