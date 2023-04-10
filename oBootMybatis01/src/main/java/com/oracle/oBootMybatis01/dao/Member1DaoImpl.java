package com.oracle.oBootMybatis01.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.model.Member1;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class Member1DaoImpl implements Member1Dao {
	private final SqlSession session;
	@Override
	public int memCount(String id) {
		int result = 0;
		System.out.println("Member1DaoImpl.memCount Start");
		try {
			result = session.selectOne("memCount", id);
		} catch (Exception e) {
			System.out.println("Member1DaoImpl.memCount e.getMessage() -> " + e.getMessage());
		}
		return result;
	}
	@Override
	public List<Member1> listMember(Member1 member1) {
		System.out.println("Member1DaoImpl.listMember Start");
		List<Member1> listMember = null;
		try {
			listMember = session.selectList("listMember1", member1);
		} catch (Exception e) {
			System.out.println("Member1DaoImpl.listMember e.getMessage() -> " + e.getMessage());
		}
		return listMember;
	}

}
