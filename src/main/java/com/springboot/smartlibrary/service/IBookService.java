package com.springboot.smartlibrary.service;

import java.util.Date;

import java.util.List;
import java.util.Optional;

import com.springboot.smartlibrary.model.Book;

public interface IBookService {
	public List<Book> listAll();
	
	public List<Book> listAvailableBooks();
	
	public void save(Book book) ;
	
	public void deleteBook(long id);
	
	
	public Optional<Book> findById(long id);
	
	public Book findByBookName(String bookName);
	
	public Book findByAuthorName(String authorName);
	
	public int findAvailableQuantityByName(long id);

	void updateBook(Book book);
}