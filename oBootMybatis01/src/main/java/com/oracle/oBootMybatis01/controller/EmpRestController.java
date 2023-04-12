package com.oracle.oBootMybatis01.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.SampleVO;
import com.oracle.oBootMybatis01.service.EmpService;

import lombok.RequiredArgsConstructor;
// @Controller + @ResponseBody
@RestController
@RequiredArgsConstructor
public class EmpRestController {
	private final EmpService empService;
	
	@GetMapping("/helloText")
	public String helloText() {
		System.out.println("EmpRestController.helloText Start");
		String hello = "안녕";
		
		return hello;
	}
	// http://jsonviewer.stack.hu/
	@GetMapping("/sample/sendVO2")
	public SampleVO sendVO2(int deptno) {
		System.out.println("EmpRestController.sendVO2 Start");
		System.out.println("EmpRestController.sendVO2 deptno -> " + deptno);
		SampleVO vo = new SampleVO();
		vo.setFirstName("Bean");
		vo.setLastName("White");
		vo.setMno(deptno);
		// 객체면 -> JsonConverter
		
		return vo;
	}
	
	@GetMapping("/sendVO3")
	public List<Dept> sendVO3(){
		System.out.println("EmpRestController.sendVO3 Start");
		List<Dept> deptList = empService.deptSelect();
		
		return deptList;
	}
	
	@GetMapping("/getDeptName")
	public String getDeptName(int deptno, Model model) {
	System.out.println("EmpRestController.getDeptName Start");
		String deptName = empService.deptName(deptno);
		System.out.println("EmpRestController.getDeptName deptName -> " + deptName);
		
		return deptName;
	}
	
	@GetMapping("/empnoDelete")
	public int empnoDelete(Emp emp) {
		System.out.println("EmpRestController.empnoDelete Start");
		int result = empService.empDelete(emp);
		
		return result;
	}
}
