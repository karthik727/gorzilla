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
import com.bt.gorzilla.response.UserAddressResponse;
import com.bt.gorzilla.service.UserAddressService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(GorzillaConstant.SLASH + GorzillaConstant.REQUEST_API + GorzillaConstant.SLASH
		+ GorzillaConstant.REQUEST_VERSION)
@Tag(name = "4. User Address Details")
public class UserAddressController {
	
	@Autowired
	UserAddressService userAddressService;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserAddressController.class);

	@RequestMapping(value = GorzillaConstant.SLASH + GorzillaConstant.USER_ADDRESS, method = RequestMethod.GET)
	public ResponseEntity<UserAddressResponse> getUsersAddress(@RequestParam(required = true, defaultValue = "0") Integer page,
			@RequestParam(required = true, defaultValue = "10") Integer size) {
		LOGGER.info("Inside getUsersAddress");
		UserAddressResponse userAddressResponse = userAddressService.getAllUsersAddress(page, size);
		return ResponseEntity.ok().body(userAddressResponse);
	}
}
