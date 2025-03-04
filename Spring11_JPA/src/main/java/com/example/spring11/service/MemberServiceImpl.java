package com.example.spring11.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring11.dto.MemberDto;
import com.example.spring11.entity.Member;
import com.example.spring11.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired private MemberRepository repo;	// dao 역할을 한다.
	
	
	@Override
	public List<MemberDto> getAll() {
		// Member Entity 의 목록
		// List<Member> entityList = repo.findAll();
		
		// 추가한 메소드를 이용해서 num 에 대해서 내림차순 정렬된 목록을 얻어낼 수 있다.
		List<Member> entityList = repo.findAllByOrderByNumDesc();
		// entity 리스트를 dto 리스트로 바꿔야 한다!
		
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
		// stream() 을 이용하면 한 줄의 coding 으로 위의 동작을 할 수가 있다.  이 한줄의 코드는 난이도가 있는 편..! map 을 사용하는 것. 메소드 참조식 :: 알기 꼭!
		List<MemberDto> list = entityList.stream().map(MemberDto::toDto).toList();
		// map 함수를 사용하기 위해서는 stream 을 호출한다. 
		
		return list;
	}

	@Override
	public void saveMember(MemberDto dto) {
		// dto 를 Entity 로 변경해서 저장한다.
		repo.save(Member.toEntity(dto));
	}

	@Override
	public void deleteMember(int num) {
		repo.deleteById(num);
	}

	@Override
	public MemberDto getData(int num) {
		Member mem = repo.findById(num).get();
		MemberDto dto = MemberDto.toDto(mem);
		return dto;
	}

}
