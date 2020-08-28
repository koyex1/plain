package net.olu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.olu.models.Account;
import net.olu.models.Task;
import net.olu.repositories.AccountRepository;
import net.olu.repositories.TaskRepository;

@RequestMapping("signup")
@Controller
public class SignUpController {

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	TaskRepository taskRepository;
	
	@GetMapping
	public String signup(@RequestParam(name="operation", required=false)String operation, 
	ModelMap mp) {
		Account account = new Account();
		mp.put("account",account);
		mp.put("task", taskRepository.findAllExceptOne());
	
		return "/signup/index";
	}
	@PostMapping("/signup_process")
		public String HandleSignUp(@ModelAttribute("account") Account account) {
		account.setStatus(true);
		account.setPassword(new BCryptPasswordEncoder().encode(account.getPassword()));
			accountRepository.save(account);
			return "redirect:/login?operation=signup_success";
		}
	
}
