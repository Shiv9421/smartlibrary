package com.springboot.smartlibrary.controller;

import com.springboot.smartlibrary.model.Book;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import com.springboot.smartlibrary.model.Borrower;
import com.springboot.smartlibrary.service.IBookService;
import com.springboot.smartlibrary.service.IBorrowerService;
import com.springboot.smartlibrary.service.IMemberService;

@Controller
public class BorrowerController {

	@Autowired
	private IBorrowerService borrowerService;

	@Autowired
	private IBookService bookService;
	
	@Autowired
    private IMemberService memberService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/list-borrowers", method = RequestMethod.GET)
	public String displayBorrowers(ModelMap model) {
		
		model.put("borrowers", borrowerService.getActiveBorrowers());
		// model.put("todos", service.retrieveTodos(name));
		return "list-borrowers";
	}
	@RequestMapping(value = "/history-borrowers", method = RequestMethod.GET)
	public String displayHistory(ModelMap model) {
		
		model.put("borrowers", borrowerService.getBorrowers());
		// model.put("todos", service.retrieveTodos(name));
		return "hist-borrowers";
	}
	

	@RequestMapping(value = "/add-borrower", method = RequestMethod.GET)
	public String displayAddBorrowerPage(ModelMap model) {
		
		
        model.put("memberList", memberService.getMembers());
        model.put("bookList", bookService.listAvailableBooks());
        model.addAttribute("borrower", new Borrower());
		return "borrower";
	}

	@RequestMapping(value = "/delete-borrower", method = RequestMethod.GET)
	public String deleteBorrower(@RequestParam int id) {
		borrowerService.deleteBorrower(id);
		// service.deleteTodo(id);
		return "redirect:/list-borrowers";
	}

	@RequestMapping(value = "/return-borrower", method = RequestMethod.GET)
	public String returnBorrowedBook(@RequestParam int id) {
		borrowerService.returnBorrower(borrowerService.getBorrowerById(id).get());
		// service.deleteTodo(id);
		return "redirect:/list-borrowers";
	}

	@RequestMapping(value = "/renew-borrower", method = RequestMethod.GET)
	public String renewBorrowedBook(@RequestParam int id) {
		borrowerService.renewBorrowedBook(borrowerService.getBorrowerById(id).get());
		// service.deleteTodo(id);
		return "redirect:/list-borrowers";
	}

	@RequestMapping(value = "/add-borrower", method = RequestMethod.POST)
	public String addBorrower(ModelMap model, @Valid Borrower borrower, BindingResult result) {

		if (result.hasErrors()) {
			return "borrower";
		}
		//while issuing a book, available quantity should be reduced by 1
		Book book = bookService.findByBookName(borrower.getBookName());
		int prevAvailableQuantity = book.getAvailableQuantity();
		if (prevAvailableQuantity >0)
			book.setAvailableQuantity(prevAvailableQuantity-1);
		bookService.updateBook(book);
		
		borrowerService.saveBorrower(borrower);
		return "redirect:/list-borrowers";
	}
	
	@RequestMapping(value = "/update-borrower", method = RequestMethod.GET)
	public String displayUpdateBorrower(@RequestParam long id, ModelMap model) {
		System.out.println("id: " + id);
		Borrower borrower = borrowerService.getBorrowerById(id).get();
		System.out.println("borrower: " + borrower);
		model.put("borrower", borrower);
		return "borrower";
	}

	@RequestMapping(value = "/update-borrower", method = RequestMethod.POST)
	public String updateBorrower(ModelMap model, @Valid Borrower borrower, BindingResult result) {

		if (result.hasErrors()) {
			return "borrower";
		}

		
		borrowerService.updateBorrower(borrower);
		return "redirect:/list-borrowers";
	}


}
