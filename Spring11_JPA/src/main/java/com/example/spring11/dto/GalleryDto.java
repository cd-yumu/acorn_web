package com.example.spring11.dto;

import java.text.SimpleDateFormat;
import java.util.Locale;

import com.example.spring11.entity.Gallery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GalleryDto {
	private long id;
	private String writer;
	private String title;
	// dto 의 날짜는 보통 String Type 으로 선언한다.
	private String createdAt;
	
	// Entity 를 dto 로 만들어 리턴하는 static 메소드
	public static GalleryDto toDto(Gallery gallery) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 E a hh:mm:ss", Locale.KOREA);
		// 2025년 03월 04일 화 오후 3:00:00 형식의 문자열을 얻어낼 수 있는 객체이다.
		String result = sdf.format(gallery.getCreatedAt());
		// 콘솔에 테스트로 출력해보기
		System.out.println(result);
		
		System.out.println("(gallery.getCreatedAt().toString(): " + gallery.getCreatedAt().toString());
		
		return GalleryDto.builder()
				.id(gallery.getId())
				.title(gallery.getTitle())
				.writer(gallery.getWriter())
				.createdAt(result)
				.build();
	}
}
