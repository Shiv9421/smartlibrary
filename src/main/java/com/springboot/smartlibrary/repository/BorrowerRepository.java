package com.springboot.smartlibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.smartlibrary.model.Borrower;

public interface BorrowerRepository extends JpaRepository<Borrower, Long>{
	List<Borrower> findAll();
}
