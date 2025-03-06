package com.example.spring12.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PostPageResponse {
	// Post 전용이 아닌 겸용으로 하고 싶다면 List 의 제너릭을 받아 사용할 수 있도록 수정 할 수도 있다 (지금은 사용X)
	private List<PostDto> list;
	private int startPageNum;
	private int endPageNum;
	private int totalPageCount;
	private int pageNum;
}
