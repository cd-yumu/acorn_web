package com.example.spring17.dto;

import lombok.Data;

@Data
public class ChatMessage{
	private String userName;	// 이름
	private String text;		// 보내는 메시지
	private String toUserName;	// 귓말을 보낼 상대
	private String saveFileName;// 파일 경로
}