package com.management.model;

import java.util.Date;

public class SiteBill {
	private Integer id;

	private Integer userId;
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
	@Override
	public String toString() {
		return "SiteBill [id=" + id + ", userId=" + userId + ", remark="
				+ remark + ", amount=" + amount + ", createTime=" + createTime
				+ ", billDate=" + billDate + "]";
	}
	
}