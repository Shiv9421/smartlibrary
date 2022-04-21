package com.springboot.smartlibrary.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.smartlibrary.model.Borrower;
import com.springboot.smartlibrary.model.Member;
import com.springboot.smartlibrary.service.IBorrowerService;
import com.springboot.smartlibrary.service.IMemberService;



@Controller
public class MemberController {

	@Autowired
	private IMemberService memberService;

	@Autowired
	private IBorrowerService borrowerService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/list-members", method = RequestMethod.GET)
	public String showMembers(ModelMap model) {
		
		model.put("members", memberService.getMembers());
		
		return "list-members";
	}

	

	@RequestMapping(value = "/add-member", method = RequestMethod.GET)
	public String displayMemberPage(ModelMap model) {
		model.addAttribute("member", new Member());
		return "member";
	}

	@RequestMapping(value = "/delete-member", method = RequestMethod.GET)
	public String deleteMember(@RequestParam long id) {
		memberService.deleteMember(id);
		// service.deleteTodo(id);
		return "redirect:/list-members";
	}

	@RequestMapping(value = "/update-member", method = RequestMethod.GET)
	public String displayMemberUpdate(@RequestParam long id, ModelMap model) {
		Member member = memberService.getMemberById(id).get();
		model.put("member", member);
		return "member";
	}

	@RequestMapping(value = "/update-member", method = RequestMethod.POST)
	public String editMember(ModelMap model, @Valid Member member, BindingResult result) {

		if (result.hasErrors()) {
			return "member";
		}

		
		memberService.updateMember(member);
		return "redirect:/list-members";
	}

	@RequestMapping(value = "/add-member", method = RequestMethod.POST)
	public String addMember(ModelMap model, @Valid Member member, BindingResult result) {

		if (result.hasErrors()) {
			return "member";
		}

		
		memberService.saveMember(member);
		return "redirect:/list-members";
	}
	

	@RequestMapping(value = "/get-books", method = RequestMethod.GET)
	public String getAppointments(@RequestParam long id, ModelMap model) {
		Member member = memberService.getMemberById(id).get();
		List<Borrower> borrowers = borrowerService.getActiveBorrowers();
		List<Borrower> activeBorrowers = new ArrayList<>();
		for (Borrower b : borrowers) {
			if (b.getMemberName().equals(member.getMemberName())){
				activeBorrowers.add(b);
			}
		}
		System.out.println(activeBorrowers);
		model.put("borrowers", activeBorrowers);
		// service.deleteTodo(id);
		return "list-borrowers";
	}
	
}
