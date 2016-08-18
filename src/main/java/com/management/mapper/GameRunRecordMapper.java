package com.management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.management.common.Page;
import com.management.model.GameRunRecord;
import com.management.model.vo.DeviceReportVO;

public interface GameRunRecordMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(GameRunRecord record);

	GameRunRecord selectByPrimaryKey(Integer id);

	GameRunRecord selectUniqueRecordByDateAndGame(GameRunRecord record);
	
	int updateRunCountUnique(GameRunRecord record);
	
	List<DeviceReportVO> findDeviceReports(Page<DeviceReportVO> page,@Param("record")DeviceReportVO record);
	
	List<DeviceReportVO> findDeviceReportsDetailsByDateAndUniqueGame(Page<DeviceReportVO> page,@Param("record")DeviceReportVO record);
}