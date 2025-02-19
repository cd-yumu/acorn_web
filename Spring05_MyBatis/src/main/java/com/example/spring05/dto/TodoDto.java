package com.example.spring05.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class TodoDto {
	private int id;
	private String content;
	private String regdate;
}
