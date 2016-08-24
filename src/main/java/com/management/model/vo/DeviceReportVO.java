package com.management.model.vo;

import com.management.model.GameRunRecord;

public class DeviceReportVO extends GameRunRecord {

	private String deviceName;
	private String siteName;
	private String gameName;
	private String siteLevel;
	
	private String startTime;
	
	private String endTime;
	
	/**
	 * 销售额
	 */
	private Double sales;
	
	/**
	 * 单价
	 */
	private Double price;
	
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSiteLevel() {
		return siteLevel;
	}

	public void setSiteLevel(String siteLevel) {
		this.siteLevel = siteLevel;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public Double getSales() {
		return sales;
	}

	public void setSales(Double sales) {
		this.sales = sales;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	
}
