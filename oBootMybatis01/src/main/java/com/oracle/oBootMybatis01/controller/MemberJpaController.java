package com.oracle.oBootMybatis01.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootMybatis01.domain.Member;
import com.oracle.oBootMybatis01.service.MemberJpaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberJpaController {
	private final MemberJpaService memberJpaService;
	
	@GetMapping("/memberJpa/new")
	public String createForm(Member member) {
		System.out.println("MemberJpaService.createForm() Start");
		
		return "memberJpa/createMemberForm";
	}
	@PostMapping("/memberJpa/save")
	public String create(Member member) {
		System.out.println("MemberJpaService.create() Start");
		System.out.println("MemberJpaService.create() member.getId() -> " + member.getId());
		System.out.println("MemberJpaService.create() member.getName() -> " + member.getName());
		memberJpaService.join(member);
		
		return "memberJpa/createMemberForm";
	}
	@GetMapping("/members")
	public String listMember(Model model) {
		System.out.println("MemberJpaService.create() Start");
		List<Member> memberList = memberJpaService.getListAllMember();
		model.addAttribute("members", memberList);
		
		return "memberJpa/memberList";
	}
	@GetMapping("/memberJpa/memberUpdateForm")
	public String memberUpdateForm(Long id, Model model) {
		Member member = null;
		String rtnJsp = "";
		System.out.println("MemberJpaService.memberUpdateForm() id -> " + id);
		// 목적 : 객체가 NULL Check 용이
		Optional<Member> maybeMember = memberJpaService.findById(id);
		if(maybeMember.isPresent()) {
			System.out.println("MemberJpaService.memberUpdateForm() maybeMember IS NOT NULL");
			member = maybeMember.get();
			model.addAttribute("member", member);
			rtnJsp = "memberJpa/memberModify";
		}else {
			System.out.println("MemberJpaService.memberUpdateForm() maybeMember IS NULL");
			member = maybeMember.get();
			model.addAttribute("message", "member가 존재하지 않습니다.");
			rtnJsp = "forward:/members";
			
		}
		return rtnJsp;
	}
	@GetMapping("/memberJpa/memberUpdate")
	public String memberUpdate(Member member, Model model) {
		System.out.println("MemberJpaService.memberUpdate() Start");
		System.out.println("MemberJpaService.memberUpdate() member.getId() -> " + member.getId());
		System.out.println("MemberJpaService.memberUpdate() member.getName() -> " + member.getName());
		memberJpaService.memberUpdate(member);
		
		return "redirect:/members";
	}
}
