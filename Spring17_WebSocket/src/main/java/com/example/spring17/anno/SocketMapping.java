package com.example.spring17.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * @Target(ElementType.METHOD) => 메소드에 붙이는 어노테이션 
 * 
 * @SocketMapping(value="/chat/send")
 * value 는 약속된 값으로, value 에 한해서 줄여사용할 수 있다.
 * @SocketMapping("/chat/send")
 * 
 * String value(); 의 선언을 하면 위와 같은 형식으로 @SocketMapping() 을 사용할 수 있다.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SocketMapping {
	String value();
}
