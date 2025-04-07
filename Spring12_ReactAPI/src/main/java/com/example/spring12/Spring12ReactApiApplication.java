package com.example.spring12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.spring12.entity.Post;
import com.example.spring12.repository.PostRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Spring12ReactApiApplication {
	
	//@Autowird PostRepository repo;
	// 위 코드로 객체를 주입 받는 것 대신 아래 코드로 생성자를 이용해 주입받을 수도 있다.
	
	// 생성자에 필요한 객체를 Spring 이 DI 해준다.
	PostRepository repo;
	// Constructor
	public Spring12ReactApiApplication(PostRepository repo) {
		this.repo = repo;
	}
	
	
	@PostConstruct 
	   public void initPost() {
	      //반복문 돌면서 sample post 를 여러개 저장한다.
	      for(int i=0;i<10;i++) {
	         Post p = Post.builder().title("제목"+i).author("작성자"+i).build();
	         repo.save(p);
	      }
	   }

	public static void main(String[] args) {
		SpringApplication.run(Spring12ReactApiApplication.class, args);
	}
}
