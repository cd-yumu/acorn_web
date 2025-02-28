package com.example.spring11.dto;

import com.example.spring11.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDto {
	
	private Integer num;
	private String name;
	private String addr;
	
	// entity 를 dto 로 변환하는 static 메소드
	public static MemberDto toDto(Member entity) {
		// 매개 변수에 전달되는 Member entity 객체에 담긴 내용을 이용해서 MemberDto 객체를 만들어서 리턴해준다.
		return MemberDto.builder()
				.num(entity.getNum())
				.name(entity.getName())
				.addr(entity.getAddr())
				.build();
	}
	
	
}
