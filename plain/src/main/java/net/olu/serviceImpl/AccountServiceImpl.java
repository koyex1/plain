package net.olu.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import net.olu.models.Account;
import net.olu.repositories.AccountRepository;
import net.olu.services.AccountService;



@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	//Behind the scene of the SecurityConfig. String username is a Model class in the security framework
	//that is latched to the path username in login form
	//seems like it does another verification for the password somewhere(loadUserByPassword) just like it has done for the username
	//here before it lathces  to Authentication
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUsername(username);
		if (account == null) {
			throw new UsernameNotFoundException("Username not found for" + username);
			
		}
		List<GrantedAuthority> grantedAuthorities= new ArrayList<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(account.getTask().getName()));
		
		return new User(account.getUsername(),account.getPassword(),grantedAuthorities);
		
		
	}

	@Override
	public Account findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}

	@Override
	public Account save(Account account) {
		return accountRepository.save(account);
	}

}
