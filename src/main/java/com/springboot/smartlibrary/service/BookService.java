package com.springboot.smartlibrary.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.smartlibrary.model.Book;
import com.springboot.smartlibrary.repository.BookRepository;

@Service
public class BookService implements IBookService{
		
		@Autowired
		private BookRepository bookRepo;
		
		
		@Override
		public List<Book> listAll(){
			List<Book> booksList = bookRepo.findAll();
			
			return booksList;
		}
		
		public List<Book> listAvailableBooks(){
			List<Book> booksList = bookRepo.findAll();
			List<Book> availableBooks = new ArrayList<>();
			for (Book b: booksList) {
				if (b.getAvailableQuantity() > 0) {
					availableBooks.add(b);
				}
			}
			return availableBooks;
		}
		
		@Override
		public void save(Book book) {
			bookRepo.save(book);
		}
		
		@Override
		public void deleteBook(long id) {
			bookRepo.deleteById(id);
		}
		
		@Override
		public Optional<Book> findById(long id) {
			return bookRepo.findById(id);
			
		}
		
		
		@Override
		public void updateBook(Book book) {
			bookRepo.save(book);
		}


		@Override
		public Book findByBookName(String bookName) {
			Book book = new Book();
			for (Book b: bookRepo.findAll()) {
				if (b.getBookName().equals(bookName)) {
					return  b;
				}
			}
			return book;
			
		}

		@Override
		public Book findByAuthorName(String authorName) {
			Book book = new Book();
			for (Book b: bookRepo.findAll()) {
				if (b.getBookAuthor().equals(authorName)) {
					return  b;
				}
			}
			return book;
			
		}

		@Override
		public int findAvailableQuantityByName(long id) {
			int availableQuantity = bookRepo.findById(id).get().getAvailableQuantity();
			return availableQuantity;
		}

		
}

