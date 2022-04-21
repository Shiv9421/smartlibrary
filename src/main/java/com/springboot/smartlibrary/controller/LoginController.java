package com.springboot.smartlibrary.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.springboot.smartlibrary.service.LoginServices;

@Controller
@SessionAttributes("username")
public class LoginController
{
    @Autowired
    LoginServices services;

    @GetMapping("/login")
    public String showLoginPage(ModelMap model)
    {
    	System.out.println("login page: " + model);
        return "login";
    }

    @PostMapping("/login")
    public String showMainHomePageWhenLogin(ModelMap model,@RequestParam String username,@RequestParam String password)
    {
        boolean authentication= services.checkUserAuthentication(username,password);
        if(!authentication)
        {
        	System.out.println("authentication error");
            model.put("errorMessage","Bad Credentials");
            return "login";
        }
        System.out.println("authentication done");

        model.put("username", "Admin");
        return "home";
    }
    @RequestMapping("/home")
    public String showHomePage(ModelMap model)
    {
    	
        return "home";
    }

  
}
