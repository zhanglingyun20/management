package com.management.model;

import java.util.Date;

public class GameRunRecord {
	/**
     * 
     */
	private Integer id;

	private String account;
	private String deviceMac;
	private String deviceCode;
	private String deviceName;
	private String gameCode;
	private String gameName;
	private String gameProcess;
	/**
	 * 上传日期
	 */
	private Date reportTime;

	/**
	 * 运行次数
	 */
	private Integer runCount;

	/**
	 * 创建日期
	 */
	private Date createTime;

	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getDeviceMac() {
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getGameCode() {
		return gameCode;
	}

	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameProcess() {
		return gameProcess;
	}

	public void setGameProcess(String gameProcess) {
		this.gameProcess = gameProcess;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public Integer getRunCount() {
		return runCount;
	}

	public void setRunCount(Integer runCount) {
		this.runCount = runCount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public GameRunRecord() {
		super();
	}

	public GameRunRecord(Integer id, String account, String deviceMac,
			String deviceCode, String deviceName, String gameCode,
			String gameName, String gameProcess, Date reportTime,
			Integer runCount, Date createTime, Date updateTime) {
		super();
		this.id = id;
		this.account = account;
		this.deviceMac = deviceMac;
		this.deviceCode = deviceCode;
		this.deviceName = deviceName;
		this.gameCode = gameCode;
		this.gameName = gameName;
		this.gameProcess = gameProcess;
		this.reportTime = reportTime;
		this.runCount = runCount;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "GameRunRecord [id=" + id + ", account=" + account
				+ ", deviceMac=" + deviceMac + ", deviceCode=" + deviceCode
				+ ", deviceName=" + deviceName + ", gameCode=" + gameCode
				+ ", gameName=" + gameName + ", gameProcess=" + gameProcess
				+ ", reportTime=" + reportTime + ", runCount=" + runCount
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}

}