package com.management.model;

import java.util.Date;

public class GameReportBO extends GameRunRecord
{
    /**
     * 场地id
     */
    private Integer siteId;

    /**
     * VR唯一标识mac
     */
    private String deviceMac;

    /**
     * VR设备编号
     */
    private String deviceCode;
    /**
     * VR设备名称
     */
    private String deviceName;
	/**
	 * 游戏运行收费单价(元/次)
	 */
	private Double runPrice;
	/**
	 * 定价日期（按照定价日期结算此日期内产生的数据）
	 */
	private Date fixedPrice;
	/**
	 * 定价是否正在使用
	 */
	private Integer isUse;
	private Double countPrice;
	
	public Double getRunPrice()
	{
		return runPrice;
	}
	public void setRunPrice(Double runPrice)
	{
		this.runPrice = runPrice;
	}

	public Date getFixedPrice()
	{
		return fixedPrice;
	}

	public void setFixedPrice(Date fixedPrice)
	{
		this.fixedPrice = fixedPrice;
	}

	public Integer getIsUse()
	{
		return isUse;
	}

	public void setIsUse(Integer isUse)
	{
		this.isUse = isUse;
	}

	public Double getCountPrice()
	{
		return countPrice;
	}

	public void setCountPrice(Double countPrice)
	{
		this.countPrice = countPrice;
	}

	public Integer getSiteId()
	{
		return siteId;
	}

	public void setSiteId(Integer siteId)
	{
		this.siteId = siteId;
	}

	public String getDeviceMac()
	{
		return deviceMac;
	}

	public void setDeviceMac(String deviceMac)
	{
		this.deviceMac = deviceMac;
	}

	public String getDeviceCode()
	{
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode)
	{
		this.deviceCode = deviceCode;
	}

	public String getDeviceName()
	{
		return deviceName;
	}

	public void setDeviceName(String deviceName)
	{
		this.deviceName = deviceName;
	}
    
    
    
	
	
}
