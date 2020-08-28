package net.olu.services;

import java.util.List;

import net.olu.models.Category;

public interface CategoryService {
	
	public Category findById(int id);
	public List<Category> findAll();
}
