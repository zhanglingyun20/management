package com.management.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.management.common.Page;
import com.management.model.FinanceStatistical;
import com.management.model.vo.FinanceStatisticalVO;

public interface FinanceStatisticalMapper {
	int insertFinanceStatistical(FinanceStatistical record);
	FinanceStatistical findFinanceStatisticalById(Integer id);
	
	List<FinanceStatisticalVO> findFinanceStatisticalList(Page<FinanceStatisticalVO> page,@Param("record")FinanceStatisticalVO record);
	
	int updateRemark(@Param("id")String id,@Param("remark")String remark);
	
	Double findBillAmountByAccountAndDate(@Param("queryDate")String queryDate,@Param("account")String account);
	
	
	FinanceStatistical getFinanceStatisticalByUserAndDate(@Param("billDate")String billDate,@Param("userId")Integer userId);
	
}