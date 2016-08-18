package com.management.mapper;

import org.apache.ibatis.annotations.Param;

import com.management.model.GamePrice;

public interface GamePriceMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(GamePrice record);

	int insertSelective(GamePrice record);

	GamePrice selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(GamePrice record);

	int updateByPrimaryKey(GamePrice record);
	
	int findFixGameCount(@Param("account")String account,@Param("isUse")String isUse);
}