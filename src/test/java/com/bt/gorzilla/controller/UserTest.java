package com.bt.gorzilla.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.bt.gorzilla.response.UserResponse;
import com.bt.gorzilla.service.UserService;

@SpringBootTest
public class UserTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserTest.class);

	@Test
	void contextLoads() {
	}

	@Test
	public void testUserController() {
		LOGGER.info("Inside testUserController");
		UserController userController = new UserController();
		ResponseEntity<UserResponse> userRespone = userController.getUsers(null, null);
		if (userRespone.getStatusCode().value() == 200) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}

	@Test
	public void testUserService() {
		LOGGER.info("Inside testUserService");
		UserService userService = new UserService();
		UserResponse userResponse = userService.getAllUsers(null, null);
		if (null != userResponse && null != userResponse.getUserData() && userResponse.getUserData().isEmpty()) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}
}
