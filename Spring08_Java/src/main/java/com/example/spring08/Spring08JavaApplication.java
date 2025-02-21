package com.example.spring08;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.spring08.util.Messenger;
import com.example.spring08.util.WritingUtil;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class Spring08JavaApplication {
	
	@Autowired
	private WritingUtil util;
	
	@Autowired
	private Messenger messenger;
	
	
	@PostConstruct
	public void testAop() {
		
		messenger.sendGreeting("안녕 하세유...");
		messenger.sendGreeting("안녕 맹구야");
		System.out.println("------------------------------");
		
		String result = messenger.getMessage();
		System.out.println("result: " + result);
		
		
		
		System.out.println("------------------------------");
		/*
		util.writeLetter();
		util.writeReport();
		util.writeDiary();
		*/
	}

	public static void main(String[] args) {
		SpringApplication.run(Spring08JavaApplication.class, args);
		
		String pwd = "1234";
		
		// 비밀번호를 암호화 해주는 객체
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		// 암호화된 비밀번호 얻어내기
		String encodedPwd = encoder.encode(pwd);
		// 결과 출력
		System.out.println(pwd+" 를 암호화 하면"+encodedPwd);
		//1234 를 암호화 하면$2a$10$NhefrqeEG8y9c5iSISEMs.bBh/P/1I0q/7jWuIoGR5WQ0Jgbxx9iy
		
		// 날 것의 비밀번호와 암호화 된 비밀번호가 일치하는지 여부 알아내기
		boolean isValid = BCrypt.checkpw("12345", encodedPwd);
		System.out.println(isValid); //true
		
		
		
		// of 메소드로 만든 List 는 읽기 전용 (Read only) 이다.
		List<String> names = List.of("김구라", "해골", "원숭이");
		//names.add("주뎅이"); // 동작하지 않는다 (예외 발생)
		
		// List 의 stream() 메소드를 호출하고 나면 Stream type 이 리턴된다.
		Stream<String> stream = names.stream();
		
		Function<String, String> f = (item) -> {
			return item + "님";
		};
		
		// 위의 Function Type 을 줄여서 쓰면 아래와 같다.
		Function<String, String> f2 = item -> item+"놈";  // 로직이 없어서 람다식으로 표현 가능
		
		List<String> names2 =  stream.map(f).toList();		// 참고로, stream 사용은 한 번만 가능
		System.out.println(names2);		// [김구라님, 해골님, 원숭이님]
		
		List<String> names3 = names.stream().map(f2).toList();
		System.out.println(names3);		// [김구라놈, 해골놈, 원숭이놈]
		// 한줄 코딩만으로 List 를 만들수 있다. 원래 리스트(names) + stream 메소드 + map 메소드 활용해서
		
		List<String> names4 = names.stream().map(item -> item + "님").toList();
		// 요런 코딩도 가능
		
		
		/* javascript 에서는 이렇게 썼다.
		 * 
		 * let names = ["kim", "lee", "park"];
		 * let names2 = names.map(item=>item+"님");
		 * 
		 * 근데 억지로 따라 하다보니 중간에 뭐가 붙는다..
		 */
		
		
		List<Integer> nums = List.of(-10, 20, 30, -5, 25, -30);
		
		// 양의 정수만으로 만들고 싶다면
		/*
		List<Integer> newNums = new ArrayList<Integer>();
		for(int tmp:nums) {
			if(tmp>0) {
				newNums.add(tmp);
			}
		}*/
		// 하던 것을
		
		// nums 에서 양의 정수만 남겨진 새로운 List 얻어내기
		List<Integer> newNums = nums.stream().filter(item -> item > 0).toList();
		// 이 한 줄로 그냥 게임 끝
		System.out.println(newNums);	// [20, 30, 25]
		
		// nums 에서 양의 정수만 남기고 2배로 곱한 새로운 List 얻어내기
		List<Integer> newNums2 = nums.stream().filter(item -> item > 0).map(item -> item*2).toList();
		System.out.println(newNums2);	// [40, 60, 50]
		
		// nums 에서 양의 정수만 남기고 2배를 곱한 새로운 List 얻어내서 순서대로 해당 숫자를 콘솔창에 모두 출력하기
		nums.stream().filter(item -> item > 0).map(item -> item*2).forEach(item -> {
			System.out.println(item);
		});
		
		// 문자열(String)이 들어있는 List
		List<String> strNums = List.of("10", "20", "30", "40", "50");
		
		// 위의 List 를 활용해서 List<Integer> 를 얻어내보세요
		List<Integer> intNums =  strNums.stream().map(item -> Integer.parseInt(item)).toList();
		System.out.println(intNums);
		
		//꿍짝꿍짝
		
		// Integer 클래스가 가지고 있는 parseInt 메소드를 참조해서 map() 함수에 전달해서 동일한 작업도 가능하다
		List<Integer> intNums2 = strNums.stream().map(Integer::parseInt).toList();
		System.out.println(intNums2);
	}

}
