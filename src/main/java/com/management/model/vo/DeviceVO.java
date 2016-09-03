package com.management.model.vo;

import com.management.model.Device;

public class DeviceVO extends Device{

	/**
	 * 销售额
	 */
	private Double sales;
	
	private String account;
	
	

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Double getSales() {
		return sales;
	}

	public void setSales(Double sales) {
		this.sales = sales;
	}
	
	
}
