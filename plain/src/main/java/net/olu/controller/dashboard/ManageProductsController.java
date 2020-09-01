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

import net.olu.models.Account;
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
	public String ManageProducts(@RequestParam(name="operation", required=false) String operation, 
			ModelMap mp, Authentication auth) {
		Account account= accountRepository.findByUsername(auth.getName());
		Product product= new Product();
		Category category= new Category();
		mp.put("category", category);
		mp.put("product",product);
		mp.put("categories", categoryRepository.findAll());
		//1 should be replaced with authentication Id instead of using a constant
//		mp.put("users_products", productRepository.findAllProductsByTask(1));
		mp.put("users_products", productRepository.findByAccount(account));
		mp.put("buttonProduct","Add");
		mp.put("buttonCategory","Add");
		
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
		else if(operation.equals("category_updated")) {
			mp.put("operation","Category successfully updated");
		}
		else if(operation.equals("category_deleted")) {
			mp.put("operation","Category successfully deleted");
		}
		else if(operation.equals("product_updated")) {
			mp.put("operation","product successfully updated");
		}
		
	}
		
		return "dashboard/manageproducts";
	}
	@PostMapping(value="/manageproducts_product_process")
	public String HandleManageProducts(@ModelAttribute("product") Product product, Authentication auth) {
		//NULL NEW PRODUCT
		if(product.getId()==null) {
		product.setAccount(accountRepository.findByUsername(auth.getName()));
	
		product.getCategory().setQuantity(1 + categoryRepository
				.findQuantityById(product.getCategory().getId()));
		}
		
		productRepository.save(product);
		if(product.getId()==null) {
		return "redirect:/dashboard/manageproducts?operation=product_added";
		}
		else {
			return "redirect:/dashboard/manageproducts?operation=product_updated";

		}
	}
	
	@PostMapping(value="/manageproducts_category_process")
	public String HandleManageCategory(@ModelAttribute("category") Category category) {
		categoryRepository.save(category);
		if(category.getId()== null) {
		return "redirect:/dashboard/manageproducts?operation=category_added";
		}
		else {
		return "redirect:/dashboard/manageproducts?operation=category_updated";

		}
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
	@GetMapping(value="/manageproducts/product_edit/{id}")
	public String HandleproductEdit(@PathVariable("id") int id, ModelMap mp
			,Authentication auth) {
		Account account= accountRepository.findByUsername(auth.getName());
		Product product= productRepository.getOne(id);
		Category category= new Category();
		mp.put("category", category);
		mp.put("product",product);
		mp.put("buttonProduct","Update");
		mp.put("categories", categoryRepository.findAll());
		mp.put("users_products", productRepository.findByAccount(account));
		
		return "dashboard/manageproducts";	
	
}
	@GetMapping(value="/manageproducts/category_edit/{id}")
	public String HandleCategoryEdit(@PathVariable("id") int id, ModelMap mp
			,Authentication auth) {
		Product product= new Product();
		Account account= accountRepository.findByUsername(auth.getName());
		Category category= categoryRepository.findById(id);
		mp.put("category", category);
		mp.put("product",product);
		mp.put("buttonCategory","Update");
		mp.put("buttonProduct","Add");
		mp.put("categories", categoryRepository.findAll());
		mp.put("users_products", productRepository.findByAccount(account));
		
		return "dashboard/manageproducts";	
	
}
	
	
}
