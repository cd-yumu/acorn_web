package com.example.spring15.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring15.dto.CommentDto;
import com.example.spring15.dto.CommentListRequest;
import com.example.spring15.dto.PostDto;
import com.example.spring15.dto.PostListDto;
import com.example.spring15.service.PostService;

import jakarta.servlet.http.HttpSession;

@RestController
@Controller
public class PostController {
	
	@Autowired private PostService service;
	
	@PostMapping("/post/update-comment")
	@ResponseBody
	public Map<String, Boolean> updateComment(CommentDto dto){
		service.updateComment(dto);
		return Map.of("isSuccess", true);
	}
	
	@GetMapping("/post/delete-comment")
	@ResponseBody
	public Map<String, Boolean> commentDelete(long num){
		
		service.deleteComment(num);
		// @ResponseBody 어노테이션을 붙여놓고 아래의 데이터를 리턴하면 {"isSuccess": true} 형식의 json 문자열이 응답된다.
		return Map.of("isSuccess", true);
	}
	
	
	@GetMapping("/post/comment-list")
	@ResponseBody 
	public Map<String, Object> commentList(CommentListRequest clr){
		//CommentListRequest 객체에는 댓글의 pageNum 과 원글의 글번호 postNum 이 들어있다.
		// Map 을 리턴 받지만 Json 으로 응답된다. (Gson 기능이 내장되어 있다.)
		return service.getComments(clr);
	}
	
	// 댓글 저장 요청처리
	@PostMapping("/post/save-comment")
	@ResponseBody // dto 에 저장된 내용을 json 으로 응답하기 위한 어노테이션
	public CommentDto saveComment(CommentDto dto) {
		// dto 에는 content, postNum, targetWriter, parentNum 이 담겨있다. 
		// 단, 원글의 댓글은 parentNum 이 없고 댓댓글만 있다. (원글댓글 경우 0)
		
		service.createComment(dto);
		return dto;
	}
	
	// 글 삭제 요청 처리
	@DeleteMapping("/posts/{num}")	// /{num} - 경로변수로 넘어오는 값 받기
	public PostDto delete(@PathVariable(value = "num") long num) {
		PostDto dto = service.getByNum(num);
		// 서비스 객체를 이용해서 삭제하기
		service.deletePost(num);
		// 삭제된 글 정보를 리턴하기
		return dto;
	}
	
	
	// 글 수정 반영 요청 처리
	@PatchMapping("/posts/{num}")	// {num} 으로 경로 파라미터를 받는다.
	public PostDto update(@PathVariable(value="num") long num,	// 글 번호
			@RequestBody PostDto dto) {							// title 과 content 를 받는다.
		// 글 번호를 dto 에 담는다.
		dto.setNum(num);
		// 서비스를 이용해서 수정반영
		service.updatedPost(dto);
		// 수정된 글 정보 리턴
		return dto;
	}
	
	
	@GetMapping("/post/new")
	public String newForm() {
		return "post/new";
	}
	
	@PostMapping("/posts")
	public Map<String, Object> save(@RequestBody PostDto dto) {
		// 새 글을 저장하고 글 번호를 리턴받는다.
		long num = service.createPost(dto);
		return Map.of("num", num);
	}
	/*
	 *  dto 에 글번호만 있는 경우도 있고
	 *  검색과 관련된 정보도 같이 있을 수 있다.
	 *  PostDto dto 를 인자로 받아야 num 또는 num, condition, keyword 이렇게 받을 수 있다.
	 */
	@GetMapping("/posts/{num}")
	public PostDto view(@PathVariable(name="num") long num, PostDto dto) {		
		// 글 번호는 경로 변수에, 검색 키워드가 있다면 해당 정보는 PostDto 에 담겨있다.
		
		// 글 번호를 dto 에 담는다.
		dto.setNum(num);
		// 글 자세한 정보를 얻어와서
		PostDto resultDto = service.getDetail(dto);
		// 응답한다.
		return resultDto;
	}
	
	
	/*
	 * pageNum 이 파라미터로 넘어오지 않으면 pageNum 의 default 값은 1로 설정
	 * 검색 키워드도 파라미터로 넘어오면 PostDto 에는 condition 과 keyword 가 null 이 아니다.
	 * 검색 키워드가 넘어오지 않으면 PostDto 의 condition 과 keyword 는 null 이다.
	 */
	@GetMapping("/posts")
	public PostListDto list(@RequestParam(defaultValue = "1") int pageNum, PostDto search) {
		// 글 목록과 검색 키워드 관련 정보가 들어있는 PostListDto
		PostListDto dto = service.getPosts(pageNum, search);
		// JSON 문자열이 응답되도록 DTO 를 리턴한다.
		return dto;
	}
}
