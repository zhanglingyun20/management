package com.management.service.sales;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.management.service.sync.SyncDataService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.common.Page;
import com.management.common.util.DateUtils;
import com.management.mapper.BillMapper;
import com.management.mapper.DeviceMapper;
import com.management.mapper.GameMapper;
import com.management.mapper.GameRunRecordMapper;
import com.management.mapper.SiteBillMapper;
import com.management.mapper.SiteMapper;
import com.management.model.Game;
import com.management.model.GameRunRecord;
import com.management.model.vo.DeviceVO;
import com.management.model.vo.GameVO;
import com.management.model.vo.SiteBillVO;
import com.management.model.vo.SiteSaleVO;
import com.management.model.vo.SiteVO;

@Service
public class SaleService {

	@Autowired
	private DeviceMapper  deviceMapper;
	
	@Autowired
	private SiteMapper  siteMapper;
	
	@Autowired
	private GameMapper  gameMapper;
	
	@Autowired
	private GameRunRecordMapper  gameRunRecordMapper;
	
	@Autowired
	private SiteBillMapper  siteBillMapper;
	
	
	/**
	 * Author ：zhxy
	 * 说明：设备销售展示
	 */
	public Page<DeviceVO> getDeviceSales(Page<DeviceVO> page,@Param("record")DeviceVO record){
		List<DeviceVO> deviceList = deviceMapper.getDevicesByNameAndCode(page, record);
		buildDeviceSalesList(deviceList,record);
		return page.bulid(deviceList);
	}
	/**
	 * Author ：zhxy
	 * 说明：设备销售导出
	 */
	public List<DeviceVO> getDeviceSalesForExport(@Param("record")DeviceVO record){
		List<DeviceVO> deviceList = deviceMapper.getDevicesByNameAndCode(record);
		buildDeviceSalesList(deviceList,record);
		return  deviceList;
	}

	private void buildDeviceSalesList(List<DeviceVO> deviceList ,DeviceVO record){
		if (deviceList!=null&&!deviceList.isEmpty()) {
			boolean isToday = (record.getStartDate()==null||"".equals(record.getStartDate()))
					&&(record.getEndDate()==null)||"".equals(record.getEndDate());
			DateFormat d2 = DateFormat.getDateInstance();
			String date = d2.format(System.currentTimeMillis());
			for (DeviceVO deviceVO : deviceList) {
				if(deviceVO==null) continue;
				if(isToday){
					deviceVO.setStartDate(date);
					deviceVO.setEndDate(date);
				}else{
					deviceVO.setStartDate(record.getStartDate());
					deviceVO.setEndDate(record.getEndDate());
				}
				List<GameRunRecord>  recordList = gameRunRecordMapper.getDeviceGamesRunCount(deviceVO);
				if (recordList!=null&&!recordList.isEmpty()) {
					for (GameRunRecord gameRunRecord : recordList)
					{
						double salesCount = deviceVO.getSalesAmount()==null?0:deviceVO.getSalesAmount();
						Game game = gameMapper.selectByGameCode(gameRunRecord.getGameCode());
						int runCount = gameRunRecord.getRunCount();
						double price = 0;
						if (game!=null) {
							price = game.getDefaultPrice()==null?0:game.getDefaultPrice();
						}
						double gameSales = runCount*price;
						deviceVO.setSalesAmount(gameSales+salesCount);
					}
				}
			}
		}
	}

	/**
	 * Author ：zhxy
	 * 说明：场地游戏销售额
	 */

	public Page<GameVO> getDeviceGamesSales(Page<GameVO> page,@Param("record")GameVO record){
		List<GameVO> gameRecordList = gameRunRecordMapper.getDeviceGamesRunCountByPage(page, record);
		buildDeviceGamesSalesList(gameRecordList);
		return page.bulid(gameRecordList);
	}

	public List<GameVO> getDeviceGamesSalesForExport(@Param("record")GameVO record){
		List<GameVO> gameRecordList = gameRunRecordMapper.getDeviceGamesRunCountByPage(record);
		buildDeviceGamesSalesList(gameRecordList);
		return gameRecordList;
	}

	private void buildDeviceGamesSalesList(List<GameVO> gameRecordList){
		if (gameRecordList!=null&&!gameRecordList.isEmpty()) {
			for (GameVO gameVO : gameRecordList) {
				double salesCount = gameVO.getSalesAmount()==null?0:gameVO.getSalesAmount();
				Game game = gameMapper.selectByGameCode(gameVO.getGameCode());
				if (game==null) {
					continue;
				}
				int runCount = gameVO.getRunCount();
				double price = 0;
				if (game!=null) {
					price = game.getDefaultPrice();
				}
				double gameSales = runCount*price;
				gameVO.setSalesAmount(gameSales+salesCount);
				gameVO.setPrice(null==game.getDefaultPrice()?0d:game.getDefaultPrice());
				gameVO.setGameName(game.getGameName());

			}
		}
	}
	/**
	 * Author ：zhxy
	 * 说明：siteSales 获取展示列表
	 */
	public Page<SiteVO> getBySiteByAccountAndSiteName(Page<SiteVO> page,@Param("record")SiteVO record){
		List<SiteVO> siteList = siteMapper.getBySiteByAccountAndSiteName(page, record);
		buildSiteSalesList(siteList,record);
		return page.bulid(siteList);
	}

