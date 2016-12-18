package com.management.job;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.management.service.operate.FinanceStatisticalService;

@Component
public class FinanceJob {

	private static Logger logger = Logger.getLogger(FinanceJob.class);
	
	@Autowired
	private FinanceStatisticalService financeStatisticalService;

	@Scheduled(cron = "0 0 1 * * ?")
	public void compareFinance() {
		logger.info("同步财务统计定时任务开始");
		Date date = DateUtils.addDays(new Date(), -1);
		financeStatisticalService.syncFinanceStatistical(com.management.common.util.DateUtils.formatDate(date, null));
//		financeStatisticalService.syncFinanceStatistical("2016-08-15");
		logger.info("同步财务统计定时任务结束");
	}
}
