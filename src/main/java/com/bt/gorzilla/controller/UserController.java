package com.bt.gorzilla.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bt.gorzilla.bean.FailureBean;
import com.bt.gorzilla.bean.SuccessBean;
import com.bt.gorzilla.bean.UserInputBean;
import com.bt.gorzilla.constant.GorzillaConstant;
import com.bt.gorzilla.constant.GorzillaErrorConstant;
import com.bt.gorzilla.entity.User;
import com.bt.gorzilla.exception.UserRegistrationException;
import com.bt.gorzilla.response.UserResponse;
import com.bt.gorzilla.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(GorzillaConstant.SLASH + GorzillaConstant.REQUEST_API + GorzillaConstant.SLASH
		+ GorzillaConstant.REQUEST_VERSION)
@Tag(name="2. User Details")
public class UserController {

	@Autowired
	UserService userService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;


	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = GorzillaConstant.SLASH + GorzillaConstant.USER, method = RequestMethod.GET)
	public ResponseEntity<UserResponse> getUsers(@RequestParam(required = true, defaultValue = "0") Integer page,
			@RequestParam(required = true, defaultValue = "10") Integer size) {
		LOGGER.info("Inside getUsers");
		UserResponse userResponse = userService.getAllUsers(page, size);
		return ResponseEntity.ok().body(userResponse);
	}

	@RequestMapping(value = GorzillaConstant.SLASH + GorzillaConstant.USER + GorzillaConstant.SLASH
			+ "{userName}", method = RequestMethod.GET)
	public ResponseEntity<User> getOneUser(@PathVariable String userName) {
		LOGGER.info("Inside getUsers");
		User user = userService.getOneUser(userName);
		return ResponseEntity.ok().body(user);
	}
	
	@RequestMapping(value = GorzillaConstant.SLASH + GorzillaConstant.USER, method = RequestMethod.POST)
	public ResponseEntity<Object> createUser(@RequestBody UserInputBean userInputBean) {
		LOGGER.info("Inside createUser");
		try {
			userService.createUser(userInputBean, passwordEncoder);
			SuccessBean se = new SuccessBean();
			se.setSuccessMessage("User created successfully");
			se.setSuccessCode("200");
			return ResponseEntity.ok().body(se);
		} catch (UserRegistrationException e) {
			LOGGER.error("Error:" + e.getMessage());
			FailureBean fe = new FailureBean();
			fe.setErrorCode("400");
			fe.setErrorMessage(GorzillaErrorConstant.GE_0002);
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(fe);
		} catch (Exception e) {
			LOGGER.error("Error:" + e.getMessage());
			FailureBean fe = new FailureBean();
			fe.setErrorCode("500");
			fe.setErrorMessage(GorzillaErrorConstant.GE_0001);
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(fe);
		}
	}

}
