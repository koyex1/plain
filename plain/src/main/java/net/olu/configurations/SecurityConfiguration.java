package net.olu.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;

import net.olu.services.AccountService;

@EnableWebSecurity
@Configuration
@Order(1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private AccountService accountService;
	
	@Override
	protected void configure(HttpSecurity httpsecure)throws Exception{
		
		httpsecure.authorizeRequests()
				  .antMatchers("/signup/**").anonymous()
				  .and()
				  .formLogin()
				  .loginPage("/login")
				 // .loginProcessingUrl("/login")
				  .defaultSuccessUrl("/home?operation=success")
				  .failureUrl("/login?operation=error")
				  .usernameParameter("username").passwordParameter("password")
				  .and()
				  .logout()
				  .logoutUrl("/logout")
				  .logoutSuccessUrl("/login?operation=loggedout")
				  .deleteCookies("JSESSIONID");
				  //.and()
				  //.exceptionHandling().accessDeniedPage("/admin-panel/accessDenied");
				  
				  
		
		httpsecure.cors().and().csrf().disable();
		
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception{
		builder.userDetailsService(accountService);
	}
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityContextHolderAwareRequestFilter awareRequestFilter() {
		return new SecurityContextHolderAwareRequestFilter();
	}
	
	@Bean
	public SecurityContextPersistenceFilter persistenceFilter() {
		return new SecurityContextPersistenceFilter();
	}
}
