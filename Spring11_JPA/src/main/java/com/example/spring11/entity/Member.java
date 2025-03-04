package com.example.spring11.entity;

import com.example.spring11.dto.MemberDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name="MEMBER_INFO")	// MEMBER_INFO 라는 이름의 테이블이 만들어진다.
@SequenceGenerator(
		name = "GENERATOR",
		sequenceName = "MEMBER_INFO_SEQ"
)
public class Member {
	/*
	 * @Entity 어노테이션으로 테이블이 만들어지고 아래에 나열한 필드명으로 테이블의 컬럼이 결정된다.
	 * 
	 * @Id 는 primary key 값으로 사용할 필드에 붙인다.
	 * @GeneratedValue 는 시퀀스가 자동으로 만들어지고 사용되기 해준다.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GENERATOR")
	private Integer num;
	private String name;
	private String addr;
	
	//dto 를 entity 로 변환하는 static 메소드
	public static Member toEntity(MemberDto dto) {
		return Member.builder()
				.num(dto.getNum())
				.name(dto.getName())
				.addr(dto.getAddr())
				.build();
	}
}

