package com.management.model;

import java.util.Date;

public class DeviceGame {
    private Integer id;
    

    private Integer deviceId; 
    
    private String deviceCode;
    
    private String state;
    
    private Integer gameId; 
    
    private Date createTime;
    /**
     * 游戏编码
     */
    private String gameCode;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceCode() {
		return deviceCode;
	}
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getGameId() {
		return gameId;
	}
	public void setGameId(Integer gameId) {
		this.gameId = gameId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getGameCode() {
		return gameCode;
	}
	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}
	@Override
	public String toString() {
		return "DeviceGame [id=" + id + ", deviceId=" + deviceId
				+ ", deviceCode=" + deviceCode + ", state=" + state
				+ ", gameId=" + gameId + ", createTime=" + createTime
				+ ", gameCode=" + gameCode + "]";
	}
	public DeviceGame(Integer id, Integer deviceId, String deviceCode,
			String state, Integer gameId, Date createTime, String gameCode) {
		super();
		this.id = id;
		this.deviceId = deviceId;
		this.deviceCode = deviceCode;
		this.state = state;
		this.gameId = gameId;
		this.createTime = createTime;
		this.gameCode = gameCode;
	}
	public DeviceGame() {
		super();
	}
	
	
	public enum State {
		NORMAL("normal"), INVALID("invalid");

		private final String value;

		State(String value)
		{
			this.value = value;
		}

		public String getValue()
		{
			return value;
		}
	}
}