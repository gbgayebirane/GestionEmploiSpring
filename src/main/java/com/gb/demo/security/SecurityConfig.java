package com.gb.demo.security;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
 
	@Autowired
	private DataSource datasource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		//auth.inMemoryAuthentication().withUser("admin@gmail.com").password("passer").roles("ADMIN");
		//auth.inMemoryAuthentication().withUser("user@gmail.com").password("passer").roles("USER"); 
		 auth.jdbcAuthentication()
	     .dataSource(datasource)
	     .usersByUsernameQuery("select username as principal,password as credentials,etat from user where username=?")
	     .authoritiesByUsernameQuery("select username_username as principal,roles_nom as role from user_roles where username_username=?")
	     .rolePrefix("ROLE_");
		 

	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		try
		{
			http.authorizeRequests()
			.antMatchers("/Cv/**").hasRole("USER")
			.antMatchers("/Postule/liste").hasRole("USER")
			.antMatchers("/Offre/**").hasRole("ADMIN")
			.antMatchers("/Postule/offre/*").hasRole("ADMIN")
			.antMatchers("/").permitAll()
			.and().formLogin().loginPage("/login");
			
			//http.formLogin().loginPage("/login");
			
			//http.authorizeRequests().antMatchers("/Offre/**").hasRole("ENTREPRISE");
			
			//http.authorizeRequests().antMatchers("/Offre/**").permitAll();
			
			//http.authorizeRequests().antMatchers("/Cv/**").hasRole("USER");
			//http.authorizeRequests().antMatchers("/Postule/**").hasRole("DEMANDEUR");

	
			http.exceptionHandling().accessDeniedPage("/Admin/403");
			
			http.csrf().disable();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
