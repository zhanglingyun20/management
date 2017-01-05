package com.management.mapper;

import java.util.Date;
import java.util.List;

import com.management.model.vo.*;
import org.apache.ibatis.annotations.Param;

import com.management.common.Page;
import com.management.model.GameRunRecord;

public interface GameRunRecordMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(GameRunRecord record);

	GameRunRecord selectByPrimaryKey(Integer id);

	GameRunRecord selectUniqueRecordByDateAndGame(GameRunRecord record);
	
	int updateRunCountUnique(GameRunRecord record);
	
	List<DeviceReportVO> findDeviceReports(Page<DeviceReportVO> page,@Param("record")DeviceReportVO record);
	
	List<DeviceReportVO> findDeviceReportsDetailsByDateAndUniqueGame(Page<DeviceReportVO> page,@Param("record")DeviceReportVO record);
	
	List<GameVO> getDeviceGamesRunCountByPage(Page<GameVO> page,@Param("record")GameVO record);
	List<GameVO> getDeviceGamesRunCountByPage(@Param("record")GameVO record);

//	List<GameRunRecord> getDeviceGamesRunCount(@Param("deviceCode")String deviceCode);
	List<GameRunRecord> getDeviceGamesRunCount(@Param("record")DeviceVO deviceVO);

//	List<GameRunRecord> getSiteDeviceGamesRunCountByAccount(@Param("account")String account);
    List<GameRunRecord> getSiteDeviceGamesRunCountByAccount(@Param("record")SiteVO record);

	List<SiteSaleVO> getSitetGameSalesAmountByAccountAndReportDate(Page<SiteSaleVO> page,@Param("record") SiteSaleVO record );
	
	List<SiteSaleVO> getSiteSalesAmountByAccountAndDate(@Param("date") String date);
}