package net.olu.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.olu.models.Account;

//crudrepository<Account, Integer> communicating with table Account
@Repository("accountRepository")
public interface AccountRepository extends CrudRepository<Account, Integer> {

	// not part of CRUDRepository's methods. so stating it in
	// this interface could only mean one thing
	// it is a new interface method
	public Account findByUsername(String username);
	public List<Account> findAll();

//	@Query("SELECT * FROM Account u WHERE u.role.name = supplier")
//	public Account findAllSuppliers();

}
//AccountRepository and crudRepository interfaces are not implemented anywhere by me. this they should be autowired 
//inorder to be used.