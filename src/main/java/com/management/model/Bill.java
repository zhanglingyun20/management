package com.management.model;

import java.util.Date;

public class Bill {
	private Integer id;

	private Integer deviceGameId;
	private Integer userId;
	private String userName;
	private String remark;
	private Double amount;
	private Date createTime;
	private Date billDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDeviceGameId() {
		return deviceGameId;
	}
	public void setDeviceGameId(Integer deviceGameId) {
		this.deviceGameId = deviceGameId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getBillDate() {
		return billDate;
	}
	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	@Override
	public String toString() {
		return "Bill [id=" + id + ", deviceGameId=" + deviceGameId
				+ ", userId=" + userId + ", userName=" + userName + ", remark="
				+ remark + ", amount=" + amount + ", createTime=" + createTime
				+ ", billDate=" + billDate + "]";
	}
	public Bill() {
		super();
	}
	public Bill(Integer id, Integer deviceGameId, Integer userId,
			String userName, String remark, Double amount, Date createTime,
			Date billDate) {
		super();
		this.id = id;
		this.deviceGameId = deviceGameId;
		this.userId = userId;
		this.userName = userName;
		this.remark = remark;
		this.amount = amount;
		this.createTime = createTime;
		this.billDate = billDate;
	}
	
	
}