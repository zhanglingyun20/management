package com.management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.management.common.Page;
import com.management.model.Bill;
import com.management.model.vo.BillVO;

public interface BillMapper {
	int insertBill(Bill record);
	Bill findBillById(Integer id);
	
	List<BillVO> findBillList(Page<BillVO> page,@Param("record")BillVO record);
}