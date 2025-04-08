package com.example.spring17.config;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.example.spring17.anno.SocketController;
import com.example.spring17.handler.DispatchingSocketHandler;
import com.example.spring17.handler.HandlerRegistry;
import com.example.spring17.handler.SocketSessionManager;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer{
	
	/*
	// 웹 소켓 핸들러(MyWebSocketHandler) 를 등록하는 메소드
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// "/ws" 경로의 웹 소켓 연결을 해 오면 MySocketHandler 객체로 처리를 하겠다는 의미
		registry.addHandler(new MySocketHandler(), "/ws")
			.setAllowedOrigins("*");	// 개발 중엔 CORS 허용
		// 동일 출처 위반? CORS 위반. ex.요청은 구글로 사용 페이지는 네이버
		
		// Web Socket 접속을 할 때 보통 ws://ip:9000/ws 와 같은 식으로 지정한다.
		// 또, 개발 중에는 CORS 환경인 경우가 많아 허용하고 배포할 때 뺀다.
	}*/
	
	// 핸들러 교체 MySocketHandler -> DispatchSocketHandler
	
	private final ApplicationContext applicationContext;
    private final SocketSessionManager sessionManager;
    
    //필요한 bean 을 생성자로 주입 받는다.
    public WebSocketConfig(ApplicationContext applicationContext, SocketSessionManager sessionManager) {
        this.applicationContext = applicationContext;
        this.sessionManager = sessionManager;
    }

	//웹소켓 핸들러를 등록하는 메소드 
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		HandlerRegistry handlerRegistry = new HandlerRegistry();
		
		// @SocketController 붙은 Bean 전부 가져오기
        Map<String, Object> controllers = applicationContext.getBeansWithAnnotation(SocketController.class);
        for (Object controller : controllers.values()) {
            handlerRegistry.register(controller); // DI가 적용된 상태의 Bean 등록
        }
		
		// "/ws" 경로의 웹소캣 연결을 해 오면 MySocketHandler 객체로 처리를 하겠다는 의미 
		registry.addHandler(new DispatchingSocketHandler(handlerRegistry, sessionManager), "/ws")
			.setAllowedOrigins("*"); // 개발 중엔 CORS 허용
		
	}
}