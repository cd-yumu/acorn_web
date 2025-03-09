package com.example.mytest2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mytest2.dto.PostDto;
import com.example.mytest2.entity.Post;
import com.example.mytest2.repository.PostRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/v1")
@RestController		// @Controller + 모든 메소드에 @ResponseBody 적용
public class PostController {
	
	@Autowired private PostRepository repo;
	
	@PostMapping("/posts")
	public PostDto insert(@RequestBody PostDto dto) {
		Post post = repo.save(Post.toEntity(dto));
		return PostDto.toDto(post);
	}
	
	@GetMapping("/posts")
	public List<PostDto> list() {
		return repo.findAll().stream().map(PostDto::toDto).toList();
	}
	
	@DeleteMapping("/posts/{id}")
	public PostDto delete(@PathVariable("id") long id) {
		Post post = repo.findById(id).get();
		repo.deleteById(id);
		return PostDto.toDto(post);
	}
	
	@PostMapping("/posts/{id}")
	public PostDto editAll(@PathVariable("id") long id, @RequestBody PostDto dto) {
		dto.setId(id);
		repo.save(Post.toEntity(dto));
		return dto;
	}
	
	
	
	
}
