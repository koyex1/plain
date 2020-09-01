package net.olu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.olu.models.Account;
import net.olu.repositories.ProductRepository;
import net.olu.services.AccountService;
import net.olu.services.CategoryService;

@Controller
@RequestMapping(value= {"home","","/","index"})
public class HomeController {

	@Autowired
	AccountService accountService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping
	public String Home(@RequestParam(name="operation", required=false) String operation,
			ModelMap mp) {
	
		
		if(operation!=null) {
		if(operation.equals("success")) {
			mp.put("operation", "Logged In Successfully");
			
		}
		}
		mp.put("products",productRepository.findAll());
		
		return "home.index";		
	}
	

}
