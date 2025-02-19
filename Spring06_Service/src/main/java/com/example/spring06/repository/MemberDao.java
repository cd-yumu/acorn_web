package com.example.spring06.repository;

import java.util.List;

import com.example.spring06.dto.MemberDto;

public interface MemberDao {
	// 만들 메소드를 미리 정의(만) 한다. 구현은 클래스가 이 인터페이스를 구현함으로써 생성한다.
	public List<MemberDto> getList();
	public void insert(MemberDto dto);
	// update, delete 는 수정, 삭제된 row 의 갯수를 리턴하는 모양으로 Dao 메소드를 정의한다.
	public int update(MemberDto dto);
	public int delete(int num);
	public MemberDto getData(int num);
}
