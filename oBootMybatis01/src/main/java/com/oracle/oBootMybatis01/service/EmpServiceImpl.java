package com.oracle.oBootMybatis01.service;

import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis01.dao.EmpDao;

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

}
