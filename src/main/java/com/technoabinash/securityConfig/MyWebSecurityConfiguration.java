package com.technoabinash.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
public class MyWebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	CustomUserDetail cDetail=new CustomUserDetail();
	
	public CustomUserDetail getUserdetail() {
		return cDetail ;
	}
	
	@Autowired
	 UserDetailsService userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder PasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		
		auth.userDetailsService(userDetailsService);
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/user/**").hasRole("USER")
		.antMatchers("/**").permitAll()
		.and()
		.formLogin().loginPage("/signin")
		.loginProcessingUrl("/dologin")
		.successHandler(new MySuccessHandler())
		.permitAll()
		.and().logout();
		
	}

}
