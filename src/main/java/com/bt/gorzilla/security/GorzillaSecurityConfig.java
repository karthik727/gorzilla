package com.bt.gorzilla.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.bt.gorzilla.dao.impl.UserDaoImpl;

import jakarta.servlet.DispatcherType;


@Configuration
@EnableWebSecurity
public class GorzillaSecurityConfig {
	
	@Autowired
	UserDaoImpl userDaoImpl;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) ->
		requests
		.requestMatchers(HttpMethod.POST, "/api/v1/user").permitAll()
		.anyRequest().authenticated()
		).httpBasic(withDefaults()).authenticationManager(new GorzillaAuthenticationManager(userDaoImpl,passwordEncoder()))
		.csrf().disable();
		return http.build();
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
