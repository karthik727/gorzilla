package com.bt.gorzilla.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bt.gorzilla.constant.GorzillaConstant;
import com.bt.gorzilla.entity.User;
import com.bt.gorzilla.service.UserService;

@RestController
@RequestMapping(GorzillaConstant.SLASH + GorzillaConstant.REQUEST_API + GorzillaConstant.SLASH
		+ GorzillaConstant.REQUEST_VERSION)
public class UserController {
	
    @Autowired
    UserService userService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value = GorzillaConstant.SLASH + GorzillaConstant.USER , method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers(Integer page,Integer size) {
		LOGGER.info("Inside getUsers");
		List<User> userList = userService.getUserDetailsService(page,size);
		
		return ResponseEntity.ok().body(userList);
	}

}
