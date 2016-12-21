package com.management.service.common;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.management.mapper.SiteMapper;
import com.management.model.GameRunRecord;

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

	public HSSFWorkbook reportExcel(String downLoadType,Object object) {

		switch (downLoadType) {
		case "finance":
			return reportFinanceExcel();
		case "siteSales":
			return siteSalesExcel((SiteVO)object);
		case "deviceSales":
			return deviceSalesExcel((DeviceVO) object);
		case "deviceSalesDetail":
			return deviceSalesDetailExcel((GameVO)object);
		default:
			break;
		}
		return null;
	}
	/**
	 * Author ：zhxy
	 * 说明：设备销售额
	 */

	private HSSFWorkbook deviceSalesDetailExcel(GameVO object) {
		List<GameVO> detailList = saleService.getDeviceGamesSalesForExport(object);
		String title="游戏销售";
		String[] rowsName = new String[]{"序号","游戏名称","游戏编号","单价","运行次数","总销售额(元)"};
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] objs ;
		for(int i=0;i<detailList.size();i++){
			GameVO gameVO = detailList.get(i);
			objs = new Object[rowsName.length];
			objs[0]=i;
			objs[1]=gameVO.getGameName();
			objs[2]=gameVO.getGameCode();
			objs[3]=gameVO.getPrice();
			objs[4]=gameVO.getRunCount();
			objs[5]=gameVO.getSalesAmount();
			dataList.add(objs);
		}
		return new ExportExcel(title, rowsName, dataList).genetrateExcel();
	}

	/**
	 * Author ：zhxy
	 * 说明：设备销售
	 */
	private HSSFWorkbook deviceSalesExcel(DeviceVO object) {
		List<DeviceVO> deviceSalesList = saleService.getDeviceSalesForExport(object);
		String title="设备销售";
		String[] rowsName = new String[]{"序号","设备编号","设备名称","总销售额(元)"};
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] objs ;
		for(int i=0;i<deviceSalesList.size();i++){
			DeviceVO deviceVO = deviceSalesList.get(i);
			objs = new Object[rowsName.length];
			objs[0]=i;
			objs[1]=deviceVO.getDeviceCode();
			objs[2]=deviceVO.getDeviceName();
			objs[3]=deviceVO.getSalesAmount();
			dataList.add(objs);
		}
		return new ExportExcel(title, rowsName, dataList).genetrateExcel();
	}

	/**
	 * Author ：zhxy
	 * 说明：场地销售额
	 */
	private HSSFWorkbook siteSalesExcel(SiteVO record) {
		List<SiteVO>  siteSaleList = saleService.getBySiteByAccountAndSiteNameForExport(record);
		String title ="场地销售";
		String[] rowsName = new String[]{"序号","场地账号","场地名称","总销售额(元)"};
		List<Object[]> dataList = new ArrayList<Object[]>();
		Object[] objs ;
		for(int i=0;i<siteSaleList.size();i++){
			SiteVO siteVo = siteSaleList.get(i);
			objs = new Object[rowsName.length];
			objs[0]=i;
			objs[1]=siteVo.getAccount();
			objs[2]=siteVo.getSiteName();
			objs[3]=siteVo.getSalesAmount();
			dataList.add(objs);
		}
		return new ExportExcel(title, rowsName, dataList).genetrateExcel();
	}

	/**
	 * Author ：zhxy
	 * 说明：账单
	 */

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
