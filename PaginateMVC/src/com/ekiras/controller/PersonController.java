package com.ekiras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ekiras.service.PersonService;

@Controller
@RequestMapping(value="/person")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	
	/**
	 * 說明：顯示資料清單
	 * @return  String
	 */
	@RequestMapping(value="/list")
	public String list(Model model, Integer offset, Integer maxResults){
		model.addAttribute("persons", personService.list(offset, maxResults));
		model.addAttribute("count", personService.count());
		model.addAttribute("offset", offset);
		return "/person/list";
	}
	
	/**
	 * 說明：產生資料
	 * @return  String
	 */
	@RequestMapping(value="/save")
	public String save(){
		personService.save();
		return "/person/list";
	}
	
}
