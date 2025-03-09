package com.example.mytest2.entity;

import com.example.mytest2.dto.PostDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Post {
	
	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
	private String author;
	
	public static Post toEntity(PostDto dto) {
		return Post.builder()
				.id(dto.getId()==0?null:dto.getId())
				.title(dto.getTitle())
				.author(dto.getAuthor())
				.build();
	}
}
