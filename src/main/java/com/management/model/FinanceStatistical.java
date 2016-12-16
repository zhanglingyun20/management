package com.management.model;

import java.util.Date;

public class FinanceStatistical {
	private Integer id;

	private Integer userId;
	private String account;
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	@Override
	public String toString() {
		return "SiteBill [id=" + id + ", userId=" + userId + ", account="
				+ account + ", remark=" + remark + ", amount=" + amount
				+ ", createTime=" + createTime + ", billDate=" + billDate + "]";
	}
	public FinanceStatistical() {
		super();
	}
	public FinanceStatistical(Integer id, Integer userId, String account, String remark,
			Double amount, Date createTime, Date billDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.account = account;
		this.remark = remark;
		this.amount = amount;
		this.createTime = createTime;
		this.billDate = billDate;
	}

	
	
}