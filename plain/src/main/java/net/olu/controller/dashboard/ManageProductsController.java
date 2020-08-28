package net.olu.controller.dashboard;

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

import net.olu.models.Category;
import net.olu.models.Product;
import net.olu.repositories.AccountRepository;
import net.olu.repositories.CategoryRepository;
import net.olu.repositories.ProductRepository;
import net.olu.services.AccountService;

@Controller
@RequestMapping("dashboard")
public class ManageProductsController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	
	@GetMapping(value="/manageproducts")
	public String ManageProducts(@RequestParam(name="operation", required=false) String operation
			,ModelMap mp) {
		Product product= new Product();
		Category category= new Category();
		mp.put("category", category);
		mp.put("product",product);
		mp.put("categories", categoryRepository.findAll());
		mp.put("users_products", productRepository.findAllProductsByTask(1));
		
		if(operation!=null) {
		if(operation.equals("product_added")) {
			mp.put("operation","product successfully added");
		}
		else if(operation.equals("product_deleted")) {
			mp.put("operation","Product successfully deleted");
		}
		else if(operation.equals("category_added")) {
			mp.put("operation","Category successfully added");
		}
		else if(operation.equals("category_deleted")) {
			mp.put("operation","Category successfully deleted");
		}
	}
		
		return "dashboard/manageproducts";
	}
	@PostMapping(value="/manageproducts_product_process")
	public String HandleManageProducts(@ModelAttribute("product") Product product, Authentication auth) {
		product.setAccount(accountRepository.findByUsername(auth.getName()));
	
		product.getCategory().setQuantity(1 + categoryRepository
				.findQuantityById(product.getCategory().getId()));
		
		productRepository.save(product);
		return "redirect:/dashboard/manageproducts?operation=product_added";
	}
	
	@PostMapping(value="/manageproducts_category_process")
	public String HandleManageCategory(@ModelAttribute("category") Category category) {
		categoryRepository.save(category);
		return "redirect:/dashboard/manageproducts?operation=category_added";
	}
	
	@GetMapping(value="/manageproducts/product_delete/{id}")
	public String Handleproductdelete(@PathVariable("id") int id) {
		Product product = new Product();
		product=productRepository.getOne(id);
		productRepository.delete(product);
		
		return "redirect:/dashboard/manageproducts?operation=product_deleted";
	}
	
	@GetMapping(value="/manageproducts/category_delete/{id}")
	public String Handlecategorydelete(@PathVariable("id") int id) {
		Category category = new Category();
		category= categoryRepository.findById(id);
		categoryRepository.delete(category);
		
		return "redirect:/dashboard/manageproducts?operation=category_deleted";
	}
	
	
}
