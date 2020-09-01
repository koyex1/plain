package net.olu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.olu.models.Account;
import net.olu.models.Cart;

public interface CartRepository extends JpaRepository<Cart,Integer>{

	@Query(value="select c.* from cart c where c.accountId=?1",nativeQuery=true)
	public Cart getCartByAccount(int id);
	
	//
	public Cart findByAccount(Account account);
}
