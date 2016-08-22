package com.management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.management.common.Page;
import com.management.model.GameRunRecord;
import com.management.model.vo.DeviceDetailsSalesVO;
import com.management.model.vo.DeviceReportVO;
import com.management.model.vo.DeviceSalesVO;

public interface GameRunRecordMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(GameRunRecord record);

	GameRunRecord selectByPrimaryKey(Integer id);

	GameRunRecord selectUniqueRecordByDateAndGame(GameRunRecord record);
	
	int updateRunCountUnique(GameRunRecord record);
	
	List<DeviceReportVO> findDeviceReports(Page<DeviceReportVO> page,@Param("record")DeviceReportVO record);
	
	List<DeviceReportVO> findDeviceReportsDetailsByDateAndUniqueGame(Page<DeviceReportVO> page,@Param("record")DeviceReportVO record);
	
	List<DeviceReportVO> findDeviceSales(Page<DeviceSalesVO> page,@Param("record")DeviceSalesVO record);
	
	List<DeviceReportVO> findDeviceSalesDetails(Page<DeviceDetailsSalesVO> page,@Param("record")DeviceDetailsSalesVO record);
	
}