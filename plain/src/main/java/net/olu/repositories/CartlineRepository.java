package net.olu.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.olu.models.Account;
import net.olu.models.Cartline;
import net.olu.models.Product;

@Repository("cartlineRepository")
public interface CartlineRepository extends JpaRepository<Cartline, Integer>{
	

	@Query(value="Select c.* from cartline c where c.accountId=?1", nativeQuery=true)
	public List<Cartline> findAllByAccount(int id);
	
	@Query(value="Select c.productId from cartline c where c.productId=?1", nativeQuery=true)
	public Cartline getByProductId(int id);
		
	@Query(value="select c.id from cartline c, account a, task t "
			+ "where c.productId=?1 and c.accountId=?2 "
			, nativeQuery=true)
	public Integer getIdbyProductId(int productId, int accountId);
	
	@Query(value="select sum(c.totalPrice) from cartline c "
			+ "where c.accountId=?1 "
			, nativeQuery=true)
	public Integer getTotalPriceByAccount(int accountId);
	
	@Query(value="Select c.* from cartline c where c.accountId=?1", nativeQuery=true)
	public List<Cartline> usersCartline(int accountId);
	
	/////////////
	@Query(value="select c.productId from cartline c where c.accountId=?1", nativeQuery=true)
	public List<Integer> getproductIdsByAccount(Account account);
	
	public List<Cartline> findByAccount(Account account);
	
	public Cartline findByProductAndAccount(Product product, Account account);
	
}
