package com.management.model.vo;

public class DeviceSalesVO {

	private String deviceName;
	/**
	 * 销售额
	 */
	private Double sales;
	
	private String deviceCode;

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public Double getSales() {
		return sales;
	}

	public void setSales(Double sales) {
		this.sales = sales;
	}
	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	
}
