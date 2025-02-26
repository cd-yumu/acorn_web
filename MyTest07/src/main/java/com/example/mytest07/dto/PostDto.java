package com.example.mytest07.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("postDto")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDto {
	private long num;
	private String writer;
	private String title;
	private String content;
	private int viewCount;
	private String createdAt;
	private String updatedAt;
}
