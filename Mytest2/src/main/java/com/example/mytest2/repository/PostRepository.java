package com.example.mytest2.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mytest2.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
