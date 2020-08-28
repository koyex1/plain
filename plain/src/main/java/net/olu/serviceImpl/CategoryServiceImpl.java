package net.olu.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.olu.models.Category;
import net.olu.repositories.CategoryRepository;
import net.olu.services.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepository categoryRepository;
	
	@Override
	public Category findById(int id) {
		return  categoryRepository.findById(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	
	
	
}
