package com.example.spring18.repository;

import java.util.List;

import com.example.spring18.dto.MemberDto;

public interface MemberDao {
	// 만들 메소드를 미리 정의(만) 한다. 구현은 클래스가 이 인터페이스를 구현함으로써 생성한다.
	public List<MemberDto> getList();
	public void insert(MemberDto dto);
	public void update(MemberDto dto);
	public void delete(int num);
	public MemberDto getData(int num);
}
