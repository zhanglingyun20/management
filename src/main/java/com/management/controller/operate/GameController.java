package com.management.controller.operate;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.management.common.Page;
import com.management.common.Result;
import com.management.model.Game;
import com.management.service.operate.GameService;


@Controller
@RequestMapping(value="/game")
public class GameController {

	private static Logger logger = Logger.getLogger(GameController.class);
	
	@Autowired
	private GameService gameService;
	
	@RequestMapping(value = "/index")
	public String index()
	{    
		return "operate/game_index";
	}
	
	@RequestMapping(value = "/game_list")
	public @ResponseBody Page<Game> list(@ModelAttribute Page<Game> page,
			Game game)
	{    
		return gameService.findGames(page, game);
	}
	
	
	@RequestMapping(value = "/add")
	public @ResponseBody Result add(Game game) {
		try {
			return gameService.addGame(game);
		} catch (Exception e) {
			logger.error("add game error{}", e);
		}
		return Result.failed("保存失败");
	}
	
	
	@RequestMapping(value = "/update_state")
	public @ResponseBody Result updateState(String ids,String state) {
		try {
			gameService.updateGameState(state, ids);
			return Result.success();
		} catch (Exception e) {
			logger.error("add game error{}", e);
		}
		return Result.failed("操作失败");
	}
	
}
