package com.management.service.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.common.Page;
import com.management.mapper.DeviceMapper;
import com.management.mapper.GameMapper;
import com.management.mapper.GameRunRecordMapper;
import com.management.mapper.SiteMapper;
import com.management.model.Device;
import com.management.model.Game;
import com.management.model.Site;
import com.management.model.vo.DeviceDetailsSalesVO;
import com.management.model.vo.DeviceReportVO;
import com.management.model.vo.DeviceSalesVO;

@Service
public class ReportService
{
	private static Logger logger = Logger.getLogger(ReportService.class);
	
	@Autowired
	private GameRunRecordMapper gameRunRecordMapper;
	
	@Autowired
	private SiteMapper  siteMapper;
	
	@Autowired
	private GameMapper  gameMapper;
	
	@Autowired
	private DeviceMapper  deviceMapper;
	
	
	public Page<DeviceReportVO> findDeviceReports(Page<DeviceReportVO> page,DeviceReportVO record)
	{
		List<DeviceReportVO> vos = gameRunRecordMapper.findDeviceReports(page, record);
		if (vos!=null&&!vos.isEmpty()) {
			for (DeviceReportVO deviceReportVO : vos) {
				String account = deviceReportVO.getAccount();
				Site site = siteMapper.selectBySiteByAccount(account);
				if (site!=null) {
					deviceReportVO.setSiteName(site.getSiteName());
					deviceReportVO.setSiteLevel(site.getSiteLevel());
				}
				String deviceMac = deviceReportVO.getDeviceMac();
				String gameCode = deviceReportVO.getGameCode();
				Game game = gameMapper.selectByGameCode(gameCode);
				if (game!=null) {
					deviceReportVO.setGameName(game.getGameName());
				}
				Device device= deviceMapper.selectByDeviceMac(deviceMac);
				if (device!=null) {
					deviceReportVO.setDeviceName(device.getDeviceName());
				}
			}
		}
		return page.bulid(vos);
	}
	
	public Page<DeviceReportVO> findDeviceReportsDetailsByDateAndUniqueGame(Page<DeviceReportVO> page,DeviceReportVO record)
	{
		return page.bulid(gameRunRecordMapper.findDeviceReportsDetailsByDateAndUniqueGame(page, record));
	}
	
	
	public Page<DeviceSalesVO> findDeviceSales(Page<DeviceSalesVO> page,@Param("record")DeviceSalesVO record){
		List<DeviceReportVO> reportList = gameRunRecordMapper.findDeviceSales(page, record);
		List<DeviceSalesVO> resultList = new ArrayList<DeviceSalesVO>();
		if (reportList!=null&&!reportList.isEmpty()) {
			Map<String,DeviceSalesVO> salesMap = new HashMap<String,DeviceSalesVO>();
			for (DeviceReportVO model : reportList) {
				if (salesMap.containsKey(model.getDeviceCode())) {
					DeviceSalesVO sales  = salesMap.get(model.getDeviceCode());
					double salesCount = sales.getSales();
					Game game = gameMapper.selectByGameCode(model.getGameCode());
					int runCount = model.getRunCount();
					double price = 0;
					if (game!=null) {
						price = game.getDefaultPrice();
					}
					double gameSales = runCount*price;
					sales.setSales(gameSales+salesCount);
					salesMap.put(model.getDeviceCode(), sales);
				}else{
					DeviceSalesVO sales = new DeviceSalesVO();
					sales.setDeviceCode(model.getDeviceCode());
					sales.setDeviceName(model.getDeviceName());
					Game game = gameMapper.selectByGameCode(model.getGameCode());
					int runCount = model.getRunCount();
					double price = 0;
					if (game!=null) {
						price = game.getDefaultPrice();
					}
					sales.setSales(runCount*price);
					salesMap.put(model.getDeviceCode(), sales);
				}
			}
			
			if (!salesMap.isEmpty()) {
				for (Entry<String, DeviceSalesVO> deviceSalesVO : salesMap.entrySet()) {
					resultList.add(deviceSalesVO.getValue());
				}
			}
		}
		page.setResults(resultList.size());
		return page.bulid(resultList);
	}
	
	
	
	
	public Page<DeviceDetailsSalesVO> findDeviceSalesDetails(Page<DeviceDetailsSalesVO> page,@Param("record")DeviceDetailsSalesVO record){
		List<DeviceReportVO> reportList = gameRunRecordMapper.findDeviceSalesDetails(page, record);
		List<DeviceDetailsSalesVO> resultList = new ArrayList<DeviceDetailsSalesVO>();
		if (reportList!=null&&!reportList.isEmpty()) {
			Map<String,DeviceDetailsSalesVO> salesMap = new HashMap<String,DeviceDetailsSalesVO>();
			for (DeviceReportVO model : reportList) {
				if (salesMap.containsKey(model.getGameCode())) {
					DeviceDetailsSalesVO detailSales  = salesMap.get(model.getGameCode());
					double salesCount = detailSales.getSales();
					Game game = gameMapper.selectByGameCode(model.getGameCode());
					int runCount = model.getRunCount();
					double price = 0;
					if (game!=null) {
						price = game.getDefaultPrice();
					}
					double gameSales = runCount*price;
					detailSales.setSales(gameSales+salesCount);
					salesMap.put(model.getGameCode(), detailSales);
				}else{
					DeviceDetailsSalesVO detailSales = new DeviceDetailsSalesVO();
					detailSales.setGameCode(model.getGameCode());
					detailSales.setGameName(model.getGameName());
					Game game = gameMapper.selectByGameCode(model.getGameCode());
					int runCount = model.getRunCount();
					double price = 0;
					if (game!=null) {
						price = game.getDefaultPrice();
						detailSales.setPrice(game.getDefaultPrice());
					}
					detailSales.setRunCount(runCount);
					detailSales.setSales(runCount*price);
					salesMap.put(model.getGameCode(), detailSales);
				}
			}
			
			if (!salesMap.isEmpty()) {
				for (Entry<String, DeviceDetailsSalesVO> deviceSalesVO : salesMap.entrySet()) {
					resultList.add(deviceSalesVO.getValue());
				}
			}
		}
		page.setResults(resultList.size());
		return page.bulid(resultList);
	}
	
}
