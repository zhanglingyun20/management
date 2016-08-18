package com.management.mapper;

import org.apache.ibatis.annotations.Param;

import com.management.model.Device;

public interface DeviceMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Device record);

	int insertSelective(Device record);

	Device selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Device record);

	int updateByPrimaryKey(Device record);
	
	Device selectByDeviceMac(@Param("deviceMac")String deviceMac);
}