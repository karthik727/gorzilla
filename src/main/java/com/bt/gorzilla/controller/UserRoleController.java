package com.bt.gorzilla.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bt.gorzilla.constant.GorzillaConstant;
import com.bt.gorzilla.response.UserRoleResponse;
import com.bt.gorzilla.service.UserRoleService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(GorzillaConstant.SLASH + GorzillaConstant.REQUEST_API + GorzillaConstant.SLASH
		+ GorzillaConstant.REQUEST_VERSION)
@Tag(name = "6. User Role Details")
public class UserRoleController {

	@Autowired
	UserRoleService userRoleService;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleController.class);

	@RequestMapping(value = GorzillaConstant.SLASH + GorzillaConstant.USER_ROLE, method = RequestMethod.GET)
	public ResponseEntity<UserRoleResponse> getUserRoles(
			@RequestParam(required = true, defaultValue = "0") Integer page,
			@RequestParam(required = true, defaultValue = "10") Integer size) {
		LOGGER.info("Inside getUserRoles");
		UserRoleResponse userRoleResponse = userRoleService.getUserRoles(page, size);
		return ResponseEntity.ok().body(userRoleResponse);
	}

}
