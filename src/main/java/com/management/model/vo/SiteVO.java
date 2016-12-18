package com.management.model.vo;

import com.management.model.Site;

public class SiteVO extends Site{

	private String account;
	
	private Integer fixPriceGameCount;

	private String startDate;

	private String endDate;
	
	/**
	 * 销售额
	 */
	private Double salesAmount;


	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Integer getFixPriceGameCount() {
		return fixPriceGameCount;
	}

	public void setFixPriceGameCount(Integer fixPriceGameCount) {
		this.fixPriceGameCount = fixPriceGameCount;
	}

	public Double getSalesAmount() {
		return salesAmount;
	}

	public void setSalesAmount(Double salesAmount) {
		this.salesAmount = salesAmount;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
