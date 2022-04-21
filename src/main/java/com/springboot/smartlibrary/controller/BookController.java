package com.springboot.smartlibrary.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.smartlibrary.model.Book;
import com.springboot.smartlibrary.model.Borrower;
import com.springboot.smartlibrary.service.IBookService;
import com.springboot.smartlibrary.service.IBorrowerService;


@Controller
public class BookController {

	@Autowired
	private IBookService bookService;
	@Autowired
	private IBorrowerService borrowService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/list-books", method = RequestMethod.GET)
	public String printBooks(ModelMap model) {
		
		model.put("books", bookService.listAll());
		// model.put("todos", service.retrieveTodos(name));
		return "list-books";
	}

	

	@RequestMapping(value = "/add-book", method = RequestMethod.GET)
	public String displayAddBook(ModelMap model) {
		System.out.println("adding book");
		model.addAttribute("book", new Book());
		return "book";
	}

	@RequestMapping(value = "/delete-book", method = RequestMethod.GET)
	public String deleteBook(@RequestParam long id) {
		bookService.deleteBook(id);
		// service.deleteTodo(id);
		return "redirect:/list-books";
	}

	@RequestMapping(value = "/update-book", method = RequestMethod.GET)
	public String displayUpdateBookPage(@RequestParam long id, ModelMap model) {
		Book book = bookService.findById(id).get();
		model.put("book", book);
		return "book";
	}

	@RequestMapping(value = "/update-book", method = RequestMethod.POST)
	public String updateBook(ModelMap model, @Valid Book book, BindingResult result) {

		if (result.hasErrors()) {
			return "book";
		}
		//if total quantity is getting updated
		//check if there are any active books issued
		//and then sub this value from the latest total to get available books
		int issuedBooks = borrowService.getBorrowersByBookName(book.getBookName()).size();
		int totalBooksCount = book.getTotalQuantity();
		book.setAvailableQuantity(totalBooksCount-issuedBooks);
		
		bookService.updateBook(book);
		return "redirect:/list-books";
	}

	@RequestMapping(value = "/add-book", method = RequestMethod.POST)
	public String addBook(ModelMap model, @Valid Book book, BindingResult result) {

		if (result.hasErrors()) {
			return "book";
		}
		//initially while adding a new book
		book.setAvailableQuantity(book.getTotalQuantity());
		bookService.save(book);
		return "redirect:/list-books";
	}
	
	@RequestMapping(value = "/check-availability", method = RequestMethod.GET)
	public String getStatus(@RequestParam long id, ModelMap model) {
		Book book = bookService.findById(id).get();
		List<Borrower> borrowers= borrowService.getActiveBorrowers();
		List<Borrower> activeBorrowers = new ArrayList<>();
		for (Borrower b: borrowers) {
			if (b.getBookName().equals(book.getBookName())) {
				activeBorrowers.add(b);
			}
		}
		
		System.out.println(activeBorrowers);
		model.put("borrowers", activeBorrowers);
		// service.deleteTodo(id);
		return "list-borrowers";
	}
	
	

	
}
