package com.management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.management.common.Page;
import com.management.model.Bill;
import com.management.model.SiteBill;
import com.management.model.vo.SiteBillVO;

public interface SiteBillMapper {
	int insertSiteBill(SiteBill record);
	Bill findSiteBillById(Integer id);
	
	List<SiteBillVO> findSiteBillList(Page<SiteBillVO> page,@Param("record")SiteBillVO record);
	
	int updateRemark(@Param("id")String id,@Param("remark")String remark);
}