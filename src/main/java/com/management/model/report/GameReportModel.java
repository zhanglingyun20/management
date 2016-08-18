package com.management.model.report;


public class GameReportModel {

	
    /**
     * 游戏编码
     */
    private String gameCode;

    /**
     * 游戏名称
     */
    private String gameName;

    /**
     * 游戏版本
     */
	private String gameVersion;

    /**
     * 游戏进程
     */
    private String gameProcess;
    
    private Integer runCount;
    
    private Double amount;
    
    private Double price;

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

	public String getGameVersion() {
		return gameVersion;
	}

	public void setGameVersion(String gameVersion) {
		this.gameVersion = gameVersion;
	}

	public String getGameProcess() {
		return gameProcess;
	}

	public void setGameProcess(String gameProcess) {
		this.gameProcess = gameProcess;
	}

	public Integer getRunCount() {
		return runCount;
	}

	public void setRunCount(Integer runCount) {
		this.runCount = runCount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public GameReportModel() {
		super();
	}

	@Override
	public String toString() {
		return "GameReportModel [gameCode=" + gameCode + ", gameName="
				+ gameName + ", gameVersion=" + gameVersion + ", gameProcess="
				+ gameProcess + ", runCount=" + runCount + ", amount=" + amount
				+ ", price=" + price + "]";
	}

	public GameReportModel(String gameCode, String gameName,
			String gameVersion, String gameProcess, Integer runCount,
			Double amount, Double price) {
		super();
		this.gameCode = gameCode;
		this.gameName = gameName;
		this.gameVersion = gameVersion;
		this.gameProcess = gameProcess;
		this.runCount = runCount;
		this.amount = amount;
		this.price = price;
	}
    
}
