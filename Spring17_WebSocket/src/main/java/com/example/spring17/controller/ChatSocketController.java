package com.example.spring17.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.example.spring17.anno.SocketController;
import com.example.spring17.anno.SocketMapping;
import com.example.spring17.dto.ChatMessage;
import com.example.spring17.handler.SocketSessionManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SocketController
public class ChatSocketController {
	
	@Autowired
	private SocketSessionManager sessionManager;
	// 객체 <=> json 상호 변경할수 있는 객체
	ObjectMapper mapper=new ObjectMapper();
	
	@SocketMapping("/chat/image")
	public void ChatImage(WebSocketSession session, ChatMessage message) {
		Map<String, Object> map=Map.of(
			"type","image",
			"payload",Map.of(
				"userName", message.getUserName(),
				"saveFileName", message.getSaveFileName()
			)
		);
		String json="{}";
		try {
			json=mapper.writeValueAsString(map);
		}catch(Exception e) {
			e.printStackTrace();
		}
		TextMessage msg=new TextMessage(json);
		sessionManager.broadcast(msg);
	}
	
	@SocketMapping("/chat/whisper")
	public void chatWishper(WebSocketSession session, ChatMessage message) {
		
		Map<String, Object> map = Map.of(
				"type","whisper",
				"payload",Map.of(
					"userName", message.getUserName(),
					"text", message.getText(),
					"toUserName", message.getToUserName()
				)
			);
		String json = "";
		try {
			json = mapper.writeValueAsString(map);
		} catch(Exception e) {
			e.printStackTrace();
		}
		TextMessage msg = new TextMessage(json);
		// 위의 TextMessage 를 보낸 사람과 받는 사람에게 private 전송한다.
		
		// 이 메시지는 broadCast 가 아니다. (귓말) 따라서.. Manager 에 관련 메소드 생성하러 ㄱ
		
		sessionManager.privateMessage(message.getUserName(), msg);		// 수신자 와
		sessionManager.privateMessage(message.getToUserName(), msg);	// 발신자 에게 보여주기 
		
	}
	
	
	
	
	@SocketMapping("/chat/public")
	public void chatPublic(WebSocketSession session, ChatMessage message) {
		String json="""
			{
				"type":"public",
				"payload":{
					"userName":"%s",
					"text":"%s"
				}
			}
		""".formatted(message.getUserName(), message.getText());
		TextMessage msg=new TextMessage(json);
		sessionManager.broadcast(msg);
	}
	
	
	
	@SocketMapping("/chat/enter")
	public void enter(WebSocketSession session, ChatMessage message) {
		//대화방에 입장하는 userName 
		String userName=message.getUserName();
		//누가 어떤 session 으로 입장했는지 저장하기 
		sessionManager.enterUser(userName, session);
		//대화방에 입장한 모든 사용자 목록
		List<String> userList=sessionManager.getAllUserNames();
		
		Map<String, Object> map=Map.of(
			"type", "enter",
			"payload", Map.of(
				"userName", userName,
				"userList", userList
			)
		);
		/* 머릿속에 생각대로 Map 과 List 를 구성할 수 있느냐가 킥임다.
		 * {
		 * 		"type":"enter",
		 * 		"payload":{
		 * 			"userName":"xxx"
		 * 			"userList":"["kim", "lee", ...]"
		 * 		}
		 * }
		 */
		
		//ObjectMapper 객체를 이용해서 Map 에 담긴 내용을 json 문자열로 변환
		String json="{}";
		try {
			json = mapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//대화방에 입장한 모든 클라이언트에게 전송할 정보 
		TextMessage msg=new TextMessage(json);
		//session manager 객체의 메소드를 이용해서 전송한다.
		sessionManager.broadcast(msg);
		
	}
	
//	@SocketMapping("/chat/send")
//	public void sendMessage(WebSocketSession session, ChatMessage message) {
//		
//		// 전달된 대화내용을 TextMessage 객체에 담는다.
//		TextMessage msg = new TextMessage(message.getText());
//		
//		// sessionManager 객체를 이용해서 접속된 모든 세션에 TextMessage 를 전달한다.
//		sessionManager.getSessions().forEach((item)->{
//			// item 은 webSocketSession 객체 이다.
//			try {
//				item.sendMessage(msg);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		});
//	}
}
