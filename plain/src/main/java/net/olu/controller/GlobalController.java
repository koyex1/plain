package net.olu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import net.olu.models.Account;
import net.olu.models.Cart;
import net.olu.models.Category;
import net.olu.repositories.AccountRepository;
import net.olu.repositories.CartRepository;
import net.olu.repositories.ProductRepository;
import net.olu.services.CategoryService;

@ControllerAdvice
public class GlobalController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	AccountRepository accountRepository;

	@ModelAttribute("categories")
	public List<Category> handlecategories() {
		return categoryService.findAll();
	}
	
	@ModelAttribute("accountGlobal")
	public Account handleAccount(Authentication auth) {
		try {
		return accountRepository.findByUsername(auth.getName());
		}catch(NullPointerException ex) {
			Account account=new Account();
			return account;
		}
	}
	
	@ModelAttribute("cart")
	public Cart handleglobalCart(Authentication auth, HttpSession serv) {
		try {
		Account account= new Account();
		account= accountRepository.findByUsername(auth.getName());
		return cartRepository.getCartByAccount(account.getId());
		}
	catch(NullPointerException ex) {
		Cart cart= new Cart();
		return cart;
	}
	}
	

}
