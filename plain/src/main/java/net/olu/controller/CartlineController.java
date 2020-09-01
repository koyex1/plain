package net.olu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.olu.models.Account;
import net.olu.models.Cart;
import net.olu.models.Cartline;
import net.olu.models.CartlineForm;
import net.olu.models.Orderline;
import net.olu.models.Product;
import net.olu.repositories.AccountRepository;
import net.olu.repositories.CartRepository;
import net.olu.repositories.CartlineRepository;
import net.olu.repositories.OrderRepository;
import net.olu.repositories.ProductRepository;
import net.olu.services.OrderService;
import net.olu.services.ProductService;



@Controller
@RequestMapping("/cartline")
public class CartlineController {

	@Autowired
	CartlineRepository cartlineRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductService productService;
	
	@GetMapping
	public String cartline(ModelMap mp, Authentication auth, @RequestParam(name="operation", required=false) String operation) {
		Account account= new Account();
		account=accountRepository.findByUsername(auth.getName());
		
		
		List<Cartline> cartline = cartlineRepository.findAllByAccount(account.getId());
		CartlineForm cartlineForm= new CartlineForm();
		cartlineForm.setCartlineList(cartline);
		mp.put("cartlineForm", cartlineForm);
		mp.put("totalprice",cartlineRepository.getTotalPriceByAccount(account.getId()));

//		cartlineRepository.getOne(2);
//		mp.put("form_cartline", cartlineRepository.getOne(2));
//		
//		
//		mp.put("cartline",cartline);
		
		if(operation!=null) {
			if(operation.equals("carts_deleted")) {
				mp.put("operation","All items removed");
			}
			if(operation.equals("cart_deleted")) {
				mp.put("operation","Item removed");
			}
		}
		
		
		
		return "cartline/index";
	}
	@GetMapping("/removeAllItems")
	public String handleRemoveAllItem(Authentication auth) {
		Account account= accountRepository.findByUsername(auth.getName());
		
		//delete from cartline
		List<Cartline> usersCart= cartlineRepository.findByAccount(account);
		cartlineRepository.deleteAll(usersCart);

		//return item back to Product
		List<Product> product= productRepository.findByIdIn(cartlineRepository.getproductIdsByAccount(account));
		List<Product> nproduct= productService.listChanges(product, usersCart);
		productRepository.saveAll(nproduct);
		
		//delete Cart
		Cart cart= cartRepository.findByAccount(account);
		cartRepository.delete(cart);
		
		return "redirect:/cartline?operation=carts_deleted"; 
	}

	@GetMapping("/removeItem/{id}")
	public String HandleDelete(@PathVariable("id") int id, Authentication auth) {
		Account account= accountRepository.findByUsername(auth.getName());
		
		//remove from cartline
		Cartline cartline= cartlineRepository.getOne(id);
		int productId = cartline.getProduct().getId();
		
		//remove cart
		Cart cart= cartRepository.findByAccount(account);
		cart.setQuantity(cart.getQuantity()-cartline.getQuantity());
		cart.setTotalPrice(cart.getTotalPrice()-cartline.getTotalPrice());
		cartRepository.save(cart);
			
		//return to product
		Product product= productRepository.findByIdAndAccount(productId, account);
		product.setQuantity(product.getQuantity()+cartline.getQuantity());
		productRepository.save(product);
		
		cartlineRepository.delete(cartline);
		return "redirect:/cartline?operation=cart_deleted"; 
}
	@GetMapping("/checkout")
	public String Handlecheckout(Authentication auth) {
		Account account= accountRepository.findByUsername(auth.getName());
		 List<Cartline> cartline = cartlineRepository.findByAccount(account);
	
		 List<Orderline> orderline= new ArrayList<Orderline>();
		 Cart cart= cartRepository.findByAccount(account);
	 //setorderproperties with cartlineproperties that also has supplierdetails
		 orderline= orderService.setOrderline(cartline);
		 orderRepository.saveAll(orderline);
		 cartRepository.delete(cart);
		 cartlineRepository.deleteAll(cartline);
		 
		 
		 return "cartline.checkout.index";
}
	
}
