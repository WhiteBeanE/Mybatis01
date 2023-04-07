package com.oracle.oBootMybatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.model.Emp;
import com.oracle.oBootMybatis01.model.EmpDept;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmpDaoImpl implements EmpDao {
	// Mybatis DB 연동 객체
	private final SqlSession session;
	
	@Override
	public int totalEmp() {
		int totEmpCount = 0;
		System.out.println("EmpDaoImpl.totalEmp() Start");
		
		try {
			totEmpCount = session.selectOne("empTotal");
			System.out.println("EmpDaoImpl.totalEmp() totEmpCount -> " + totEmpCount);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl.totalEmp() e.getMessage() -> " + e.getMessage());
		}
		return totEmpCount;
	}

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpDaoImpl.listEmp Start");
		try {							   // Map ID	Parameter
			empList = session.selectList("tkEmpListAll", emp);
			System.out.println("EmpDaoImpl.listEmp empList.size() -> " + empList.size());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl.listEmp() e.getMessage() -> " + e.getMessage());
		}
		
		return empList;
	}

	@Override
	public Emp detailEmp(int empno) {
		//						mapper ID   ,    Parameter
		//emp = session.selectOne("tkEmpSelOne",    empno);
		Emp emp = null;
		System.out.println("EmpDaoImpl.detailEmp Start");
		try {
			emp = session.selectOne("tkEmpSelOne", empno);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl.detailEmp() e.getMessage() -> " + e.getMessage());
		}
		return emp;
	}

	@Override
	public int updateEmp(Emp emp) {
		System.out.println("EmpDaoImpl.detailEmp Start");
		int updateCount = 0;
		//	    							mapper ID, Parameter
		// updateCount = session.update("tkEmpUpdate",emp);
		try {
			updateCount = session.update("tkEmpUpdate",emp);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl.updateEmp() e.getMessage() -> " + e.getMessage());
		}
		
		return updateCount;
	}

	@Override
	public List<Emp> listManager() {
		System.out.println("EmpDaoImpl.listManager Start");
		List<Emp> empList = null;
		try {
			empList = session.selectList("tkSelectManager");
		} catch (Exception e) {
			System.out.println("EmpDaoImpl.updateEmp() e.getMessage() -> " + e.getMessage());
		}
		return empList;
	}

	@Override
	public int insertEmp(Emp emp) {
		System.out.println("EmpDaoImpl.insertEmp Start");
		int insertResult = 0;
		try {
			insertResult = session.insert("tkInsertEmp", emp);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl.insertEmp() e.getMessage() -> " + e.getMessage());
		}
		return insertResult;
	}

	@Override
	public int deleteEmp(int empno) {
		System.out.println("EmpDaoImpl.insertEmp Start");
		int deleteResult = 0;
		try {
			deleteResult = session.delete("tkDeleteEmp", empno);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl.insertEmp() e.getMessage() -> " + e.getMessage());
		}
		return deleteResult;
	}

	@Override
	public List<Emp> empSearchList(Emp emp) {
		System.out.println("EmpDaoImpl.empSearchList Start");
		List<Emp> empSearchList = null;
		try {
			empSearchList = session.selectList("tkEmpSearchList", emp);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl.empSearchList() e.getMessage() -> " + e.getMessage());
		}
		return empSearchList;
	}

	@Override
	public int empSearchCount(Emp emp) {
		System.out.println("EmpDaoImpl.empSearchCount Start");
		List<Emp> empSearchList = null;
		try {
			empSearchList = session.selectList("tkEmpSearchCount", emp);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl.empSearchCount() e.getMessage() -> " + e.getMessage());
		}
		return empSearchList.size();
	}

	@Override
	public List<EmpDept> listEmpDept() {
		System.out.println("EmpDaoImpl.empDeptList Start");
		List<EmpDept> listEmpDept = null;
		try {
			listEmpDept = session.selectList("tkListEmpDept");
		} catch (Exception e) {
			System.out.println("EmpDaoImpl.empDeptList() e.getMessage() -> " + e.getMessage());
		}
		return listEmpDept;
	}

}