	/**
	 * Author ：zhxy
	 * 说明：siteSales 获取导出列表
	 */
	public List<SiteVO> getBySiteByAccountAndSiteNameForExport(SiteVO record){
		List<SiteVO> siteList = siteMapper.getBySiteByAccountAndSiteName(record);
		buildSiteSalesList(siteList,record);
		return  siteList;
	}
	/**
	 * Author ：zhxy
	 * 说明：siteSales 封装数据
	 */
	private void buildSiteSalesList(List<SiteVO> siteList,SiteVO record){
		DateFormat d2 = DateFormat.getDateInstance();
		String date = d2.format(System.currentTimeMillis());
		if (siteList!=null&&!siteList.isEmpty()) {
			for (SiteVO siteVO : siteList) {
				if (siteVO==null) {
					continue;
				}
				record.setAccount(siteVO.getAccount());
				if((record.getStartDate()==null||"".equals(record.getStartDate()))
						&&(record.getEndDate()==null)||"".equals(record.getEndDate())){
					record.setStartDate(date);
					record.setEndDate(date);
				}
				String account = siteVO.getAccount();
				List<GameRunRecord> recordList = gameRunRecordMapper.getSiteDeviceGamesRunCountByAccount(record);
				if (recordList!=null&&!recordList.isEmpty()) {
					Map<String,Double>  siteSaleMap = setSiteSales(recordList);
					if (siteSaleMap.containsKey(account)) {
						siteVO.setSalesAmount(siteSaleMap.get(account)==null?0d:siteSaleMap.get(account));
					}else{
						siteVO.setSalesAmount(0d);
					}

				}
			}
		}
	}
	/**
	 * 
	 * @author sawyer
	 * @date 2016年8月23日
	 * @param recordList 场地运行记录
	 * @return 场地账号-->销售额
	 */
	private Map<String,Double> setSiteSales(List<GameRunRecord> recordList){
		Map<String,Double> siteMap = new HashMap<String,Double>();
		for (GameRunRecord gameRunRecord : recordList) {
			String siteMapKey= gameRunRecord.getAccount();
			if (siteMap.containsKey(siteMapKey)) 
			{
				double sumSales = siteMap.get(siteMapKey);
				int runCount = gameRunRecord.getRunCount()==null?0:gameRunRecord.getRunCount();
				double gameSales = calculateGameSale(runCount, gameRunRecord.getGameCode());
				siteMap.put(siteMapKey, sumSales += gameSales);
				
			}else
			{
				//计算此场地游戏的 销售额
				int runCount = gameRunRecord.getRunCount()==null?0:gameRunRecord.getRunCount();
				double gameSales = calculateGameSale(runCount, gameRunRecord.getGameCode());
				siteMap.put(siteMapKey, gameSales);
			}
		}
		return siteMap;
	}
	
	/**
	 * 
	 * @author sawyer
	 * @date 2016年8月23日
	 * @param runCount 运行次数
	 * @param gameCode 游戏编码
	 * @return
	 */
	private Double calculateGameSale(int runCount,String gameCode){
		Game game = gameMapper.selectByGameCode(gameCode);
		if (game==null) {
			return 0d;
		}
		double price = game.getDefaultPrice()==null?0:game.getDefaultPrice();
		return  runCount*price;
	}
	
	
	public Page<SiteSaleVO> getSitetGameSalesAmountByAccountAndReportDate(Page<SiteSaleVO> page,SiteSaleVO siteGameSaleVO ){
		if (StringUtils.isEmpty(siteGameSaleVO.getQueryDate())) {
			siteGameSaleVO.setQueryDate(DateUtils.formatDate(new Date(),"yyyy-MM-dd"));
		}
		List<SiteSaleVO> list= gameRunRecordMapper.getSitetGameSalesAmountByAccountAndReportDate(page, siteGameSaleVO);
		if (list!=null&&!list.isEmpty()) {
			for (SiteSaleVO model : list) {
				Double billAmount = siteBillMapper.findBillAmountByAccountAndDate(siteGameSaleVO.getQueryDate(), model.getAccount());
				model.setBillAmount(billAmount==null?0:billAmount);
			}
		}
		return page.bulid(list);
	}
	
	
}
