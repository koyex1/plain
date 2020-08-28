package net.olu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import net.olu.models.Category;
import net.olu.repositories.ProductRepository;
import net.olu.services.CategoryService;

@ControllerAdvice
public class GlobalController {

	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductRepository productRepository;

	@ModelAttribute("categories")
	public List<Category> handlecategories() {
		return categoryService.findAll();
	}
	
	

}
