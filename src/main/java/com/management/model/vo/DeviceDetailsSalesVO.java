package com.management.model.vo;

public class DeviceDetailsSalesVO {

	private String deviceCode;
	
	private String gameName;
	private String gameCode;
	/**
	 * 销售额
	 */
	private Double sales;
	
	/**
	 * 单价
	 */
	private Double price;
	
	/**
	 * 运行次数
	 */
	private Integer runCount;
	
	private String startTime;
	
	private String endTime;

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameCode() {
		return gameCode;
	}

	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
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

	public Integer getRunCount() {
		return runCount;
	}

	public void setRunCount(Integer runCount) {
		this.runCount = runCount;
	}

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

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	
}
