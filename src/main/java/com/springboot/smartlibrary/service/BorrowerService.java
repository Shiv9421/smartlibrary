package com.springboot.smartlibrary.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.smartlibrary.model.Book;
import com.springboot.smartlibrary.model.Borrower;
import com.springboot.smartlibrary.model.Member;
import com.springboot.smartlibrary.repository.BookRepository;
import com.springboot.smartlibrary.repository.BorrowerRepository;

@Service
public class BorrowerService implements IBorrowerService {

	@Autowired
	private BorrowerRepository borrowerRepository;
   
	@Autowired
	private BookRepository bookRepository;
   
	@Override
	public List<Borrower> getBorrowers() {
		return borrowerRepository.findAll();
	}
	@Override
	public List<Borrower> getActiveBorrowers() {
		List<Borrower> borrowers =  borrowerRepository.findAll();
		List<Borrower> activeBorrowers = new ArrayList<>();
		for (Borrower a: borrowers) {
			System.out.println(a.getReturned());
			if (a.getReturned().equals("No")) {
				activeBorrowers.add(a);
			}
		}
		return activeBorrowers;
	}

	@Override
	public Optional<Borrower> getBorrowerById(long id) {
		return borrowerRepository.findById(id);
	}
	
	

	@Override
	public void updateBorrower(Borrower borrower) {
		borrower.setDueDate(DateUtils.addMonths(borrower.getBorrowedDate(), 1));
		borrowerRepository.save(borrower);
	}

	@Override
	public void addBorrower(Borrower borrower) {
		borrower.setDueDate(DateUtils.addMonths(borrower.getBorrowedDate(), 1));
		borrowerRepository.save(borrower);
	}

	@Override
	public void deleteBorrower(long id) {
		Optional<Borrower> borrower = borrowerRepository.findById(id);
		if (borrower.isPresent()) {
			borrowerRepository.delete(borrower.get());
		}
	}

	@Override
	public void saveBorrower(Borrower borrower) {
		borrower.setDueDate(DateUtils.addMonths(borrower.getBorrowedDate(), 1));
		borrower.setReturned("No");
		borrowerRepository.save(borrower);
	}

	@Override
	public List<Borrower> getBorrowersByBookName(String bookName) {
		List<Borrower> borrowers = borrowerRepository.findAll();
		List<Borrower> borrowersByBookName = new ArrayList<>();
		for (Borrower b : borrowers) {
			if (b.getBookName().equals(bookName)) {
				borrowersByBookName.add(b);
			}
		}
		return borrowersByBookName;
	}
	

	@Override
	public List<Borrower> getBorrowersByMemberName(String member) {
		List<Borrower> borrowers = borrowerRepository.findAll();
		List<Borrower> borrowersByMemberName = new ArrayList<>();
		for (Borrower b : borrowers) {
			if (b.getMemberName().equals(member)) {
				borrowersByMemberName.add(b);
			}
		}
		return borrowersByMemberName;
	}
	
	@Override
	public void returnBorrower(Borrower borrower) {
		Date date = new Date();
		borrower.setReturned("Returned on: " + date);
		List<Book> books = bookRepository.findAll();
		for (Book b: books) {
			if (b.getBookName().equals(borrower.getBookName())){
				b.setAvailableQuantity(b.getAvailableQuantity()+1);
				bookRepository.save(b);
				break;
			}
		}
		borrowerRepository.save(borrower);
	}
	@Override
	public void renewBorrowedBook(Borrower borrower) {
		Date date = new Date();
		borrower.setBorrowedDate(date);
		borrower.setDueDate(DateUtils.addMonths(date, 1));
		borrowerRepository.save(borrower);
	}
}