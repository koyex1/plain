package net.olu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.olu.models.Category;

@Repository("categoryRepository")
public interface CategoryRepository extends CrudRepository<Category, Integer> {

	public Category findById(int id);
	public List<Category> findAll();
	
	@Query(value="select c.quantity from category c where c.id=?1", nativeQuery=true)
	public int findQuantityById(int id);
	
	
}
