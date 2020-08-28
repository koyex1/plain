package net.olu.controller.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.olu.models.Product;
import net.olu.repositories.ProductRepository;


@Controller
@RequestMapping("dashboard")
public class UsersDataController {

	@Autowired
	ProductRepository productRepository;
	
	@GetMapping(value="/usersdata")
	public String Usersdata(@RequestParam(name="operation", required=false) String operation
			,ModelMap mp) {
		//suppliers data
		mp.put("suppliers", productRepository.findAllProductsByTask(9));
		if(operation!=null) {
			if(operation.equals("supplier_deleted")) {
				mp.put("operation","product successfully deleted");
			}
		}
		
		//get form 
		
		
		return "dashboard.usersdata";
	}
	@GetMapping(value="/usersdata_process")
	public String HandleDeletionofUsersProducts() {
		
		return "redirect:/dashboard/usersdata?operation=deleted";
	}
	
	@GetMapping(value="/usersdata/supplier_delete/{id}")
	public String HandleSupplierdelete(@PathVariable("id") int id) {
		Product product = new Product();
		product= productRepository.getOne(id);
		productRepository.delete(product);
		
		return "redirect:/dashboard/usersdata?operation=supplier_deleted";
	}
	

}
