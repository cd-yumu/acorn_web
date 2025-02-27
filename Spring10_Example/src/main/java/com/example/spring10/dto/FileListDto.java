package com.example.spring10.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("fileListDto")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FileListDto {
	
	private List<FileDto> list;
	
	private int startRowNum;	// DB 에서 가져올 시작 row
	private int endRowNum;		// DB 에서 가져올 끝 row
	
	private int startPageNum;	// 하단 페이징 시작 번호
	private int endPageNum;		// 하단 페이징 끝 번호
	private int pageNum;		// 현재 페이지 번호
	
	private int totalPageCount;	// 총 페이지 개수가 0개면 표시 안할거임
	private int totalRowCount;	// ?
	
	private String findQuery;
	private String condition;
	private String keyword;

}
