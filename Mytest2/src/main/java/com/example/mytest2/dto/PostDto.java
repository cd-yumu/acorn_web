package com.example.mytest2.dto;

import com.example.mytest2.entity.Post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PostDto {
	private long id;
	private String title;
	private String author;
	
	public static PostDto toDto(Post post) {
		return PostDto.builder()
				.id(post.getId())
				.title(post.getTitle())
				.author(post.getAuthor())
				.build();
	}
}
