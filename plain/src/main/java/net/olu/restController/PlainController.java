package net.olu.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.olu.models.Account;
import net.olu.models.Product;
import net.olu.repositories.AccountRepository;
import net.olu.repositories.ProductRepository;

@RestController
@RequestMapping("json")
public class PlainController {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@GetMapping("allProducts")
	public List<Product> listOfProducts() {
		List<Product> findAll= new ArrayList<Product>();
		findAll= productRepository.findAll();
		return findAll;
	}
	
	@GetMapping("allUsers")
	public List<Account> listOfAccount() {
		List<Account> findAll= new ArrayList<Account>();
		findAll= accountRepository.findAll();
		return findAll;
	}
	
	@PostMapping("allUsers")
	public ResponseEntity addAccount(@RequestBody Account account) {
		accountRepository.save(account);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@PostMapping("allProducts")
	public ResponseEntity<Product> addProduct(@RequestBody Product product){
		productRepository.save(product);
		return new ResponseEntity<>(product,HttpStatus.OK);
	}
	
}
