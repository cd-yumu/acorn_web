package com.example.spring12.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring12.entity.Post;

/*
 * JpaRepository<Entity 클래스 Type, Id 역할을 하는 컬럼의 Type>
 * 위와 같이 설정하면 (인터페이스를 정의한 것 만으로)
 * PostRepository 의 구현 클래스가 자동으로 만들어지고
 * 해당 클래스로 생성된 객체가 Bean 으로 관리된다. (@Autowired 로 주입해 사용할 수 있음)
 */
public interface PostRepository extends JpaRepository<Post, Long>{

}
