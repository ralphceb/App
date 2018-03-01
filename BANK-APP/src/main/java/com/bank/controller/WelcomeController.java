package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bank.service.H2Service;

@Controller
public class WelcomeController {

	@Autowired
	private H2Service h2Meory;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcomePage(ModelMap model) {
		try{
			h2Meory.initDB();
			model.put("name", getLoggedinUserName());
			model.put("acount", h2Meory.findPerson());
		}catch(Exception e){
			System.out.println("");
		}
		return "welcome";
	}

	private String getLoggedinUserName() {
		Object ini = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (ini instanceof UserDetails) {
			return ((UserDetails) ini).getUsername();
		}
		
		return ini.toString();
	}

}
