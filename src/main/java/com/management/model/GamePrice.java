package com.management.model;

import java.util.Date;

public class GamePrice {
	/**
     * 
     */
	private Integer id;

	/**
	 * 场地账号
	 */
	private String account;
	private String gameCode;
	private String gameProcess;

	/**
	 * 游戏运行收费单价(元/次)
	 */
	private Double runPrice;

	/**
	 * 定价日期（按照定价日期结算此日期内产生的数据）
	 */
	private Date fixedPrice;

	
	/**
	 * 是否是当前使用价格
	 */
	private Integer isUse;
	/**
	 * 创建时间
	 */
	private Date createTime;
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
	public String getGameCode() {
		return gameCode;
	}
	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}
	public String getGameProcess() {
		return gameProcess;
	}
	public void setGameProcess(String gameProcess) {
		this.gameProcess = gameProcess;
	}
	public Double getRunPrice() {
		return runPrice;
	}
	public void setRunPrice(Double runPrice) {
		this.runPrice = runPrice;
	}
	public Date getFixedPrice() {
		return fixedPrice;
	}
	public void setFixedPrice(Date fixedPrice) {
		this.fixedPrice = fixedPrice;
	}
	public Integer getIsUse() {
		return isUse;
	}
	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public GamePrice(Integer id, String account, String gameCode,
			String gameProcess, Double runPrice, Date fixedPrice,
			Integer isUse, Date createTime) {
		super();
		this.id = id;
		this.account = account;
		this.gameCode = gameCode;
		this.gameProcess = gameProcess;
		this.runPrice = runPrice;
		this.fixedPrice = fixedPrice;
		this.isUse = isUse;
		this.createTime = createTime;
	}
	public GamePrice() {
		super();
	}
	@Override
	public String toString() {
		return "GamePrice [id=" + id + ", account=" + account + ", gameCode="
				+ gameCode + ", gameProcess=" + gameProcess + ", runPrice="
				+ runPrice + ", fixedPrice=" + fixedPrice + ", isUse=" + isUse
				+ ", createTime=" + createTime + "]";
	}
	
	public enum Use {
		YES("yes"), NO("no");

		private final String value;

		Use(String value)
		{
			this.value = value;
		}

		public String getValue()
		{
			return value;
		}
	}

	
}