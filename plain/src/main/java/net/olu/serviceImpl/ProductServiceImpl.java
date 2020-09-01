package net.olu.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.olu.models.Cartline;
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

	@Override
	public List<Integer> QuanityLeft(int left) {
		List<Integer> quantityleft= new ArrayList<Integer>();
		for(int i=1;i<=left;i++) {
			quantityleft.add(i);
		}
		
		return quantityleft;
	}


	@Override
	public List<Product> listChanges(List<Product> product, List<Cartline> cartline) {
	int a=0;
	int i=0;
		while(i<cartline.size()) {
		a=	product.get(i).getQuantity() + cartline.get(i).getQuantity();
		product.get(i).setQuantity(a);
		i++;
		}
		
		return  product;
	}
		
	
}