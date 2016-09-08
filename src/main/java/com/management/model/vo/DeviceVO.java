package com.management.model.vo;

import com.management.model.Device;

public class DeviceVO extends Device{

	/**
	 * 销售额
	 */
	private Double salesAmount;
	
	private String account;
	
	

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Double getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(Double salesAmount) {
		this.salesAmount = salesAmount;
	}

	
}
