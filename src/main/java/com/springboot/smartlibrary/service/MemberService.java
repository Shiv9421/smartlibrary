package com.springboot.smartlibrary.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.smartlibrary.model.Member;
import com.springboot.smartlibrary.repository.MemberRepository;


@Service
public class MemberService implements IMemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Override
	public List<Member> getMembers() {
		
		return memberRepository.findAll();
	}

	@Override
	public Optional<Member> getMemberById(long id) {
		return memberRepository.findById(id);
		
	}

	
	@Override
	public void updateMember(Member member) {
		memberRepository.save(member);
	}

	@Override
	public void addMember(Member member) {
		memberRepository.save(member);
	}

	@Override
	public void deleteMember(long id) {
		Optional<Member> member = memberRepository.findById(id);
		if (member.isPresent()) {
			memberRepository.delete(member.get());
		}
	}

	@Override
	public void saveMember(Member member) {
		memberRepository.save(member);
	}

	
}