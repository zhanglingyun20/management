package com.management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.management.common.Page;
import com.management.model.Device;
import com.management.model.vo.DeviceVO;

public interface DeviceMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Device record);

	int insertSelective(Device record);

	Device selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Device record);

	int updateByPrimaryKey(Device record);
	
	Device selectByDeviceMac(@Param("deviceMac")String deviceMac);
	
	Device selectByDeviceCode(@Param("deviceCode")String deviceCode);
	
	List<DeviceVO> getDevicesByNameAndCode(Page<DeviceVO> page,@Param("record")DeviceVO record);
}