package com.example.mytest07.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("commentDto")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentDto {
	public long num;
	public String writer;
	public String content;
	public String targetWriter;
	public long postNum;
	public long parentNum;
	public String deleted;
	public String createdAt;
	public String updatedAt;
}

