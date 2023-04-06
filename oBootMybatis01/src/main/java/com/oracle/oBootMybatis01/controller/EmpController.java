package com.oracle.oBootMybatis01.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.service.EmpService;
import com.oracle.oBootMybatis01.service.Paging;

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
		// Paging 작업
		Paging page = new Paging(totalEmp, currentPage);
		// Parameter emp -> Page만 추가 Setting
		emp.setStart(page.getStart());
		emp.setEnd(page.getEnd());
		
		List<Emp> listEmp = empService.listEmp(emp);
		System.out.println("EmpController.empList list listEmp.size() -> " + listEmp.size());
		
		model.addAttribute("totalEmp", totalEmp);
		model.addAttribute("listEmp", listEmp);
		model.addAttribute("page", page);
		
		return "list";
	}
	
	@GetMapping("detailEmp")
	public String detailEmp(int empno, Model model) {
		System.out.println("EmpController.detailEmp Start");
		System.out.println("EmpController.detailEmp empno -> " + empno);
//		1. EmpService안에 detailEmp method 선언
//		   1) parameter : empno
//		   2) Return      Emp
//		2. EmpDao   detailEmp method 선언 
////    mapper ID   ,    Parameter
//emp = session.selectOne("tkEmpSelOne",    empno);
		Emp emp = empService. detailEmp(empno);
		model.addAttribute("emp", emp);
		
		return "detailEmp";
	}
	
	@GetMapping("updateFormEmp")
	public String updateFormEmp(int empno, Model model) {
		System.out.println("EmpController.updateFormEmp Start");
		Emp emp = empService.detailEmp(empno);
		System.out.println("EmpController.updateFormEmp emp -> " + emp);
		// 문제 
		// 1. DTO  String hiredate
		// 2.View : 단순조회 OK ,JSP에서 input type="date" 문제 발생
		// 3.해결책  : 년월일만 짤라 넣어 주어야 함
		String hitedate = "";
		if(emp.getHiredate() != null) {
			hitedate = emp.getHiredate().substring(0, 10);
			emp.setHiredate(hitedate);
		}
		model.addAttribute("emp", emp);
		
		return "updateFormEmp";
	}
	
	@PostMapping("updateEmp")
	public String updateEmp(Emp emp, Model model) {
		System.out.println("EmpController.updateEmp Start");
		// 1. EmpService안에 updateEmp method 선언
		// 1) parameter : Emp
		// 2) Return      updateCount (int)
		// 2. EmpDao updateEmp method 선언
		//       		             	    mapper ID, Parameter
		// updateCount = session.update("tkEmpUpdate",emp);
		int updateCount = empService.updateEmp(emp);
		System.out.println("EmpController.updateEmp updateCount -> " + updateCount);
		model.addAttribute("updateCount", updateCount);
		model.addAttribute("Message", "Message Test");
		
		return "forward:listEmp";
	}
	
	@RequestMapping("writeFormEmp")
	public String writeFormEmp(Model model) {
		System.out.println("EmpController.updateEmp Start");
		// 관리자 사번 만 Get
		List<Emp> empList = empService.listManager();
		System.out.println("EmpController.updateEmp empList.size() -> " + empList.size());
		model.addAttribute("empList", empList);
		return "writeFromEmp";
	}
}
