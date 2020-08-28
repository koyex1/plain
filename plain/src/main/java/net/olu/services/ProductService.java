package net.olu.services;

import java.util.List;

import net.olu.models.Category;
import net.olu.models.Product;

public interface ProductService {
	List<Product> findAllByCategory(Category category);
	public List<Product> findAll();
}
