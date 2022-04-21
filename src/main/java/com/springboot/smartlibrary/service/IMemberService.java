package com.springboot.smartlibrary.service;

import java.util.Date;

import java.util.List;
import java.util.Optional;

import com.springboot.smartlibrary.model.Member;

public interface IMemberService {

	List<Member> getMembers();
	
	Optional<Member> getMemberById(long id);

	void updateMember(Member member);

	void addMember(Member member);

	void deleteMember(long id);
	
	void saveMember(Member member);

}