package com.management.service.operate;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.management.common.Page;
import com.management.common.Result;
import com.management.common.util.DateUtils;
import com.management.mapper.BillMapper;
import com.management.mapper.DeviceGameMapper;
import com.management.mapper.DeviceMapper;
import com.management.mapper.GameMapper;
import com.management.model.Bill;
import com.management.model.Device;
import com.management.model.DeviceGame;
import com.management.model.Game;
import com.management.model.vo.BillVO;
import com.management.model.vo.DeviceGameVO;

/**
 * 
 * @author sawyer
 * @date 2016年9月5日
 */
@Service
public class DeviceGameService {

	private static Logger logger = Logger.getLogger(DeviceGameService.class);
	
	@Autowired
	private DeviceGameMapper deviceGameMapper;
	
	@Autowired
	private GameMapper gameMapper;
	
	@Autowired
	private DeviceMapper deviceMapper;
	
	
	@Autowired
	private BillMapper billMapper;
	
	
	public Page<DeviceGameVO> findDeviceGameList(Page<DeviceGameVO> page,DeviceGameVO deviceGameVO)
	{
		List<DeviceGameVO> vos = deviceGameMapper.findDeviceGameList(page, deviceGameVO);
		return page.bulid(vos);
	}
	
	public Page<BillVO> findBillList(Page<BillVO> page,BillVO billVO)
	{
		if (StringUtils.isNotBlank(billVO.getStartTime())) {
			billVO.setStartTime(billVO.getStartTime()+" 00:00:00");
		}
		if (StringUtils.isNotBlank(billVO.getEndTime())) {
			billVO.setEndTime(billVO.getEndTime()+" 23:59:59");
		}
		List<BillVO> vos = billMapper.findBillList(page, billVO);
		return page.bulid(vos);
	}
	
	
	public Page<BillVO> findCompareBillList(Page<BillVO> page,BillVO billVO)
	{
		if (StringUtils.isNotBlank(billVO.getStartTime())) {
			billVO.setStartTime(billVO.getStartTime()+" 00:00:00");
		}
		if (StringUtils.isNotBlank(billVO.getEndTime())) {
			billVO.setEndTime(billVO.getEndTime()+" 23:59:59");
		}
		List<BillVO> billList = billMapper.findBillList(page, billVO);
		if (billList!=null&&!billList.isEmpty()) {
			//设置场地
			for (BillVO model : billList) {
				
			}
		}
		return page.bulid(billList);
	}
	
	
	public void createDeviceGame(String deviceCode,String gameCode){
		if (StringUtils.isEmpty(gameCode)||StringUtils.isEmpty(deviceCode)) {
			logger.warn("createDeviceGame deviceCode ={}"+deviceCode+",gameCode={}"+gameCode);
			return ;
		}
		//TODO 此处需要优化，每次上传数据查询数据库性能有问题。从缓存中校验是否已经关联
		if (deviceGameMapper.findNormalDeviceGameByDeviceCodeAndGameCode(deviceCode, gameCode)==null) {
			DeviceGame deviceGame = new DeviceGame();
			deviceGame.setCreateTime(new Date());
			deviceGame.setDeviceCode(deviceCode);
			deviceGame.setGameCode(gameCode);
			deviceGame.setState(DeviceGame.State.NORMAL.getValue());
			Game game = gameMapper.selectByGameCode(gameCode);
			if (game!=null) {
				deviceGame.setGameId(game.getId());
			}else
			{
				deviceGame.setGameId(0);
				logger.warn("createDeviceGame selectByGameCode game not found gameCode={}"+gameCode);
			}
			Device device = deviceMapper.selectByDeviceCode(deviceCode);
			if (device!=null) {
				deviceGame.setDeviceId(device.getId());
			}else
			{
				deviceGame.setDeviceId(0);
				logger.warn("createDeviceGame selectByGameCode device not found deviceCode={}"+deviceCode);
			}
			deviceGameMapper.insertDeviceGame(deviceGame);
		}
	}
	
	
	public Result creatBill(Bill bill,String date,String amount){
		if (StringUtils.isEmpty(date)) {
			return Result.failed("日期格式错误");
		}
		Date billDate = null;
		try {
			billDate = DateUtils.parseDate(date, "yyy-MM-dd HH:mm:ss");
		} catch (ParseException e) {
			logger.error("creatBill date format error...{}" , e);
			return Result.failed("日期格式错误");
		}
		if (StringUtils.isEmpty(amount)||!isNumeric(amount)) {
			return Result.failed("金额格式错误");
		}
		
		if(StringUtils.isNoneEmpty(bill.getRemark())&&bill.getRemark().length()>500){
			return Result.failed("备注不能超过500个字符");
		}
		bill.setAmount(Double.valueOf(amount));
		bill.setCreateTime(new Date());
		bill.setBillDate(billDate);
		try {
			billMapper.insertBill(bill);
		} catch (Exception e) {
			logger.error("insertBill error" , e);
			return Result.failed("服务异常");
		}
		return Result.success();
	}
	
	
    public  boolean isNumeric(String amount)
    {
          Pattern pattern = Pattern.compile("([1-9]+[0-9]*|0)(\\.[\\d]+)?");
          Matcher isNum = pattern.matcher(amount);
          return isNum.matches();
    }
}
