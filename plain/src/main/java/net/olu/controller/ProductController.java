package net.olu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.olu.models.Account;
import net.olu.models.Cartline;
import net.olu.models.Product;
import net.olu.repositories.AccountRepository;
import net.olu.repositories.CartlineRepository;
import net.olu.repositories.ProductRepository;

@Controller
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CartlineRepository cartlineRepository;
	
	@Autowired
	AccountRepository accountRepository;

	@GetMapping("/{id}")
	public String handleproducts(@PathVariable("id") int id, @RequestParam(name="operation", required=false) String operation, ModelMap mp) {
		
		Product singleProduct = new Product();
		Cartline cartline = new Cartline();
		singleProduct = productRepository.getOne(id);
		
		mp.put("product",singleProduct);
		mp.put("cartline",cartline);
		mp.put("productSelect",singleProduct);
		
		if(operation!=null) {
			if(operation.equals("added")) {
				mp.put("operation","successfully added to Cart");
				singleProduct.setQuantity(singleProduct.getQuantity()-1);
			}
		}
		
		return "product.index";
	}
	@PostMapping("/add_to_cart/{id}")
	public String handlesaddingtocart(@PathVariable("id")int id, Authentication auth, @ModelAttribute("cartline") Cartline cartline) {
		Product product= new Product();
		product= productRepository.getOne(id);
		Account account = new Account();
		account = accountRepository.findByUsername(auth.getName());
		
		cartline.setAccount(account);
		cartline.setName(product.getName());
		cartline.setPrice(product.getPrice());
		
		cartlineRepository.save(cartline);
		
		return "redirect:/product/{id}?operation=added";
	}
}
