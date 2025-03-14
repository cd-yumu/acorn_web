package com.example.spring15.exception;

// 거부된 요청일 때 발생시킬 Exception 정의하기
public class DeniedException extends RuntimeException {
	public DeniedException(String msg) {
		super(msg);
	}
	// 그저 부모 생성자에게 메시지를 전달해주면 된다!
	// 부모가 가진 .getMessage() 를 호출하면 예외 메시지를 얻을 수 있다.
}
