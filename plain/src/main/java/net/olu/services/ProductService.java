package net.olu.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import net.olu.models.Cartline;
import net.olu.models.Category;
import net.olu.models.Product;

public interface ProductService {
	List<Product> findAllByCategory(Category category);
	public List<Product> findAll();
	public List<Integer> QuanityLeft(int left);
	public List<Product> listChanges(List<Product> product,List<Cartline> cartline);
	
	//IMAGE
	public void saveImage(Product product) throws Exception;
}
