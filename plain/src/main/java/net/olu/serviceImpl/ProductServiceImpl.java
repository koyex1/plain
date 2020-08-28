package net.olu.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.olu.models.Category;
import net.olu.models.Product;
import net.olu.repositories.AccountRepository;
import net.olu.repositories.ProductRepository;
import net.olu.repositories.TaskRepository;
import net.olu.services.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService{
	

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	TaskRepository taskRepository;

	@Override
	public List<Product> findAllByCategory(Category category) {
		return productRepository.findByCategory(category);
	}

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}
		
	
}