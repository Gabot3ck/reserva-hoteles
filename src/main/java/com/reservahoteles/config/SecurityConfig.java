package com.reservahoteles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
				.authorizeHttpRequests( auth -> {
					auth.anyRequest().authenticated();
				})
				.formLogin(formLogin ->
					formLogin
						.loginPage("/login")
						.defaultSuccessUrl("/",true)
						.permitAll()
				);

		return httpSecurity.build();
	}
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("1234")
				.roles("USER","ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(user);
	}

}
