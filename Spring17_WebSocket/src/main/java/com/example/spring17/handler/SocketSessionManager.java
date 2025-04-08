package com.example.spring17.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component	// Bean 생성 (어딘가 주입 받아 사용하겠다는 의미)
public class SocketSessionManager {
	// Thread Safe 한 동기화된 리스트 객체 사용하기 
	List<WebSocketSession> sessionList=Collections.synchronizedList(new ArrayList<>());
	
	/*
	 * userName <=> SocketSession 을 저장하기 위한 Map
	 * userName 을 알면 바로 세션을 구할 수 있도록..
	 * 
	 * ConcurrentHashMap 객체도 Thread Safe 한 동기화된 Map 객체 (Thread 안정성 보장 필요)
	 */
	Map<String, WebSocketSession> userSession = new ConcurrentHashMap<>();

	// 대화방에 참여한  user 의 session 을 저장하는 메소드 
	public void enterUser(String userName, WebSocketSession session) {
		userSession.put(userName, session);	// userName 과 세션 일 대 일 대응해서 저장
	}
	
	// userName 을 전달하면 해당 Session 을 리턴해주는 메소드 
	public WebSocketSession getUserSession(String userName) {
		return userSession.get(userName);	// userName 을 이용해 대응되는 세션을 반환
	}
	
	// 모든 user session 정보를 리턴하는 메소드
	public Map<String, WebSocketSession> getAllUserSession(){
		return userSession;
	}
	
	
	
	
	public void register(WebSocketSession session) {
		sessionList.add(session);
	}
	
	public void remove(WebSocketSession session) {
		sessionList.remove(session);
	}
	
	public List<WebSocketSession> getSessions(){
		return sessionList;
	}
}


