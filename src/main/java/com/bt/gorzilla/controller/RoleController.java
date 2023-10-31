package com.bt.gorzilla.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bt.gorzilla.constant.GorzillaConstant;
import com.bt.gorzilla.response.RoleResponse;
import com.bt.gorzilla.service.RoleService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(GorzillaConstant.SLASH + GorzillaConstant.REQUEST_API + GorzillaConstant.SLASH
		+ GorzillaConstant.REQUEST_VERSION)
@Tag(name = "5. Role Details")
public class RoleController {
	
	@Autowired
	RoleService roleService;

	private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

	@RequestMapping(value = GorzillaConstant.SLASH + GorzillaConstant.ROLE, method = RequestMethod.GET)
	public ResponseEntity<RoleResponse> getRoles(@RequestParam(required = true, defaultValue = "0") Integer page,
			@RequestParam(required = true, defaultValue = "10") Integer size) {
		LOGGER.info("Inside getRoles");
		RoleResponse roleResponse = roleService.getAllRoles(page, size);
		return ResponseEntity.ok().body(roleResponse);
	}

}
