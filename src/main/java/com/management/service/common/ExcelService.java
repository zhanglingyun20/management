package com.management.service.common;

import java.util.ArrayList;
import java.util.List;

import com.management.model.vo.DeviceVO;
import com.management.model.vo.GameVO;
import com.management.model.vo.SiteVO;
import com.management.service.sales.SaleService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.management.common.util.DateUtils;
import com.management.common.util.ExportExcel;
import com.management.mapper.FinanceStatisticalMapper;
import com.management.model.vo.FinanceStatisticalVO;

@Component
public class ExcelService {

	@Autowired
	private FinanceStatisticalMapper financeStatisticalMapper;
	@Autowired
	private SaleService saleService;



	public HSSFWorkbook reportExcel(String downLoadType,Object record) {

		switch (downLoadType) {
		case "finance":
			return reportFinanceExcel();
		case "sitesales":
			return reportSiteSalesExcel((SiteVO)record);
		case "devicesales":
			return reportDeviceSalesExcel((DeviceVO)record);
		case "salesdetail":
			return reportSalesDetailExcel((GameVO)record);
		default:
			break;
		}
		return null;
	}





	public HSSFWorkbook reportFinanceExcel() {
		List<FinanceStatisticalVO> financeList = financeStatisticalMapper.findFinanceStatisticalAll();
		String title = "财务统计";
		String[] rowsName = new String[] { "序号", "场地名称", "金额", "交易日期", "录入时间", "备注" };
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] objs = null;
		for (int i = 0; i < financeList.size(); i++) {
			FinanceStatisticalVO model = financeList.get(i);
			objs = new Object[rowsName.length];
			objs[0] = i;
			objs[1] = model.getSiteName();
			objs[2] = model.getAmount();
			objs[3] = "";
			try {
				objs[3] = model.getBillDate()==null?"":DateUtils.formatDate(model.getBillDate(), "yyyy-MM-dd");
			} catch (Exception e) {
				objs[3] = "";
			}
			objs[4] = "";
			try {
				objs[4] = model.getCreateTime()==null?"":DateUtils.formatDate(model.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
			} catch (Exception e) {
				objs[4]= "";
			}
			objs[5] = model.getRemark();
			dataList.add(objs);
		}
		return new ExportExcel(title, rowsName, dataList).genetrateExcel();
	}

	private HSSFWorkbook reportSiteSalesExcel(SiteVO record) {
		saleService.getBySiteByAccountAndSiteName(null,record);
		String title = "場地銷售額";
		String[] rowsName = new String[] { "序号", "場地賬號","场地名称", "总销售额(元)" };
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] objs = null;
		return null;
	}
	private HSSFWorkbook reportDeviceSalesExcel(DeviceVO record) {
		return null;
	}
	private HSSFWorkbook reportSalesDetailExcel(GameVO record) {
		return null;
	}
}
