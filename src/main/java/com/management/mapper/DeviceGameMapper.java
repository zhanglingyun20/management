package com.management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.management.common.Page;
import com.management.model.DeviceGame;
import com.management.model.vo.DeviceGameVO;

public interface DeviceGameMapper {
	int insertDeviceGame(DeviceGame record);
	DeviceGame findNormalDeviceGameByDeviceCodeAndGameCode(
			@Param("deviceCode")String deviceCode,@Param("gameCode")String gameCode);
	
	int updateStateByDeviceCodeAndGameCode(
			@Param("state")String state,
			@Param("deviceCode")String deviceCode,@Param("gameCode")String gameCode);
	
	
	List<DeviceGameVO> findDeviceGameList(Page<DeviceGameVO> page,@Param("record")DeviceGameVO record);
}