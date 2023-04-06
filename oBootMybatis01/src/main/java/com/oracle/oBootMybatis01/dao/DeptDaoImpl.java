package com.oracle.oBootMybatis01.dao;

import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.model.Dept;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DeptDaoImpl implements DeptDao {
	// Mybatis DB연동
	private final SqlSession session;
	@Override
	public List<Dept> deptSelect() {
		System.out.println("DeptDaoImpl.deptSelect Start");
		List<Dept> deptList = session.selectList("tkSelectDept");
		System.out.println("DeptDaoImpl.deptSelect deptList.size() -> " + deptList.size());

		return deptList;
	}

}
