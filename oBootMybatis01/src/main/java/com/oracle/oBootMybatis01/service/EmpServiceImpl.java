package com.oracle.oBootMybatis01.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis01.dao.DeptDao;
import com.oracle.oBootMybatis01.dao.EmpDao;
import com.oracle.oBootMybatis01.dao.Member1Dao;
import com.oracle.oBootMybatis01.model.Dept;
import com.oracle.oBootMybatis01.model.DeptVO;
import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;
import com.oracle.oBootMybatis01.model.Member1;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {
	private final EmpDao empDao;
	private final DeptDao deptDao;
	private final Member1Dao member1Dao;
	
	@Override
	public int totalEmp() {
		System.out.println("EmpServiceImpl.totalEmp() Start");
		int totEmpCnt = empDao.totalEmp();
		System.out.println("EmpServiceImpl.totalEmp() totEmpCnt -> " + totEmpCnt);
		return totEmpCnt;
	}
	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpServiceImpl.listEmp Start");
		empList = empDao.listEmp(emp);
		System.out.println("EmpServiceImpl.listEmp empList.size() -> " + empList.size());
		return empList;
	}
	@Override
	public Emp detailEmp(int empno) {
//		2. EmpDao   detailEmp method 선언 
//	    						mapper ID   ,    Parameter
//		emp = session.selectOne("tkEmpSelOne",    empno);
		System.out.println("EmpServiceImpl.detailEmp Start");
		Emp emp = empDao.detailEmp(empno);
		return emp;
	}
	@Override
	public int updateEmp(Emp emp) {
		System.out.println("EmpServiceImpl.detailEmp Start");
		// 2. EmpDao updateEmp method 선언
		//       		             	    mapper ID, Parameter
		// updateCount = session.update("tkEmpUpdate",emp);
		int updateCount = empDao.updateEmp(emp);
		
		return updateCount;
	}
	@Override
	public List<Emp> listManager() {
		System.out.println("EmpServiceImpl.listManager Start");
		List<Emp> empList = empDao.listManager();
		System.out.println("EmpServiceImpl.listManager empList.size() -> " + empList.size());
		
		return empList;
	}
	@Override
	public List<Dept> deptSelect() {
		System.out.println("EmpServiceImpl.deptSelect Start");
		List<Dept> deptList = deptDao.deptSelect();
		System.out.println("EmpServiceImpl.deptSelect deptList.size() -> " + deptList.size());
		return deptList;
	}
	@Override
	public int insertEmp(Emp emp) {
		System.out.println("EmpServiceImpl.insertEmp Start");
		int insertResult = empDao.insertEmp(emp);
		System.out.println("EmpServiceImpl.insertEmp insertResult -> " + insertResult);
		return insertResult;
	}
	@Override
	public int deleteEmp(int empno) {
		System.out.println("EmpServiceImpl.deleteEmp Start");
		int result = empDao.deleteEmp(empno);
		System.out.println("EmpServiceImpl.insertEmp deleteResult -> " + result);
		return result;
	}
	@Override
	public List<Emp> listSearchEmp(Emp emp) {
		System.out.println("EmpServiceImpl.listSearchEmp Start");
		List<Emp> empSearchList = empDao.empSearchList(emp);
		System.out.println("EmpServiceImpl.listSearchEmp empSearchList.size() -> " + empSearchList.size());
		return empSearchList;
	}
	@Override
	public int searchCount(Emp emp) {
		System.out.println("EmpServiceImpl.listSearchEmp Start");
		int searchCount = empDao.empSearchCount(emp);
		System.out.println("EmpServiceImpl.listSearchEmp searchCount -> " + searchCount);
		return searchCount;
	}
	@Override
	public List<EmpDept> listEmpDept() {
		System.out.println("EmpServiceImpl.listEmpDept Start");
		List<EmpDept> listEmpDept = empDao.listEmpDept();
		System.out.println("EmpServiceImpl.listSearchEmp listEmpDept.size() -> " + listEmpDept.size());
		return listEmpDept;
	}
	@Override
	public void insertDept(DeptVO deptVO) {
		System.out.println("EmpServiceImpl.insertDept Start");
		deptDao.insertDept(deptVO);
		
	}
	@Override
	public void selListDept(HashMap<String, Object> map) {
		System.out.println("EmpServiceImpl.selListDept Start");
		deptDao.selListDept(map);	
	}
	@Override
	public int memCount(String id) {
		System.out.println("EmpServiceImpl.memCount Start");
		int memCnt = member1Dao.memCount(id);
		System.out.println("EmpServiceImpl.memCount memCnt -> " + memCnt);
		
		return memCnt;
	}
	@Override
	public List<Member1> listMember(Member1 member1) {
		System.out.println("EmpServiceImpl.listMember Start");
		List<Member1> listMember = member1Dao.listMember(member1);
		System.out.println("EmpServiceImpl.listMember listMember.size() -> " + listMember.size());
		return listMember;
	}
	@Override
	public String deptName(int deptno) {
		System.out.println("EmpServiceImpl.deptName Start");
		return empDao.deptName(deptno);
	}
	@Override
	public int empDelete(Emp emp) {
		System.out.println("EmpServiceImpl.empDelete Start");
		return empDao.empDelete(emp);
	}

}
