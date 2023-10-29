package com.bt.gorzilla.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bt.gorzilla.bean.SuccessBean;
import com.bt.gorzilla.constant.GorzillaConstant;
import com.bt.gorzilla.entity.User;
import com.bt.gorzilla.response.UserResponse;
import com.bt.gorzilla.service.UserService;

@RestController
@RequestMapping(GorzillaConstant.SLASH + GorzillaConstant.REQUEST_API + GorzillaConstant.SLASH
		+ GorzillaConstant.REQUEST_VERSION)
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;


	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = GorzillaConstant.SLASH + GorzillaConstant.USER, method = RequestMethod.GET)
	public ResponseEntity<UserResponse> getUsers(Integer page, Integer size) {
		LOGGER.info("Inside getUsers");
		UserResponse userResponse = userService.getAllUsers(page, size);
		return ResponseEntity.ok().body(userResponse);
	}

	@RequestMapping(value = GorzillaConstant.SLASH + GorzillaConstant.USER + GorzillaConstant.SLASH
			+ "{userName}", method = RequestMethod.GET)
	public ResponseEntity<User> getOneUser(@PathVariable String userName, Integer page, Integer size) {
		LOGGER.info("Inside getUsers");
		User user = userService.getOneUser(userName, page, size);
		return ResponseEntity.ok().body(user);
	}
	
	@RequestMapping(value = GorzillaConstant.SLASH + GorzillaConstant.USER, method = RequestMethod.POST)
	public ResponseEntity<SuccessBean> createUser(@RequestBody User user) {
		LOGGER.info("Inside createUser");
		boolean isUserCreated = userService.createUser(user,passwordEncoder);
		SuccessBean se = new SuccessBean();
		se.setSuccessMessage("User created successfully");
		se.setSuccessCode("200");
		return ResponseEntity.ok().body(se);
	}

}
