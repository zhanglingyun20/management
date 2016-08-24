package com.management.service.sync;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.common.Result;
import com.management.mapper.GameMapper;
import com.management.mapper.GameRunRecordMapper;
import com.management.mapper.DeviceMapper;
import com.management.mapper.SiteMapper;
import com.management.mapper.UsersMapper;
import com.management.model.DeviceInfo;
import com.management.model.Game;
import com.management.model.GameRunRecord;
import com.management.model.Site;
import com.management.model.Device;
import com.management.model.Users;
import com.management.service.common.GameRecordVO;
import com.management.service.common.GameRunRecordRequest;
import com.management.service.common.MessageHelperService;

/**
 * 
 * <b>Descriptions:</b>
 * <p>
 * </p>
 * 
 * @author sawyer
 * @createDate 2016年8月6日
 * @file SyncDataService.java
 * @package com.management.service.sync
 * @project management
 * @version 1.0
 *
 */
@Service
public class SyncDataService {

	private static Logger logger = Logger.getLogger(SyncDataService.class);

	@Autowired
	private UsersMapper usersMapper;

	@Autowired
	private MessageHelperService messageHelperService;

	@Autowired
	private DeviceMapper siteDeviceMapper;

	@Autowired
	private GameMapper gameMapper;

	@Autowired
	private GameRunRecordMapper gameRunRecordMapper;
	
	
	@Autowired
	private SiteMapper siteMapper;

	/**
	 * 
	 * <b>同步游戏运行记录:</b>
	 * <p>
	 * </p>
	 * 
	 * @author sawyer
	 * @param data
	 * @return
	 * @createDate 2016年8月6日
	 *
	 */
	public Result syncGameRunData(GameRunRecordRequest gameRunRecordRequest)
	{
		if (gameRunRecordRequest != null) {
			List<GameRecordVO> recordVOs = gameRunRecordRequest.getRecord();
			if (recordVOs == null || recordVOs.isEmpty()) { return Result.success(); }
			Users user = usersMapper.selectByAccount(gameRunRecordRequest.getAccount());
			if (user == null) { return Result.failed(messageHelperService.getUnknownAccount()); }
			batchCreateGameRecord(bulidGameRunrecord(gameRunRecordRequest));
		}
		return Result.success();
	}

	public int batchCreateGameRecord(List<GameRunRecord> records)
	{
		if (records != null && !records.isEmpty()) {
			for (GameRunRecord gameRunRecord : records) {
//				GameRunRecord oldRecord = gameRunRecordMapper.selectUniqueRecordByDateAndGame(gameRunRecord);
//				if (oldRecord == null) {
//					gameRunRecordMapper.insert(gameRunRecord);
//				}
//				else {
//					Integer oldCount = null == oldRecord.getRunCount()? 0 : oldRecord.getRunCount();
//					Integer newCount = null == gameRunRecord.getRunCount() ? 0 : gameRunRecord.getRunCount();
//					oldRecord.setRunCount(oldCount + newCount);
//					gameRunRecordMapper.updateRunCountUnique(oldRecord);
//				}
				gameRunRecordMapper.insert(gameRunRecord);
			}
		}
		return 1;
	}

	private List<GameRunRecord> bulidGameRunrecord(GameRunRecordRequest gameRunRecordRequest)
	{
		List<GameRunRecord> records = new ArrayList<GameRunRecord>();
		List<GameRecordVO> recordVOs = gameRunRecordRequest.getRecord();
		for (GameRecordVO vo : recordVOs) 
		{
			GameRunRecord record = new GameRunRecord();
			record.setAccount(gameRunRecordRequest.getAccount());
			record.setDeviceCode(gameRunRecordRequest.getDeviceCode());
			record.setDeviceMac(gameRunRecordRequest.getDeviceMac());
			record.setDeviceName(gameRunRecordRequest.getDeviceName());
			record.setGameCode(vo.getGameCode());
			record.setGameName(vo.getGameName());
			record.setCreateTime(vo.getCreateTime());
			record.setUpdateTime(gameRunRecordRequest.getUploadTime());
			record.setReportTime(gameRunRecordRequest.getUploadTime());
			record.setGameProcess(vo.getGameProcess());
			record.setRunCount(vo.getRunCount()==null?1:vo.getRunCount());
			records.add(record);
		}
		return records;
	}
	
	
	public List<Game> getAllGames()
	{
		return gameMapper.selectAllGames(Game.State.ACTIVE.getValue());
	}
	
	
	public Result activaAccount(DeviceInfo device) {

		Device result = siteDeviceMapper.selectByDeviceCode(device
				.getDeviceCode());
		if (result != null) {
			return Result.failed("已激活");
		}
		Users vidata = usersMapper.selectByAccountAndPwd(device.getAccount(),
				device.getPassword());
		if (vidata != null) {
			
			if (!Users.Type.SITE.getValue().equals(vidata.getUserType())) {
				return Result.failed("未知的用户");
			}
			// 绑定场地账号和设备信息
			Device record = new Device();
			Site site = siteMapper.selectByUserId(vidata.getId());
			record.setSiteId(site == null ? 0 : site.getId());
			record.setDeviceCode(device.getDeviceCode());
			record.setCreateTime(new Date());
			record.setDeviceMac(device.getDeviceMac());
			record.setDeviceName(device.getDeviceName());
			siteDeviceMapper.insert(record);
			return Result.success();
		}
		return Result.failed("未知的用户");
	}
}
