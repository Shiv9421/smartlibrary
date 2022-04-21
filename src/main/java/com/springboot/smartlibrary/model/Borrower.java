package com.springboot.smartlibrary.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;


@Entity
@Table(name="borrowers")
@DynamicUpdate
public class Borrower {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String bookName;
	
	private String memberName;
	
	private Date borrowedDate;

	private Date dueDate;
	@Column(columnDefinition = "varchar(255) default 'No'", nullable = false)
	private String returned;

	public Borrower(long id, String bookName, String memberName, Date borrowedDate, Date dueDate, String returned) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.memberName = memberName;
		this.borrowedDate = borrowedDate;
		this.dueDate = dueDate;
		this.returned = returned;
	}

	public Borrower() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Borrow [id=" + id + ", bookName=" + bookName + ", memberName=" + memberName + ", borrowedDate="
				+ borrowedDate + ", dueDate=" + dueDate + ", returned=" + returned + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Date getBorrowedDate() {
		return borrowedDate;
	}

	public void setBorrowedDate(Date borrowedDate) {
		this.borrowedDate = borrowedDate;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getReturned() {
		return returned;
	}

	public void setReturned(String returned) {
		this.returned = returned;
	}
	
	

	
}
