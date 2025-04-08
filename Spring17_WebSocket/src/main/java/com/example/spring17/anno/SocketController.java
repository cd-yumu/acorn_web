package com.example.spring17.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/*
 * 어노테이션을 만들 때는 @interface 예약어를 이용한다.
 * 
 * 목적은 다음과 같은 어노테이션을 만들기 위한 것
 * @SocketController
 * public class MySocketController{}
 * 
 * @Target(ElementType.TYPE) => 클래스에 붙일 어노테이션이다 라고 선언 (클래스/메소드)
 * @Retention(RetentionPolicy.RUNTIME) => 컴파일 단계가 아닌 runtime(실행) 단계에서 해석되는 어노테이션
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface SocketController {

}
