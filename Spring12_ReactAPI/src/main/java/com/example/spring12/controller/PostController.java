package com.example.spring12.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring12.dto.PostDto;
import com.example.spring12.entity.Post;
import com.example.spring12.repository.PostRepository;

//무조건 Json 을 응답하는 API => Restful API
@RequestMapping("/v1")	// 모든 요청에 대해서 메소드 상관 없이 이곳으로 ex) v1/posts 요청 시 
@RestController
public class PostController {
	
	// @ResponseBody RestController 에서는 @ResponseBody 가 기본이다.
	/*
	 * 보통 API 서버에는 클라이언트가 json 문자열을 전송한다.
	 * 해당 json 문자열에서 데이터를 추출하기 위해서는 @RequestBody 라는 어노테이션이 필요하다.
	 */
	
	@Autowired private PostRepository repo;
	
	
	
	// Map, dto, List, int, String 모두 응답할 수 있다.
	// 일반 form 전송일 경우 input 요소의 name 속성 값이 dto 의 필드명과 동일하다면 자동 추출된다.
	// 그러나 API 전송일 경우 Json 문자열을 전송 및 전달 받는다. 이 때는 추출하는 방법이 다르다.
	// 이때, 매개변수에 @RequestBody 어노테이션을 붙인다.
	@PostMapping("/posts")
	public PostDto insert(@RequestBody PostDto dto){
		
		// dto 를 Entity 로 변경해서 save 메소드에 전달한다.
		Post post = repo.save(Post.toEntity(dto)); // 방금 저장된 정보가 들어있는 Entity 가 리턴된다.
		
		// Entity 를 dto 로 변경해서 리턴하기
		return PostDto.toDto(post);
	}
	
	// 글 목록 요청 처리
	@GetMapping("/posts")	// 같은 경로지만 메소드가 다르다
	public List<PostDto> list(){
		// Entity List 를 dto List 로 변경해서 리턴한다.
		return repo.findAll().stream().map(PostDto::toDto).toList();
	}
	
	@DeleteMapping("/posts/{id}") // 넘어오는 {id} 를 추출해서 long id 에 담긴다.
	public PostDto delete(@PathVariable("id") long id) {
		// 삭제 할 post 를 읽어온다. (삭제 post 정보를 응답하기 위해)
		Post post = repo.findById(id).get();
		
		// id 를 이용해 삭제한다.
		repo.deleteById(id);
		
		return PostDto.toDto(post);
	}
	
	@PutMapping("/posts/{id}")
	public PostDto updateAll(@PathVariable("id") long id, @RequestBody PostDto dto) {
		dto.setId(id);
		// Entity 의 id 가 null 이 아니기 때문에 insert 가 아닌 update 가 수행된다.
		repo.save(Post.toEntity(dto));
		
		return dto;
	}
	
	
	
}
