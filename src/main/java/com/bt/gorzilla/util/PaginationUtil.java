package com.bt.gorzilla.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bt.gorzilla.bean.PaginationBean;

public class PaginationUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PaginationUtil.class);

	public static PaginationBean calculatePagination(Integer page, Integer size) {
		LOGGER.info("calculating pagination");
		PaginationBean paginationBean = null;
		if (null != page && null != size) {
			paginationBean = new PaginationBean();
			paginationBean.setRowsToSkip(calculateRowsToSkip(page, size));
			paginationBean.setNumberOfRows(size);
		}
		return paginationBean;
	}

	private static Integer calculateRowsToSkip(Integer page, Integer size) {
		return page.intValue() * size.intValue();
	}

}
