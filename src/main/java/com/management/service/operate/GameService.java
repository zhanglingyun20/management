package com.management.service.operate;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.common.Page;
import com.management.common.Result;
import com.management.common.util.DataUtils;
import com.management.mapper.GameMapper;
import com.management.model.Game;

@Service
public class GameService {

	private static Logger logger = Logger.getLogger(GameService.class);
	
	@Autowired
	private GameMapper gameMapper;
	
	public Page<Game> findGames(Page<Game> page,Game game){
		return page.bulid(gameMapper.findGames(page, game));
	}
	
	public Result addGame(Game game){
		if (Objects.isNull(game.getId())) {
			if (game==null||StringUtils.isEmpty(game.getGameProcess())||StringUtils.isEmpty(game.getGameName())) {
				logger.info("资料不完整");
				return Result.failed("资料不完整");
			}
			if (gameMapper.selectByGameProcess(game.getGameProcess())!=null) {
				return Result.failed("该游戏进程已存在");
			}
			game.setGameCode(DataUtils.generateGameCode());
			game.setCreateTime(new Date());
			game.setState(Game.State.ACTIVE.getValue());
			gameMapper.insertSelective(game);
		}else{
			return updateGame(game);
		}
		return Result.success();
	}
	
	public Result updateGame(Game game) {
		if (game == null || StringUtils.isEmpty(game.getGameProcess())
				|| StringUtils.isEmpty(game.getGameName())) {
			logger.info("资料不完整");
			return Result.failed("资料不完整");
		}
		Game oldGame = gameMapper.selectByPrimaryKey(game.getId());
		if (gameMapper.selectByGameProcess(game.getGameProcess()) != null
				&&!game.getGameProcess().equals(oldGame.getGameProcess())) {
			return Result.failed("该游戏进程已存在");
		}
		gameMapper.updateByPrimaryKeySelective(game);
		return Result.success();
	}
	
	public Result updateGameState(String state,String ids){
		if (!Game.getStates().contains(state)) {
			return Result.failed("传入状态不对");
		}
		if (StringUtils.isEmpty(ids)) {
			return Result.failed("请选择要操作的数据");
		}
		List<String> idList = Arrays.asList(ids.split(","));
		if (idList==null||idList.isEmpty()) {
			return Result.failed("请选择要操作的数据");
		}
		Map<String, Object> parmaMap =new HashMap<String, Object>();
		parmaMap.put("state", state);
		parmaMap.put("ids", idList);
		try {
			gameMapper.updateGameState(parmaMap);
		} catch (Exception e) 
		{
			logger.error("updateGameState error", e);
			return Result.failed("操作失败");
		}
		return Result.success();
	}
}
