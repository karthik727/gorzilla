package com.bt.gorzilla.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;

public class GorzillaUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GorzillaUtil.class);


	public static String getLoggedInUserName(Authentication authentication) {
		String loggedInUserName = null;
		if(null!=authentication) {
			if(!isObjectEmpty(authentication.getPrincipal()) && !isStringEmpty(authentication.getPrincipal().toString())){
				return authentication.getPrincipal().toString();
			}
		}
		return loggedInUserName;
	}

	private static boolean isStringEmpty(String inputString) {
		if (null == inputString || inputString.isEmpty()) {
			return true;
		}
		return false;
	}
	
	private static boolean isObjectEmpty(Object object) {
		if (null == object) {
			return true;
		}
		return false;
	}


}
