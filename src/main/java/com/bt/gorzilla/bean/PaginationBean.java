package com.bt.gorzilla.bean;

public class PaginationBean {

	private Integer rowsToSkip;
	private Integer numberOfRows;

	public Integer getRowsToSkip() {
		return rowsToSkip;
	}

	public void setRowsToSkip(Integer rowsToSkip) {
		this.rowsToSkip = rowsToSkip;
	}

	public Integer getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(Integer numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

}
