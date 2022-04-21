package com.springboot.smartlibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.smartlibrary.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	List<Member> findAll();
}
