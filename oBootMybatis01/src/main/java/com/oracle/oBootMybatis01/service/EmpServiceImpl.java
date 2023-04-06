package com.oracle.oBootMybatis01.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis01.dao.EmpDao;
import com.oracle.oBootMybatis01.model.Emp;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {
	private final EmpDao emaDao;
	@Override
	public int totalEmp() {
		System.out.println("EmpServiceImpl.totalEmp() Start");
		int totEmpCnt = emaDao.totalEmp();
		System.out.println("EmpServiceImpl.totalEmp() totEmpCnt -> " + totEmpCnt);
		return totEmpCnt;
	}
	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpServiceImpl.listEmp Start");
		empList = emaDao.listEmp(emp);
		System.out.println("EmpServiceImpl.listEmp empList.size() -> " + empList.size());
		return empList;
	}
	@Override
	public Emp detailEmp(int empno) {
//		2. EmpDao   detailEmp method 선언 
//	    						mapper ID   ,    Parameter
//		emp = session.selectOne("tkEmpSelOne",    empno);
		System.out.println("EmpServiceImpl.detailEmp Start");
		Emp emp = emaDao.detailEmp(empno);
		return emp;
	}
	@Override
	public int updateEmp(Emp emp) {
		System.out.println("EmpServiceImpl.detailEmp Start");
		// 2. EmpDao updateEmp method 선언
		//       		             	    mapper ID, Parameter
		// updateCount = session.update("tkEmpUpdate",emp);
		int updateCount = emaDao.updateEmp(emp);
		
		return updateCount;
	}
	@Override
	public List<Emp> listManager() {
		System.out.println("EmpServiceImpl.listManager Start");
		List<Emp> empList = emaDao.listManager();
		System.out.println("EmpServiceImpl.listManager empList.size() -> " + empList.size());
		
		return empList;
	}

}
