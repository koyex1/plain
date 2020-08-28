package net.olu.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import net.olu.models.Account;

public interface AccountService extends UserDetailsService{
	
	public Account findByUsername(String username);
	
	public Account save(Account account);
	
}
