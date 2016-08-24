package com.management.service.report;

import java.util.List;

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
import com.management.model.vo.DeviceReportVO;

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
	
}
