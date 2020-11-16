package net.olu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import net.olu.models.Category;
import net.olu.repositories.ProductRepository;
import net.olu.services.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping("/{id}")
	public String CategoryPage(@PathVariable("id") int id, ModelMap mp) {
		Category category= new Category();
		category = categoryService.findById(id);
		
		
		mp.put("products", productRepository.findByCategory(category));
		mp.put("category", category);
		
		return "category.index";
	}

	
	
}
