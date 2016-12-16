package com.management.service.operate;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.common.Page;
import com.management.common.Result;
import com.management.mapper.FinanceStatisticalMapper;
import com.management.model.FinanceStatistical;
import com.management.model.vo.FinanceStatisticalVO;

/**
 * 
 * @author sawyer
 * @date 2016年9月5日
 */
@Service
public class FinanceStatisticalService {

	private static Logger logger = Logger.getLogger(FinanceStatisticalService.class);
	
	@Autowired
	private FinanceStatisticalMapper financeStatisticalMapper;
	
	
	public void financeStatisticaJob(Date date){
		
	}
	
	public Page<FinanceStatisticalVO> findFinanceStatisticalList(Page<FinanceStatisticalVO> page,FinanceStatisticalVO financeStatisticalVO)
	{
//		if (StringUtils.isEmpty(FinanceStatisticalVO.getQueryDate())) {
//			FinanceStatisticalVO.setQueryDate(DateUtils.formatDate(new Date(),"yyyy-MM-dd"));
//		}
		List<FinanceStatisticalVO> vos = financeStatisticalMapper.findFinanceStatisticalList(page, financeStatisticalVO);
		return page.bulid(vos);
	}
	
	
	public Result creatFinanceStatistical(FinanceStatistical financeStatistical){
		financeStatistical.setCreateTime(new Date());
		try {
			financeStatisticalMapper.insertFinanceStatistical(financeStatistical);
		} catch (Exception e) {
			logger.error("insertBill error" , e);
			return Result.failed("服务异常");
		}
		return Result.success();
	}
	
	public FinanceStatistical getFinanceStatisticalByUserAndDate(String date,Integer userId){
		return financeStatisticalMapper.getFinanceStatisticalByUserAndDate(date, userId);
	}
	
	
    public  boolean isNumeric(String amount)
    {
          Pattern pattern = Pattern.compile("([1-9]+[0-9]*|0)(\\.[\\d]+)?");
          Matcher isNum = pattern.matcher(amount);
          return isNum.matches();
    }
	
}
