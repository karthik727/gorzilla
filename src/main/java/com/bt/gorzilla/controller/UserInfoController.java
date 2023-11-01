package com.bt.gorzilla.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bt.gorzilla.bean.FailureBean;
import com.bt.gorzilla.bean.SuccessBean;
import com.bt.gorzilla.bean.UserInfoRegisterBean;
import com.bt.gorzilla.constant.GorzillaConstant;
import com.bt.gorzilla.constant.GorzillaErrorConstant;
import com.bt.gorzilla.entity.UserInfo;
import com.bt.gorzilla.exception.UserRegistrationException;
import com.bt.gorzilla.response.UserInfoResponse;
import com.bt.gorzilla.service.UserInfoService;
import com.bt.gorzilla.util.GorzillaUtil;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(GorzillaConstant.SLASH + GorzillaConstant.REQUEST_API + GorzillaConstant.SLASH
		+ GorzillaConstant.REQUEST_VERSION)
@Tag(name = "3. User Info Details")
public class UserInfoController {

	@Autowired
	UserInfoService userInfoService;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);

	@RequestMapping(value = GorzillaConstant.SLASH + GorzillaConstant.USER_INFO, method = RequestMethod.GET)
	public ResponseEntity<UserInfoResponse> getUsersInfo(@RequestParam(required = true, defaultValue = "0") Integer page,
			@RequestParam(required = true, defaultValue = "10") Integer size) {
		LOGGER.info("Inside getUsersInfo");
		UserInfoResponse userInfoResponse = userInfoService.getAllUsersInfo(page, size);
		return ResponseEntity.ok().body(userInfoResponse);
	}

	@RequestMapping(value = GorzillaConstant.SLASH + GorzillaConstant.USER_INFO + GorzillaConstant.SLASH
			+ "{userid}", method = RequestMethod.GET)
	public ResponseEntity<UserInfo> getOneUserInfo(@PathVariable String userid) {
		LOGGER.info("Inside getOneUserInfo");
		UserInfo userInfo = userInfoService.getOneUserInfo(userid);
		return ResponseEntity.ok().body(userInfo);
	}

	@RequestMapping(value = GorzillaConstant.SLASH + GorzillaConstant.USER_INFO, method = RequestMethod.POST)
	public ResponseEntity<Object> createUserInfo(Authentication authentication,@RequestBody UserInfoRegisterBean userInfoRegisterBean) {
		LOGGER.info("Inside createUserInfo");
		try {
			String loggedInUserName = GorzillaUtil.getLoggedInUserName(authentication);
			LOGGER.info("loggedInUserName:"+loggedInUserName);
			userInfoService.createUserInfo(userInfoRegisterBean,loggedInUserName);
			SuccessBean se = new SuccessBean();
			se.setSuccessMessage("UserInfo created successfully");
			se.setSuccessCode("200");
			return ResponseEntity.ok().body(se);
		} catch (UserRegistrationException e) {
			LOGGER.error("Error:" + e.getMessage());
			FailureBean fe = new FailureBean();
			fe.setErrorCode("400");
			fe.setErrorMessage(GorzillaErrorConstant.GE_0004);
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
