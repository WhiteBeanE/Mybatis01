package com.oracle.oBootMybatis01.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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

}
