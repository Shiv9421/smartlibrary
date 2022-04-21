package com.springboot.smartlibrary.service;

import java.util.Date;

import java.util.List;
import java.util.Optional;

import com.springboot.smartlibrary.model.Borrower;

public interface IBorrowerService {

	List<Borrower> getBorrowers();
	
	List<Borrower> getActiveBorrowers();
	
	List<Borrower> getBorrowersByBookName(String bookName);

	Optional<Borrower> getBorrowerById(long id);

	void updateBorrower(Borrower borrower);

	void returnBorrower(Borrower borrower);
	
	void renewBorrowedBook(Borrower borrower);
	
	void addBorrower(Borrower borrower);

	void deleteBorrower(long id);
	
	void saveBorrower(Borrower borrower);

	List<Borrower> getBorrowersByMemberName(String member);

}