package com.management.model.vo;

import com.management.model.Game;

public class GameVO extends Game{

	/**
	 * 销售额
	 */
	private Double salesAmount;
	
	private String deviceCode;
	
	private Integer runCount;
	
	private Double price;
	

	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getRunCount() {
		return runCount;
	}

	public void setRunCount(Integer runCount) {
		this.runCount = runCount;
	}


	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public Double getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(Double salesAmount) {
		this.salesAmount = salesAmount;
	}
	
	
}
