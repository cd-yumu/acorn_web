package com.example.spring11.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring11.dto.MemberDto;
import com.example.spring11.entity.Member;
import com.example.spring11.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired private MemberRepository repo;
	
	
	@Override
	public List<MemberDto> getAll() {
		// Member Entity 의 목록
		List<Member> entityList = repo.findAll();
		
		/*
		// MemberDto 의 목록으로 만들어서 리턴해야 한다.
		List<MemberDto> list = new ArrayList<>();
		// 반복문 돌면서 Member 객체를 순서대로 참조
		for(Member tmp:entityList) {
			list.add(MemberDto.toDto(tmp));
		}
		// 위 작업을 자바 중수라면 한 줄 코딩 해야 한다...!
		// Map 의 Stream 메소드 사용하기..!
		*/
		
		// 방법 1
		// List<MemberDto> list = entityList.stream().map(item -> MemberDto.toDto(item)).toList();
		
		// 방법 2
		List<MemberDto> list = entityList.stream().map(MemberDto::toDto).toList();
		
		return list;
	}

	@Override
	public void saveMember(MemberDto dto) {
		// dto 를 Entity 로 변경해서 저장한다.
		repo.save(Member.toEntity(dto));
	}

}
