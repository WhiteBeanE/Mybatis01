package com.oracle.oBootMybatis01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.service.EmpService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class EmpController {
	private final EmpService empService;
	
	@RequestMapping("listEmp")
	public String empList(Emp emp, String currentPage, Model model) {
		System.out.println("EmpController.empList Start");
		// Emp 전체 Count 28
		int totalEmp = empService.totalEmp();
		System.out.println("EmpController.empList totalEmp -> " + totalEmp);
		
		model.addAttribute("totalEmp", totalEmp);
		return "list";
	}
}
