package com.management.model.vo;

import com.management.model.GameRunRecord;

public class DeviceReportVO extends GameRunRecord {

	private String deviceName;
	private String siteName;
	private String gameName;
	private String siteLevel;
	
	

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

}
