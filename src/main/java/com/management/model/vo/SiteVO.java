package com.management.model.vo;

import com.management.model.Site;

public class SiteVO extends Site{

	private String account;
	
	private Integer fixPriceGameCount;

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

	
	
}
