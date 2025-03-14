package com.example.spring15.exception;

/*
 * 비밀 번호 수정 시 입력한 비밀번호가 DB 에 저장된 비밀번호화 일치 하지 않을 때 발생시키는 Exception Type
 */

public class PasswordException extends RuntimeException{
	
	// 생성자
	public PasswordException(String message) {
		super(message); // 생성자의 매개 변수에 전달된 예외 메시지를 부모 생성자에게 전달해야
		// 이 객체의 .getMessage() 메소드를 어딘가에서 호출하면 해당 메시지가 리턴된다.
	}
}
