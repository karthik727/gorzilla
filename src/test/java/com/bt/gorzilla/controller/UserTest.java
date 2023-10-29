package com.bt.gorzilla.controller;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.bt.gorzilla.entity.User;
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
		ResponseEntity<List<User>> userRespone = userController.getUsers(null, null);
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
		List<User> userList = userService.getUserDetailsService(null, null);
		if (null != userList && userList.isEmpty()) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}
}
