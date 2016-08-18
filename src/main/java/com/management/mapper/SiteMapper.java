package com.management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.management.common.Page;
import com.management.model.Game;
import com.management.model.Site;
import com.management.model.vo.SiteVO;

public interface SiteMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Site record);

	int insertSelective(Site record);

	Site selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Site record);

	int updateByPrimaryKey(Site record);
	
	Site selectByUserId(Integer userId);
	
	List<SiteVO> findSites(Page<SiteVO> page,@Param("site")SiteVO siteVO);
	
	Site selectBySiteByAccount(@Param("account")String account);
	
	
}