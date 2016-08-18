package com.management.model;

import java.util.Date;

public class Site {
	/**
	 * 场地id
	 */
	private Integer id;

	/**
	 * 父级场地
	 */
	private Integer parentId;

	/**
	 * 用户id
	 */
	private Integer userId;

	/**
	 * 场地名称
	 */
	private String siteName;

	/**
	 * 省编码
	 */
	private String province;

	/**
	 * 城市编码
	 */
	private String city;
	
	
	private String county;

	/**
	 * 场地编码
	 */
	private String siteCode;
	
	private String remark;
	
	private String siteLevel;
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
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getSiteLevel() {
		return siteLevel;
	}
	public void setSiteLevel(String siteLevel) {
		this.siteLevel = siteLevel;
	}
	public Site(Integer id, Integer parentId, Integer userId, String siteName,
			String province, String city, String county, String siteCode,
			String remark, String siteLevel, Date createTime) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.userId = userId;
		this.siteName = siteName;
		this.province = province;
		this.city = city;
		this.county = county;
		this.siteCode = siteCode;
		this.remark = remark;
		this.siteLevel = siteLevel;
		this.createTime = createTime;
	}
	public Site() {
		super();
	}
	@Override
	public String toString() {
		return "Site [id=" + id + ", parentId=" + parentId + ", userId="
				+ userId + ", siteName=" + siteName + ", province=" + province
				+ ", city=" + city + ", county=" + county + ", siteCode="
				+ siteCode + ", remark=" + remark + ", siteLevel=" + siteLevel
				+ ", createTime=" + createTime + "]";
	}

	public enum Level {
		PROVINCE("province"), CITY("city"), COUNTY("county");
		private final String value;

		Level(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
	
	
}