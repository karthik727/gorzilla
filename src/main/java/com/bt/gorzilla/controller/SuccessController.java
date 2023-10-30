package com.bt.gorzilla.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bt.gorzilla.bean.SuccessBean;
import com.bt.gorzilla.constant.GorzillaConstant;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(GorzillaConstant.SLASH + GorzillaConstant.REQUEST_API + GorzillaConstant.SLASH
		+ GorzillaConstant.REQUEST_VERSION)
@Tag(name="4. Success Controller")
public class SuccessController {

	@RequestMapping(value = GorzillaConstant.SLASH + GorzillaConstant.TEST, method = RequestMethod.GET)
	public ResponseEntity<SuccessBean> successTest() {
		SuccessBean se = new SuccessBean();
		se.setSuccessMessage("Test success, API invoked Successfully");
		se.setSuccessCode("200");
		return ResponseEntity.ok().body(se);
	}

}
