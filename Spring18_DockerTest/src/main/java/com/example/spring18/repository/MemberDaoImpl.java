package com.example.spring18.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.spring18.dto.MemberDto;

// dao 를 bean 으로 만들기 위한 어노테이션
@Repository
public class MemberDaoImpl implements MemberDao{

	// MyBatis 기반으로 DB 관련 작업을 하기 위한 핵심 의존 객체를 DI 받는다.
	@Autowired
	private SqlSession session; // MyBatis 의 Session 객체를 이용
	
	@Override
	public List<MemberDto> getList() {

		// SqlSession 객체를 이용해서 회원 목록을 얻어온다.
		List<MemberDto> list = session.selectList("member.getList");
		
		return list;
	} // 매우 간단해진다. try catch 문이고 sql쿼리문이고 뭐고 다 없다. 그저 목록을 얻어달라는 코드만 있으면 된다.

	@Override
	public MemberDto getData(int num) {
		
		MemberDto dto = session.selectOne("member.getData", num);
		
		return dto;
	}
	
	@Override
	public void insert(MemberDto dto) {
		/*
		 * Mapper 의 namespace : member
		 * sql 의 id : insert
		 * parameterType : MemberDto
		 */
		session.insert("member.insert", dto);	// 전달할 정보를 dto(또는 map)에 담아서 전달
												// 현재 이 dto 에는 name 과 addr 이 담겨있다.
	}

	@Override
	public void update(MemberDto dto) {
		/*
		 * Mapper namespace: member
		 * sql id: update
		 * parameterType: MemberDto
		 */
		session.update("member.update", dto);
	}

	@Override
	public void delete(int num) {
		session.delete("member.delete", num);
	}
}
