package com.oracle.oBootMybatis01.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis01.domain.Member;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberJpaRepositoryImpl implements MemberJpaRepository {
	
	private final EntityManager entityManager;
	
	@Override
	public Member save(Member member) {
		System.out.println("MemberJpaRepositoryImpl.save Start");
		entityManager.persist(member);
		
		return member;
	}

	@Override
	public List<Member> findAll() {
		System.out.println("MemberJpaRepositoryImpl.findAll Start");
		List<Member> memberList = entityManager.createQuery("SELECT m FROM Member m", Member.class).getResultList();
		
		return memberList;
	}

	@Override
	public Optional<Member> findById(Long memberId) {
		System.out.println("MemberJpaRepositoryImpl.findById Start");
		Member member = entityManager.find(Member.class, memberId);
		return Optional.ofNullable(member);
	}

	@Override
	public void updateByMember(Member member) {
		System.out.println("MemberJpaRepositoryImpl.updateByMember Start");
		entityManager.merge(member);

	}

}
