package net.olu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("login")
public class LoginController {

	@GetMapping
	public String index(@RequestParam(name="operation", required=false) String operation,
			ModelMap mp) {
		if(operation!=null) {
		if(operation.equals("error")) {
			mp.put("operation", "Failed to Login");
		}
		else if(operation.equals("loggedout")) {
			mp.put("operation", "Logged out Successfully");
		}
		else if(operation.equals("signup_success")) {
			mp.put("operation", "Account created Successfully");
		}
		}
		return "login.index";
	}
	
	@PostMapping
	public String loggedIn() {
		
		return "redirect:/home?operation=success";
	}
	
}
