package com.bt.gorzilla.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bt.gorzilla.Exception.UserRegistrationException;
import com.bt.gorzilla.bean.FailureBean;
import com.bt.gorzilla.bean.SuccessBean;
import com.bt.gorzilla.bean.UserRegisterBean;
import com.bt.gorzilla.constant.GorzillaConstant;
import com.bt.gorzilla.constant.GorzillaErrorConstant;
import com.bt.gorzilla.service.UserRegisterService;

@RestController
@RequestMapping(GorzillaConstant.SLASH + GorzillaConstant.REQUEST_API + GorzillaConstant.SLASH
		+ GorzillaConstant.REQUEST_VERSION)
public class UserRegisterController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRegisterController.class);

	
	@Autowired
	UserRegisterService userRegisterService;
	
	@Autowired
    private PasswordEncoder passwordEncoder;

	
	@RequestMapping(value = GorzillaConstant.SLASH + GorzillaConstant.REGISTER, method = RequestMethod.POST)
	public ResponseEntity<Object> registerUser(@RequestBody UserRegisterBean userRegisterBean) {
		LOGGER.info("Inside createUser");
		try {
			boolean isUserCreated = userRegisterService.registerUser(userRegisterBean,passwordEncoder);
			SuccessBean se = new SuccessBean();
			se.setSuccessMessage("User created successfully");
			se.setSuccessCode("200");
			return ResponseEntity.ok().body(se);
		}catch(UserRegistrationException e) {
			LOGGER.error("Error:"+e.getMessage());
			FailureBean fe = new FailureBean();
			fe.setErrorCode("400");
			fe.setErrorMessage(GorzillaErrorConstant.GE_0002);
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(fe);
		}catch(Exception e) {
			LOGGER.error("Error:"+e.getMessage());
			FailureBean fe = new FailureBean();
			fe.setErrorCode("500");
			fe.setErrorMessage(GorzillaErrorConstant.GE_0001);
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(fe);
		}
	}


}
