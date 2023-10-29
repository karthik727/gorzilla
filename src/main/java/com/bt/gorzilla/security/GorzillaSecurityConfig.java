package com.bt.gorzilla.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.bt.gorzilla.dao.impl.UserDaoImpl;


@Configuration
@EnableWebSecurity
public class GorzillaSecurityConfig {
	
	@Autowired
	UserDaoImpl userDaoImpl;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) ->
		requests.requestMatchers("/api/v1/test").permitAll()
		.anyRequest().authenticated()
		).httpBasic(withDefaults()).authenticationManager(new GorzillaAuthenticationManager(userDaoImpl,passwordEncoder()));
		return http.build();
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
