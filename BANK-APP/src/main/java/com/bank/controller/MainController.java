package com.bank.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bank.model.Cards;
import com.bank.model.Person;
import com.bank.service.H2Service;
@Component
@Controller
public class MainController {
	
	@Autowired
	H2Service h2Meory;

	@RequestMapping(value = "/list-movements", method = RequestMethod.GET)
	public String showMovements(ModelMap model) throws SQLException {
		model.put("acount", h2Meory.findPerson());
		try{
			model.put("moves", h2Meory.findAll());
		}catch(Exception e){
				System.out.println("Ther is no data yet");
			}
		
		return "acountMovements";
	}
	
	@RequestMapping(value = "/depositT", method = RequestMethod.GET)
	public String depositTGET(ModelMap model) throws SQLException {
		model.addAttribute("depositT",new Cards(1,1,"",1,""));
		model.put("acount", h2Meory.findPerson());
		List<String> allCards = new ArrayList<String>();
		for(Cards card:h2Meory.findCards(getLoggedInUserName(model))){
			allCards.add(card.getDesc());
		}
		model.put("cards",allCards);
		return "depositT";
	}
	
	@RequestMapping(value = "/withdrawT", method = RequestMethod.GET)
	public String withdrawTGET(ModelMap model) throws SQLException {
		model.addAttribute("withdrawT",new Cards(1,1,"",1,""));
		model.put("acount", h2Meory.findPerson());
		List<String> allCards = new ArrayList<String>();
		for(Cards card:h2Meory.findCards(getLoggedInUserName(model))){
			allCards.add(card.getDesc());
		}
		model.put("cards",allCards);
		return "withdrawT";
	}
	
	@RequestMapping(value = "/depositT", method = RequestMethod.POST)
	public String depositTPOST(ModelMap model, @Valid Cards cards, BindingResult result) throws SQLException {
		model.addAttribute("depositT",new Person(1,"","","",0.00,""));
		model.put("acount", h2Meory.findPerson());
		List<String> allCards = new ArrayList<String>();
		for(Cards card:h2Meory.findCards(getLoggedInUserName(model))){
			allCards.add(card.getDesc());
		}
		model.put("cards",allCards);
		h2Meory.cardType(cards.getDesc());
		return "redirect:/depositD";
	}
	
	@RequestMapping(value = "/withdrawT", method = RequestMethod.POST)
	public String withdrawTPOST(ModelMap model, @Valid Cards cards, BindingResult result) throws SQLException {
		model.addAttribute("withdrawT",new Cards(1,1,"",1,""));
		model.put("acount", h2Meory.findPerson());
		List<String> allCards = new ArrayList<String>();
		for(Cards card:h2Meory.findCards(getLoggedInUserName(model))){
			allCards.add(card.getDesc());
		}
		model.put("cards",allCards);
		h2Meory.cardType(cards.getDesc());
		return "redirect:/withdrawD";
	}	
	
	@RequestMapping(value = "/depositD", method = RequestMethod.GET)
	public String depositDGET(ModelMap model) throws SQLException {
		model.addAttribute("depositD",new Person(1,"","","",0.0,""));
		model.put("acount", h2Meory.findPerson());
		
		return "depositD";
	}
	
	@RequestMapping(value = "/withdrawD", method = RequestMethod.GET)
	public String withdrawDGET(ModelMap model) throws SQLException {
		model.addAttribute("withdrawD",new Person(1,"","","",0.0,""));
		model.put("acount", h2Meory.findPerson());
		
		return "withdrawD";
	}
	
	@RequestMapping(value = "/depositD", method = RequestMethod.POST)
	public String depositDPOST(ModelMap model, @Valid Person person,BindingResult result) throws SQLException {
		
		if (result.hasErrors()) {
			return "depositT";
		}
		h2Meory.movRegisterDeposit(getLoggedInUserName(model),person);
		return "redirect:/depositC";
	}
	
	@RequestMapping(value = "/withdrawD", method = RequestMethod.POST)
	public String withdrawDPOST(ModelMap model, @Valid Person person,BindingResult result) throws SQLException {
		
		if (result.hasErrors()) {
			return "withdrawT";
		}
		h2Meory.movRegisterWithdraw(getLoggedInUserName(model),person);
		return "redirect:/withdrawC";
	}
	
	@RequestMapping(value = "/depositC", method = RequestMethod.GET)
	public String depositCGET(ModelMap model) throws SQLException {
		model.addAttribute("depositc",new Person(1,"","","",0.0,""));
		model.put("acount", h2Meory.findPerson());
		
		return "depositC";
	}
	
	@RequestMapping(value = "/withdrawC", method = RequestMethod.GET)
	public String withdrawCGET(ModelMap model) throws SQLException {
		model.addAttribute("withdrawC",new Person(1,"","","",0.0,""));
		model.put("acount", h2Meory.findPerson());
		
		return "withdrawC";
	}
	
	@RequestMapping(value = "/depositC", method = RequestMethod.POST)
	public String depositCPOST(ModelMap model) throws SQLException {
		model.addAttribute("depositc",new Person(1,"","","",0.0,""));
		model.put("acount", h2Meory.findPerson());
		
		return "welcome";
	}
	
	@RequestMapping(value = "/withdrawC", method = RequestMethod.POST)
	public String withdrawCPOST(ModelMap model) throws SQLException {
		model.addAttribute("withdrawC",new Person(1,"","","",0.0,""));
		model.put("acount", h2Meory.findPerson());
		
		return "welcome";
	}
	
	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		
		return principal.toString();
	}

}
