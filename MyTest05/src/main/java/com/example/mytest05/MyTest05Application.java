package com.example.mytest05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;

@PropertySource(value="classpath:custom.properties")
@SpringBootApplication
public class MyTest05Application {

	public static void main(String[] args) {
		SpringApplication.run(MyTest05Application.class, args);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
