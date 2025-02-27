package com.example.spring10.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.spring10.dto.CommentDto;
import com.example.spring10.dto.PostDto;
import com.example.spring10.exception.DeniedException;
import com.example.spring10.repository.CommentDao;
import com.example.spring10.repository.FileDao;
import com.example.spring10.repository.PostDao;

@Component	// bean 으로 만들기 위한 어노테이션
@Aspect		// aspect 역할을 하기 위한 어노테이션
public class AuthAspect {
	
	@Autowired private PostDao postDao;
	@Autowired private CommentDao commentDao;
	@Autowired private FileDao fileDao;
	
	
	// 매소드에 전달된 매개 변수 값을 읽어내야 하기 때문에 around 를 사용해야 한다.
	// before 나 after 는 joinPoint 가 제공되지 않기때문에
	@Around("execution(* com.example.spring10.service.*.delete*(..)) ||execution(* com.example.spring10.service.*.update*(..))")
	public Object checkWriter(ProceedingJoinPoint joinPoint) throws Throwable {
		
		String methodName=joinPoint.getSignature().getName();
		System.out.println(methodName + " 메소드에 aop 가 적용됨");
		
		// 수정, 삭제 작업을 할 글번호
		long num = 0;
		
		// 매개변수에 전달된 데이터를 Object[] 로 얻어내기
		Object[] args = joinPoint.getArgs();
		// 반복문 돌리면서 원하는 type 을 찾는다.
		for(Object tmp:args) {
			if(tmp instanceof Long) {
				num = (long)tmp;
			} else if(tmp instanceof PostDto) {
				num = ((PostDto)tmp).getNum();
			} else if(tmp instanceof CommentDto) {
				num = ((CommentDto)tmp).getNum();
			}
		}
		
		String writer = null;
		if(methodName.contains("Post")) {
			writer = postDao.getData(num).getWriter();
		} else if(methodName.contains("Comment")) {
			writer = commentDao.getData(num).getWriter();
		} else if(methodName.contains("File")) {
			writer = fileDao.getData(num).getUploader();
		}
		
		// 로그인된 userName
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		
		if(!writer.equals(userName)) {
			throw new DeniedException("요청이 거부되었습니다.");
		}
		
		// aop 가 적용된 메소드를 실행한다
		Object obj = joinPoint.proceed();
		
		return obj;
	}
	
	
}
