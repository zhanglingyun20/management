package com.management.model;

/**
 * 场地级别
 * @author sawyer
 * @date 2016年8月13日
 */
public class SiteLevel {

	private Integer id;
	
	/**
	 * 级别编码
	 */
	private String levelCode;
	/**
	 * 级别名称
	 */
	private String levelName;
	
	/**
	 * 备注
	 */
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLevelCode() {
		return levelCode;
	}

	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
	}

	@Override
	public String toString() {
		return "SiteLevel [id=" + id + ", levelCode=" + levelCode
				+ ", levelName=" + levelName + ", remark=" + remark + "]";
	}

	public SiteLevel(Integer id, String levelCode, String levelName,
			String remark) {
		super();
		this.id = id;
		this.levelCode = levelCode;
		this.levelName = levelName;
		this.remark = remark;
	}

	public SiteLevel() {
		super();
	}
}
