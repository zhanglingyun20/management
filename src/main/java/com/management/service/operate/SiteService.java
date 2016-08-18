package com.management.service.operate;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.common.Page;
import com.management.mapper.GamePriceMapper;
import com.management.mapper.SiteMapper;
import com.management.model.GamePrice;
import com.management.model.vo.SiteVO;

/**
 * 
 * @author sawyer
 * @date 2016年8月14日
 */
@Service
public class SiteService {

	private static Logger logger = Logger.getLogger(SiteService.class);
	
	@Autowired
	private SiteMapper siteMapper;
	
	
	@Autowired
	private GamePriceMapper gamePriceMapper;
	
	public Page<SiteVO> findSites(Page<SiteVO> page,SiteVO siteVO)
	{
		List<SiteVO> vos = siteMapper.findSites(page, siteVO);
		if (vos!=null&&!vos.isEmpty()) 
		{
			for (SiteVO model : vos) {
				int fixPriceGameCount = 0;
				if (StringUtils.isNotBlank(model.getAccount())) {
					fixPriceGameCount = gamePriceMapper.findFixGameCount(model.getAccount(), GamePrice.Use.YES.getValue());
				}
				model.setFixPriceGameCount(fixPriceGameCount);
			}
		}
		return page.bulid(vos);
	}
	
//	public Result boundPrice(Game game){
//		if (game==null||StringUtils.isEmpty(game.getGameProcess())||StringUtils.isEmpty(game.getGameName())) {
//			logger.info("资料不完整");
//			return Result.failed("资料不完整");
//		}
//		if (gameMapper.selectByGameProcess(game.getGameProcess())!=null) {
//			return Result.failed("该游戏进程已存在");
//		}
//		game.setGameCode(DataUtils.generateGameCode());
//		game.setCreateTime(new Date());
//		game.setState(Game.State.ACTIVE.getValue());
//		gameMapper.insertSelective(game);
//		return Result.success();
//	}
}
