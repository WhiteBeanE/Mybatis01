package com.oracle.oBootMybatis01.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootMybatis01.domain.Member;
import com.oracle.oBootMybatis01.repository.MemberJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberJpaService {
	private final MemberJpaRepository memberJpaRepository;
	
	// 회원가입
	public Long join(Member member) {
		System.out.println("MemberJpaService.join Start");
		memberJpaRepository.save(member);
		System.out.println("MemberJpaService.join member.getId() -> " + member.getId());
		
		return member.getId();
	}
	// 전체 회원 조회
	public List<Member> getListAllMember() {
		System.out.println("MemberJpaService.getListAllMember Start");
		List<Member> listMember = memberJpaRepository.findAll();
		System.out.println("MemberJpaService.getListAllMember listMember.size() -> " + listMember.size());
		
		return listMember;
	}
	
	public Optional<Member> findById(Long id){
		System.out.println("MemberJpaService.findById Start");
		Optional<Member> member = memberJpaRepository.findById(id);
		
		return member;
	}
	public void memberUpdate(Member member) {
		System.out.println("MemberJpaService.memberUpdate Start");
		System.out.println("MemberJpaService.memberUpdate member.getId() -> " + member.getId());
		System.out.println("MemberJpaService.memberUpdate member.getName() -> " + member.getName());
		memberJpaRepository.updateByMember(member);
	}
}
