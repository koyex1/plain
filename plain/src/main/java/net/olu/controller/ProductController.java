package net.olu.controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;

import net.olu.models.Account;
import net.olu.models.Cart;
import net.olu.models.Cartline;
import net.olu.models.Product;
import net.olu.repositories.AccountRepository;
import net.olu.repositories.CartRepository;
import net.olu.repositories.CartlineRepository;
import net.olu.repositories.ProductRepository;
import net.olu.services.ProductService;

@Controller
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CartlineRepository cartlineRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	AccountRepository accountRepository;

	@GetMapping("/{id}")
	public String handleproducts(@PathVariable("id") int id, @RequestParam(name="operation", required=false) String operation, ModelMap mp) {
		
		Product singleProduct = new Product();
		Cartline cartline = new Cartline();
		singleProduct = productRepository.getOne(id);
		List<Integer> productSelect= new ArrayList<Integer>();
		productSelect=productService.QuanityLeft(singleProduct.getQuantity());
		
		mp.put("productSelect",productSelect);
		mp.put("product",singleProduct);
		mp.put("cartline",cartline);
		
		if(operation!=null) {
			if(operation.equals("added")) {
				mp.put("operation","successfully added to Cart");
//				singleProduct.setQuantity(singleProduct.getQuantity()-1);
			}
		}
		
		return "product/index";
	}
	@PostMapping("/add_to_cart/{id}")
	public String handlesaddingtocart(@PathVariable("id")int id, Authentication auth,
			@ModelAttribute("cartline") Cartline cartline
			) {
		Account account = accountRepository.findByUsername(auth.getName());
		//SETTING PRODUCT
		Product product= productRepository.getOne(id);
		product.setQuantity(product.getQuantity()-cartline.getQuantity());
		//SETTING CARTLINE
		//id not yet generated
		cartline.setAccount(account);  //accountId
		cartline.setName(product.getName()); //name
		cartline.setPrice(product.getPrice()); //price
		cartline.setProduct(product);  //productId
		cartline.setInStock(product.getQuantity()); //instock
		cartline.setTotalPrice(product.getPrice()* cartline.getQuantity());
		cartline.setSupplier(product.getAccount());
		
		Cartline cartline1= cartlineRepository.findByProductAndAccount
				(cartline.getProduct(),cartline.getAccount());	
			
//				cartlineRepository.getIdbyProductId
//				(cartline.getProduct().getId(),
//				cartline.getAccount().getId());
		//if id ALREADY EXIST
		if(cartline1 != null) {
			int qtyAdd= cartline1.getQuantity();
			cartline1.setQuantity(cartline1.getQuantity()+qtyAdd);
			cartlineRepository.save(cartline1);
		}
		//IF ID DOESNT EXIST
		cartlineRepository.save(cartline);
		Cart cart= cartRepository.findByAccount(account);
		//creating new cart if doesnt exist
		 if(cart==null) {
			Cart mcart= new Cart();
			mcart.setQuantity(cartline.getQuantity());
			mcart.setTotalPrice(cartline.getTotalPrice());
			mcart.setAccount(account);
			cartRepository.save(mcart);
		}
		 //working with the carts that already exist
		 else if(cart!=null) {
			 if(cartline1!=null) {
				cart.setQuantity(cart.getQuantity()+cartline1.getQuantity());
				cart.setTotalPrice(cart.getTotalPrice()+cartline1.getTotalPrice());
			 }
			 else {
				 cart.setQuantity(cart.getQuantity()+cartline.getQuantity());
					cart.setTotalPrice(cart.getTotalPrice()+cartline.getTotalPrice());
			 }
				cartRepository.save(cart);
			}
		
		
		return "redirect:/product/{id}?operation=added";
		
	}
}
