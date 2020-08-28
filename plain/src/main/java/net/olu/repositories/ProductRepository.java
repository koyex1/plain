package net.olu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.olu.models.Category;
import net.olu.models.Product;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Integer>{

	public List<Product> findByCategory(Category category);

	public List<Product> findAll();
	
	@Query(value="SELECT p.* "
			+ "FROM product AS p, account AS a, task AS t "
			+ "WHERE p.accountId= a.id "
			+ "AND a.taskId= t.id "
			+ "AND t.id=?1", nativeQuery=true)
	public List<Product> findAllProductsByTask(int id);
	
	
	
//@Query(value="SELECT count(p.id) from product p, category c where p.categoryId=c.id and c.id=?1",nativeQuery=true)
//public int numberOfProductsByCategory(int id);
	
}
