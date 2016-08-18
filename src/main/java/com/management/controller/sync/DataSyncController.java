package com.management.controller.sync;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.management.common.GameRecordComsumer;
import com.management.common.Result;
import com.management.common.util.MD5Util;
import com.management.model.DeviceInfo;
import com.management.service.common.GameRunRecordRequest;
import com.management.service.common.MessageHelperService;
import com.management.service.sync.SyncDataService;

/**
 * 
 * <b>Descriptions:</b>
 * <p>
 * </p>
 * 
 * @author sawyer
 * @createDate 2016年8月6日
 * @file DataSyncController.java
 * @package com.management.controller.api
 * @project management
 * @version 1.0
 *
 */
@Controller
public class DataSyncController {

	private static Logger logger = Logger.getLogger(DataSyncController.class);
	@Autowired
	private MessageHelperService messageHelperService;
	
	
	@Autowired
	private SyncDataService syncDataService;

	/**
	 * 
	 * <b>游戏运行记录时间同步接口:</b>
	 * 
	 * @param data
	 *            数据格式:</br>
	 *            <p>
	 * @author sawyer
	 * @return
	 * @createDate 2016年8月6日
	 *
	 */
	@RequestMapping("/sync/game_run_record")
	public @ResponseBody Result syncGameRunRecord(@RequestParam("data") String data, @RequestParam("token") String token)
	{
		
		if (!MD5Util.createToken().equals(token)) {
			Result.failed(messageHelperService.getTokenInvalid());
		}
		try {
			GameRunRecordRequest gameRunRecordRequest = JSONObject.parseObject(data, GameRunRecordRequest.class);
			GameRecordComsumer.gameRecordQueue.offer(gameRunRecordRequest);
		}
		catch (Exception e) {
			logger.error("syncGameRunRecord", e);
			return Result.failed(messageHelperService.getGameRecordDataFormatError());
		}
		return Result.success();
	}
	
	/**
	 * 获取或有游戏
	 * @param token
	 * @return
	 * @date 2016年8月9日
	 */
	@RequestMapping("/sync/get_games")
	public @ResponseBody Result getGames(@RequestParam("token") String token)
	{

		if (!MD5Util.createToken().equals(token)) {
			Result.failed(messageHelperService.getTokenInvalid());
		}
		return Result.success().addObject(syncDataService.getAllGames());
	}
	
	
	/**
	 * 绑定场地绑定设备
	 * @param token
	 * @param user
	 * @param siteDive
	 * @return
	 * @date 2016年8月9日
	 */
	@RequestMapping("/sync/active_account")
	public @ResponseBody Result activeAccount(@RequestParam("token") String token,@RequestParam("data") String data)
	{

		if (!MD5Util.createToken().equals(token)) {
			Result.failed(messageHelperService.getTokenInvalid());
		}
		try {
			DeviceInfo device = JSONObject.parseObject(data, DeviceInfo.class);
			return syncDataService.activaAccount(device);
		} catch (Exception e) {
			logger.error("data format error", e);
		}
		return Result.failed("data format error");
	}
	
	
}
