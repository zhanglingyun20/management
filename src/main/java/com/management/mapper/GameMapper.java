package com.management.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.management.common.Page;
import com.management.model.Game;

public interface GameMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Game record);

	int insertSelective(Game record);

	Game selectByPrimaryKey(Integer id);
	
	Game selectByGameCode(@Param("gameCode")String gameCode);
	
	Game selectByGameProcess(@Param("gameProcess")String gameProcess);
	
	int updateByPrimaryKeySelective(Game record);

	int updateByPrimaryKey(Game record);
	
	List<Game> selectAllGames(@Param("state")String state);
	
	List<Game> findGames(Page<Game> page,@Param("game")Game game);
	
	int updateGameState(Map<String, Object> parmaMap);
}