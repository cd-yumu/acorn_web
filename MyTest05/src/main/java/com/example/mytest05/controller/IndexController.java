package com.example.mytest05.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	// 최상위 경로 요청시 home 페이지로 forward
	@GetMapping("/")
	public String index(Model model) {
		
		// 공지사항 데이터 같이
		List<String> noticeList = List.of("One", "Two", "Three");
		
		// 페이지 이동시 데이터 가져가기 (Model 에 담는다 = Request 영역에 담는다)
		model.addAttribute("noticeList",noticeList);
		
		// 아무런 설정이 없는 경로는 자동으로 templates 폴더 안의 파일로 인식
		return "home";
	}
	
	// 서버가 준비 되었을 때 실행할 메소드 설정
	@EventListener(ApplicationReadyEvent.class)
	public void openChrome() {
		String url = "http://localhost:9000/mytest05/";
		// 운영체젝의 이름을 소문자로
		String os = System.getProperty("os.name").toLowerCase();
		ProcessBuilder builder = null;
		try {
			if (os.contains("win")) {
				builder = new ProcessBuilder("cmd.exe", "/c", "start chrome " + url);
			} else if (os.contains("mac")) {
				builder = new ProcessBuilder("/usr/bin/open", "-a", "Google Chrome", url);
			} else {
				System.out.println("지원하지 않는 운영체제 입니다.");
				return;
			}
			builder.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
