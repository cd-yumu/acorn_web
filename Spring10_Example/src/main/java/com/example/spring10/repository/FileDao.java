package com.example.spring10.repository;

import java.util.List;

import com.example.spring10.dto.FileDto;
import com.example.spring10.dto.FileListDto;

public interface FileDao {
	public List<FileDto> getList(FileListDto fileListDto);
	public int saveFileInfo(FileDto dto);
	public FileDto getData(long num);
	public int deleteFile(long num);
	public int getCount(FileListDto dto);
}