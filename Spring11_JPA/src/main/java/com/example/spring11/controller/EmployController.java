package com.example.spring11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.spring11.dto.EmpDeptDto;
import com.example.spring11.dto.EmpDto;
import com.example.spring11.entity.Emp;
import com.example.spring11.repository.EmpRepository;


@Controller
public class EmployController {
	
	// 테스트의 편의를 위해 서비스 만들지 않고 바로 Repository 객체 활용하기
	@Autowired private EmpRepository empRepo;
	
	@GetMapping("/emp/list")
	public String list(Model model) {
		// List<Emp> 를 stream() 을 이용해서 List<EmpDto> 로 만든다.
		
		// @id (empno) 컬럼에 대해서 오름차순 정렬된 결과
		//List<EmpDto> list = empRepo.findAll().stream().map(EmpDto::toDto).toList();
		
		// ename 컬럼에 대해서 오름차순 정렬된 결과
		List<EmpDto> list = empRepo.findAllByOrderByEnameAsc().stream().map(EmpDto::toDto).toList();
		model.addAttribute("list", list);
		return "emp/list";
	}
	
	@GetMapping("/emp/list2")
	public String List2(Model model) {
		
		// 부서 정보를 포함한 모든 사원의 목록
		List<EmpDeptDto> list = empRepo.findAll().stream().map(EmpDeptDto::toDto).toList();
		model.addAttribute("list", list);
		
		return "emp/list2";
	}
	
	@GetMapping("/emp/info")
	public String empInfo(int empno, Model model) {
		// 사원번호를 이용해 해당 사원의 정보 추출
		Emp emp = empRepo.findById(empno).get();
		// Entity 객체를 사용가능한 Dto 객체로 변환하기
		EmpDeptDto dto = EmpDeptDto.toDto(emp);
		System.out.println("추출된 데이터 dto: " + dto);
		model.addAttribute("dto", dto);
		
		return "emp/info";
	}
	
	@GetMapping("/emp/dinfo")
	public String dinfo(int deptno, Model model) {

		List<Emp> list = empRepo.findAllByDeptno();
		model.addAttribute("list", list);
		
		return "emp/info-dept.html";
	}
	
}
