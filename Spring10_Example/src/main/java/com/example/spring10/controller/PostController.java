package com.example.spring10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.spring10.dto.CommentDto;
import com.example.spring10.dto.PostDto;
import com.example.spring10.dto.PostListDto;
import com.example.spring10.service.PostService;

import jakarta.servlet.http.HttpSession;


@Controller
public class PostController {
	
	@Autowired private PostService service;
	
	
	
	
	
	// 글 삭제 요청 처리
	@GetMapping("/post/delete")
	public String delete(long num) {
		service.deletePost(num);
		return "post/delete";
	}
	
	
	// 글 수정 반영 요청 처리
	@PostMapping("/post/update")
	public String update(PostDto dto, RedirectAttributes ra) {
		
		service.updatedPost(dto);
		ra.addFlashAttribute("updateMessage", dto.getNum()+" 번 글을 수정했습니다.");
		
		// 수정 반영 후 글 자세히 보기로 이동
		return "redirect:/post/view?num="+dto.getNum();
	}
	
	// 글 수정 폼 요청 처리
	@GetMapping("/post/edit")
	public String edit(long num, Model model) {
		// 수정할 글 정보를 얻어와서 Model 객체에 담는다.
		PostDto dto = service.getByNum(num);
		model.addAttribute("dto", dto);
		return "post/edit";
	}
	
	
	@GetMapping("/post/new")
	public String newForm() {
		return "post/new";
	}
	
	@PostMapping("/post/save")
	public String save(PostDto dto, RedirectAttributes ra) {
		
		// ?? 글번호를 저장하는 시점에 얻는게 아니라 미리 얻어내야 한다?
		// 아마도.. 저장하면서 얻어낸 글 번호를 따로 또 알아내야 하는 번거로움 때문인듯
		
		// 새 글을 저장하고 글 번호를 리턴 받는다.
		long num = service.createPost(dto);
		
		/*
		 * RedirectAttribute 객체에 FlashAttribute 로 담은 내용은
		 * redirect 이동된 요청을 처리하는 곳의 Model 객체에 자동으로 담긴다.
		 */
		
		ra.addFlashAttribute("saveMessage", "글을 성공적으로 저장했습니다.");
		
		// 글 자세히 보기로 리다이렉트 이동하라는 응답하기
		return "redirect:/post/view?num=" + num;
	}
	/*
	 *  dto 에 글번호만 있는 경우도 있고
	 *  검색과 관련된 정보도 같이 있을 수 있다.
	 *  PostDto dto 를 인자로 받아야 num 또는 num, condition, keyword 이렇게 받을 수 있다.
	 */
	@GetMapping("/post/view")
	public String view(PostDto dto, Model model, HttpSession session) {
		
		PostDto resultDto = service.getDetail(dto);
		model.addAttribute("postDto", resultDto);
		
		// 새로 작성한 글이 아닌 경우에만 조회수 관련 처리를 한다.
		if(model.getAttribute("saveMessage") == null) {
			// 조회수 관련 처리를 한다.
			String sessionId = session.getId();
		}
		
		// 조회수 관련 처리를 한다.
		String sessionId = session.getId();
		service.manageViewCount(dto.getNum(), sessionId);
		
		
		return "post/view";
	}
	
	
	/*
	 * pageNum 이 파라미터로 넘어오지 않으면 pageNum 의 default 값은 1로 설정
	 * 검색 키워드도 파라미터로 넘어오면 PostDto 에는 condition 과 keyword 가 null 이 아니다.
	 * 검색 키워드가 넘어오지 않으면 PostDto 의 condition 과 keyword 는 null 이다.
	 */

	// @RequestParam 은 파라미터를 직접 추출할 때 사용되는 어노테이션으로 평소 생략 가능하다.
	// defaultValue 는 문자열 타입이지만 자동 int 형으로 형 변환이 이루어진다.
	@GetMapping("/post/list")
	public String list(@RequestParam(defaultValue = "1") int pageNum, PostDto search, Model model) {
		PostListDto dto = service.getPosts(pageNum, search);
		model.addAttribute("dto", dto);
		
		
		// 테스트를 위한 임시 데이터
		model.addAttribute("num", 1);
		model.addAttribute("name", "김구라");
		
		
		return "post/list";
	}
}
