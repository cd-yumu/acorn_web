package com.example.mytest07.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Alias("userDto")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	public long num;
	public String id;
	public String pwd;
	public String email;
	public String profileImage;
	public String role;
	public String createdAt;
}
