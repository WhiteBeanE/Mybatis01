package com.oracle.oBootMybatis01.controller;

import java.util.HashMap;
import java.util.List;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.DeptVO;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;
import com.oracle.oBootMybatis01.model.Member1;
import com.oracle.oBootMybatis01.service.EmpService;
import com.oracle.oBootMybatis01.service.Paging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class EmpController {
	private final EmpService empService;
	// 메일 보내주는 객체
	private final JavaMailSender mailSender;
	
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
		System.out.println("EmpController.empList currentPage -> " + currentPage);
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
		model.addAttribute("empMngList", empList);
		
		// 부서(코드, 부서명)
		List<Dept> deptList = empService.deptSelect();
		model.addAttribute("deptList", deptList);
		System.out.println("EmpController.updateEmp deptList.size() -> " + deptList.size());
		
		return "writeFromEmp";
	}
	
	@PostMapping("writeEmp")
	public String writeEmp(Emp emp, Model model) {
		System.out.println("EmpController.writeEmp Start");
		
		// Service, Dao, Mapper명[insertEmp] 까지 insert
		int insertResult = empService.insertEmp(emp);
		if(insertResult > 0 ) return "redirect:listEmp";
		else {
			model.addAttribute("msg", "입력 실패 확인해 보세요");
			return "forward:writeFormEmp";
		}
	}
	
	// Validation시 참조하세요
	@PostMapping("writeEmp3")
	public String writeEmp3(@ModelAttribute("emp") @Valid Emp emp, 
								BindingResult result, Model model) {
		System.out.println("EmpController.writeEmp3 Start");
		
		// Validation 오류 시 Result
		if(result.hasErrors()) {
			System.out.println("EmpController.writeEmp3 hassError");
			model.addAttribute("msg", "BindingResult 입력 실패 확인해 주세요");
			return "forward:writeFormEmp";
		}
		int insertResult = empService.insertEmp(emp);
		if(insertResult > 0 ) return "redirect:listEmp";
		else {
			model.addAttribute("msg", "입력 실패 확인해 보세요");
			return "forward:writeFormEmp";
		}
	}
	
	@GetMapping("confirm")
	public String confirm(int empno, Model model) {
		Emp emp = empService.detailEmp(empno);
		model.addAttribute("empno", empno);
		if(emp != null) {
			System.out.println("EmpController.confirm 중복된 사번");
			model.addAttribute("msg", "중복된 사번입니다.");
			return "forward:writeFormEmp";
			
		}else {
			System.out.println("EmpController.confirm 사용 가능한 사번");
			model.addAttribute("msg", "사용 가능한 사번입니다.");
			return "forward:writeFormEmp";

		}
	}
	
	@GetMapping("deleteEmp")
	public String deleteEmp(int empno, Model model) {
		System.out.println("EmpController.deleteEmp Start");
		int result = empService.deleteEmp(empno);
		
		return "redirect:listEmp";
		
	}
	
	@GetMapping("listSearch3")
	public String listSearch3(Emp emp, String currentPage, Model model) {
		System.out.println("EmpController.listSearch3 Start");
		// Emp 전체 Count
		int totalEmp = empService.totalEmp();
		
		if(!emp.getKeyword().equals("")) {
			totalEmp = empService.searchCount(emp);
			System.out.println("EmpController.listSearch3 Keyword totalEmp -> " + totalEmp);
			
		}

		System.out.println("EmpController.listSearch3 currentPage -> " + currentPage);
		System.out.println("EmpController.listSearch3 totalEmp -> " + totalEmp);
		// Paging 작업
		Paging page = new Paging(totalEmp, currentPage);
		// Paameter emp -> Page만 추가 Setting
		emp.setStart(page.getStart());
		emp.setEnd(page.getEnd());
		System.out.println("EmpController.listSearch3 page.getStart() -> " + page.getStart() + " page.getEnd() -> " + page.getEnd());
		List<Emp> listSearchEmp = empService.listSearchEmp(emp);
		model.addAttribute("totalEmp",totalEmp);
		model.addAttribute("listEmp",listSearchEmp);
		model.addAttribute("page",page);
		return "list";
	}
	
	@GetMapping("/listEmpDept")
	public String listEmpDept(Model model) {
		System.out.println("EmpController.listEmpDept Start");
		// Service, Dao(ed) -> listEmpDept
		// Mapper만 -> tkListEmpDept
		List<EmpDept> listEmpDept = empService.listEmpDept();
		model.addAttribute("listEmpDept", listEmpDept);
		
		return "listEmpDept";
		
	}
	
	@GetMapping("/mailTransport")
	public String mailTransport(HttpServletRequest request, Model model) {
		System.out.println("EmpController.mailTransport Start");
		String tomail = "hbsm0104@naver.com";	// 받는 사람 이메일
		System.out.println(tomail);
		String setForm = "gbsm0121@gmail.com";
		String title = "mailTransport 입니다.";	// 제목
		
		try {
			// Mime 전자우편 Internet 표준 Format
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			messageHelper.setFrom(setForm);		// 보내는 사람 생략하거나 하면 정상작동을 안함
			messageHelper.setTo(tomail);		// 받는사람 이메일
			messageHelper.setSubject(title);	// 메일 제목 생략 가능
			String tempPassword = (int)(Math.random() * 999999) +1 +"";
			messageHelper.setText("임시 비밀번호 입니다 : " + tempPassword); // 메일 내용
			System.out.println("임시 비밀번호 입니다 : " + tempPassword);
			DataSource dataSource = new FileDataSource("c:\\log\\2.gif");
			messageHelper.addAttachment(MimeUtility.encodeText("hwa3.png", "UTF-8", "B"), dataSource);
			mailSender.send(message);
			model.addAttribute("check", 1); // 정상 전달
			// DB tempPassword Logic 구성
		} catch (Exception e) {
			System.out.println("EmpController. mailTransport e.getMessage() ->" + e.getMessage());
			model.addAttribute("check", 2); // 전달 실패
		}
		
		return "mailResult";
	}
	
	// Procedure Test 입력화면
	@GetMapping("/writeDeptIn")
	public String writeDeptIn(Model model) {
		System.out.println("EmpController.writeDeptIn Start");
		
		//List<Dept> listDept = empService.deptSelect();
		//model.addAttribute("listDept", listDept);
		return "writeDept3";
	}
	
	// Procedure 통한 Dept 입력후 VO전달
	@PostMapping("/writeDept")
	public String writeDept(DeptVO deptVO, Model model) {
		System.out.println("EmpController.writeDept Start");
		empService.insertDept(deptVO);
		List<Dept> listDept = empService.deptSelect();
		System.out.println("EmpController.writeDept listDept.size() -> " + listDept.size());
		if(deptVO == null) {
			System.out.println("deptVO NULL");
		}else {
			System.out.println("deptVO.getOdeptno() -> " + deptVO.getOdeptno());
			System.out.println("deptVO.getOdname() -> " + deptVO.getOdname());
			System.out.println("deptVO.getOloc() -> " + deptVO.getOloc());
			model.addAttribute("msg", "정상 입력 되었습니다.");
			model.addAttribute("dept", deptVO);
			model.addAttribute("listDept", listDept);
		}
		
		return "writeDept3";
	}
	
	// Map 전송
	@GetMapping("/writeDeptCursor")
	public String writeDeptCursor(Model model) {
		System.out.println("EmpController.writeDeptCursor Start");
		// 부서범위 조회
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sDeptno", 10);
		map.put("eDeptno", 55);
		
		empService.selListDept(map);
		List<Dept> deptList = (List<Dept>) map.get("dept");
		for(Dept dept : deptList) {
			System.out.println("dept.getDname() -> " + dept.getDname());
			System.out.println("dept.dept.getLoc() -> " + dept.getLoc());
		}
		System.out.println("deptList.size() -> " + deptList.size());
		model.addAttribute("deptList", deptList);
		
		return "writeDeptCursor";
	}
	
	// interCeptor 시작 화면
	@GetMapping("/interCeptorForm")
	public String interCeptorForm(Model model) {
		System.out.println("EmpController.interCeptorForm Start");
		
		return "interCeptorForm";
	}
	
	// interCeptor Number 2
	@GetMapping("/interCeptor")
	public String interCeptor(String id, Model model) {
		System.out.println("EmpController.interCeptor Start");
		System.out.println("EmpController.interCeptor id -> " + id);
		// 존재 : 1, 비존재 : 0
		int memCnt = empService.memCount(id);
		System.out.println("EmpController.interCeptor memCnt -> " + memCnt);
		
		model.addAttribute("id", id);
		model.addAttribute("memCnt", memCnt);
		
		return "interCeptor";	// User 존재하면 User 이용 조회 Page
		// 이건 안쓰긴 하는데 꼭 적어야함?
	}
	
	// SampleInterceptor 내용을 받아 처리
	@GetMapping("doMemberWrite")
	public String doMemberWrite(Model model, HttpServletRequest request) {
		System.out.println("EmpController.doMemberWrite Start");
		String ID = (String)request.getSession().getAttribute("ID");
		System.out.println("doMemberWrite 부터 하세요.");
		model.addAttribute("id", ID);
		
		return "doMemberWrite";
	}
	// InterCeptor 진행 Test
	@GetMapping("doMemberList")
	public String doMemberList(Model model, HttpServletRequest request) {
		System.out.println("EmpController.doMemberList Start");
		String ID = (String)request.getSession().getAttribute("ID");
		System.out.println("EmpController.doMemberList ID -> " + ID);
		Member1 member1 = null;
		// Member1 List Get Service
		List<Member1> listMember = empService.listMember(member1);
		model.addAttribute("id", ID);
		model.addAttribute("listMember", listMember);
		
		return "doMemberList";	// User 존재하면 User 이용 조회 Page
	}
	
	@GetMapping("/ajaxForm")
	public String ajaxForm(Model model) {
		System.out.println("EmpController.ajaxForm Start");
		
		return "ajaxForm";
	}
	
	@GetMapping("/listEmpAjaxForm")
	public String listEmpAjaxForm(Model model) {
		Emp emp = new Emp();
		System.out.println("EmpController.listEmpAjaxForm Start");
		// Parameter emp -> Page만 추가 Setting
		emp.setStart(1);
		emp.setEnd(10);
		
		List<Emp> listEmp = empService.listEmp(emp);
		System.out.println("EmpController.listEmpAjaxForm lisEmp.size() -> " + listEmp.size());
		model.addAttribute("resutl", "White");
		model.addAttribute("listEmp", listEmp);
		
		return "listEmpAjaxForm";
	}
	
	@GetMapping("/listEmpAjaxForm2")
	public String listEmpAjaxForm2(Model model) {
		Emp emp = new Emp();
		System.out.println("EmpController.listEmpAjaxForm2 Start");
		
		// Parameter emp -> Page만 추가 Setting
		emp.setStart(1);
		emp.setEnd(5);
		
		List<Emp> listEmp = empService.listEmp(emp);
		System.out.println("EmpController.listEmpAjaxForm lisEmp.size() -> " + listEmp.size());
		model.addAttribute("listEmp", listEmp);
		
		return "listEmpAjaxForm2";
	}
	
	
}
