package com.example.spring11.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.spring11.entity.Member;

/*
 * 아래와 같이 선언만 해도 MemberRepository 인터페이스를 구현한 클래스로 생성된 객체가
 * bean 으로 관리된다.
 */

// extends JpaRepository<Entity type, Entity 에서 아이디 역할을 하는 필드의 Type>
public interface MemberRepository extends JpaRepository<Member, Integer>{
	// 번호에 대해서 내림차순 정렬해서 select 하는 메소드 추가
	
	/*
	 * 정렬된 결과를 select 하는 메소드를 custom 으로 추가하기
	 * - 정해진 형식이 있다.
	 * 
	 * findAllByOrderBy컬럼명Desc()
	 * findAllByOrderBy컬럼명Asc()
	 */
	
	public List<Member> findAllByOrderByNumDesc();
	// 메소드 명명: findAllByOrderBy + 컬럼명 + 차순종류
	// 이것이 JspRepository 의 메소드 작성 규칙이다.
	
	
	/*
	 * Java Persistence Query Language (JPQL)
	 * - JPQL은 SQL 과 유사하지만 엔티티와 속성에 기반하여 작성되며, 데이터베이스는 종속적이지 않음
	 * - JPQL 만의 문법이 존재한다.
	 * - Entity 의 name 은 @Entity 어노테이션이 붙어있는 클래스의 이름 또는 name 속성의 value
	 * - Entity 의 별칭은 필수
	 * - Select 된 row 의 정보를 Entity 혹은 Dto 에 담을 수 있다.
	 */
	
	@Query(value = "SELECT m FROM MEMBER_INFO m ORDER BY m.num DESC")
	public List<Member> getList(); // 메소드명은 마음대로 규칙 없이
	
}