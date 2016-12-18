package com.management.service.operate;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.common.Page;
import com.management.common.Result;
import com.management.common.util.DateUtils;
import com.management.mapper.FinanceStatisticalMapper;
import com.management.mapper.GameRunRecordMapper;
import com.management.mapper.SiteBillMapper;
import com.management.model.FinanceStatistical;
import com.management.model.vo.FinanceStatisticalVO;
import com.management.model.vo.SiteSaleVO;

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

	@Autowired
	private GameRunRecordMapper gameRunRecordMapper;

	@Autowired
	private SiteBillMapper siteBillMapper;


	public Page<FinanceStatisticalVO> findFinanceStatisticalList(Page<FinanceStatisticalVO> page,
			FinanceStatisticalVO financeStatisticalVO) {
//		if (StringUtils.isEmpty(financeStatisticalVO.getQueryDate())) {
//			financeStatisticalVO.setQueryDate(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
//		}
		List<FinanceStatisticalVO> vos = financeStatisticalMapper.findFinanceStatisticalList(page,
				financeStatisticalVO);
		return page.bulid(vos);
	}

	public Result creatFinanceStatistical(FinanceStatistical financeStatistical) {
		financeStatistical.setCreateTime(new Date());
		try {
			financeStatisticalMapper.insertFinanceStatistical(financeStatistical);
		} catch (Exception e) {
			logger.error("insertBill error", e);
			return Result.failed("服务异常");
		}
		return Result.success();
	}

	public FinanceStatistical getFinanceStatisticalByUserAndDate(String date, Integer userId) {
		return financeStatisticalMapper.getFinanceStatisticalByUserAndDate(date, userId);
	}

	public boolean isNumeric(String amount) {
		Pattern pattern = Pattern.compile("([1-9]+[0-9]*|0)(\\.[\\d]+)?");
		Matcher isNum = pattern.matcher(amount);
		return isNum.matches();
	}

	public void syncFinanceStatistical(String date) {
		List<SiteSaleVO> siteSaleList = gameRunRecordMapper.getSiteSalesAmountByAccountAndDate(date);
		if (siteSaleList == null || siteSaleList.isEmpty()) {
			logger.info("getSiteSalesAmountByAccountAndDate 未查到数据");
			return;
		}
		for (SiteSaleVO model : siteSaleList) {
			Double billAmount = siteBillMapper.findBillAmountByAccountAndDate(date,
					model.getAccount());
			if (billAmount==null) {
				billAmount = 0.0;
			}
			// 当日记账金额和当地销售额一致时，记录到财务表
			if (!Objects.isNull(model.getSalesAmount()) && model.getSalesAmount().equals(billAmount)) {
				FinanceStatistical financeStatistical = new FinanceStatistical(null, model.getUserId(),
						model.getAccount(), "定时任务同步财务记账", billAmount, new Date(), DateUtils.parseDate(date));
				creatFinanceStatistical(financeStatistical);
				logger.info("同步财务记账：场地账号，account=" + model.getAccount() + ",记账金额：" + billAmount);
			}
		}
	}
}
