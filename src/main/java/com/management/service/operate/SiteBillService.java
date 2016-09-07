package com.management.service.operate;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.common.Page;
import com.management.common.Result;
import com.management.common.util.DateUtils;
import com.management.mapper.SiteBillMapper;
import com.management.model.GameRunRecord;
import com.management.model.SiteBill;
import com.management.model.vo.SiteBillVO;

/**
 * 
 * @author sawyer
 * @date 2016年9月5日
 */
@Service
public class SiteBillService {

	private static Logger logger = Logger.getLogger(SiteBillService.class);
	
	@Autowired
	private SiteBillMapper siteBillMapper;
	
	public Page<SiteBillVO> findSiteBillList(Page<SiteBillVO> page,SiteBillVO siteBillVO)
	{
//		if (StringUtils.isEmpty(siteBillVO.getQueryDate())) {
//			siteBillVO.setQueryDate(DateUtils.formatDate(new Date(),"yyyy-MM-dd"));
//		}
		List<SiteBillVO> vos = siteBillMapper.findSiteBillList(page, siteBillVO);
		return page.bulid(vos);
	}
	
	
	public Result creatSiteBill(SiteBill siteBill,String date,String amount){
		if (StringUtils.isEmpty(date)) {
			return Result.failed("日期格式错误");
		}
		Date billDate = null;
		try {
			billDate = DateUtils.parseDate(date, "yyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			logger.error("creatBill date format error...{}" , e);
			return Result.failed("日期格式错误");
		}
		if (StringUtils.isEmpty(amount)||!isNumeric(amount)) {
			return Result.failed("金额格式错误");
		}
		
		if(StringUtils.isNoneEmpty(siteBill.getRemark())&&siteBill.getRemark().length()>500){
			return Result.failed("备注不能超过500个字符");
		}
		siteBill.setAmount(Double.valueOf(amount));
		siteBill.setCreateTime(new Date());
		siteBill.setBillDate(billDate);
		try {
			siteBillMapper.insertSiteBill(siteBill);
		} catch (Exception e) {
			logger.error("insertBill error" , e);
			return Result.failed("服务异常");
		}
		return Result.success();
	}
	
	public Result updateRemark(String id,String remark){
		if (id==null) {
			return Result.failed("请选择修改记录");
		}
		if(StringUtils.isNoneEmpty(remark)&&remark.length()>500){
			return Result.failed("备注不能超过500个字符");
		}
		try {
			siteBillMapper.updateRemark(id, remark);
		} catch (Exception e) {
			logger.error("updateRemark error" , e);
			return Result.failed("服务异常");
		}
		return Result.success();
	}
	
    public  boolean isNumeric(String amount)
    {
          Pattern pattern = Pattern.compile("([1-9]+[0-9]*|0)(\\.[\\d]+)?");
          Matcher isNum = pattern.matcher(amount);
          return isNum.matches();
    }
	
}
