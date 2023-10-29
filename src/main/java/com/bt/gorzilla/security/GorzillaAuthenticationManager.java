package com.bt.gorzilla.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.bt.gorzilla.dao.impl.UserDaoImpl;
import com.bt.gorzilla.entity.User;

@Component
public class GorzillaAuthenticationManager implements AuthenticationManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(GorzillaAuthenticationManager.class);

    private UserDaoImpl userDaoImpl;

    private PasswordEncoder passwordEncoder;

    public GorzillaAuthenticationManager(UserDaoImpl userDaoImpl, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.userDaoImpl = userDaoImpl;
    }

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = String.valueOf(authentication.getPrincipal());
		String password = String.valueOf(authentication.getCredentials());
		User user = userDaoImpl.getSingleUserDetails(username, null);
		if (user == null) {
			throw new BadCredentialsException("Authentication not provided");
		}
		
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new BadCredentialsException("Username Password did not match");
		}
		if (!user.getIsActive().equalsIgnoreCase("Y")) {
			throw new DisabledException("User is inactive");
		}
		return new UsernamePasswordAuthenticationToken(username, null, null);
	}

}
