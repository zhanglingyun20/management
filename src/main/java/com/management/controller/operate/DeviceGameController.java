package com.management.controller.operate;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.management.common.Page;
import com.management.common.Result;
import com.management.model.Bill;
import com.management.model.Users;
import com.management.model.vo.BillVO;
import com.management.model.vo.DeviceGameVO;
import com.management.service.operate.DeviceGameService;


@Controller
@RequestMapping(value="/device_game")
public class DeviceGameController {

	private static Logger logger = Logger.getLogger(DeviceGameController.class);
	
	@Autowired
	private DeviceGameService deviceGameService;
	
	@RequestMapping(value = "/index")
	public String index()
	{    
		return "operate/device_game";
	}
	
	@RequestMapping(value = "/device_game_list")
	public @ResponseBody Page<DeviceGameVO> list(@ModelAttribute Page<DeviceGameVO> page,
			DeviceGameVO deviceGameVO,HttpSession session)
	{    
		Users user = (Users) session.getAttribute("user");
		if (user!=null&&Users.Type.SITE.getValue().equals(user.getUserType())) {
			deviceGameVO.setUserId(user.getId());
		}
		return deviceGameService.findDeviceGameList(page, deviceGameVO);
	}
	
	
	
	@RequestMapping(value = "/add_bill")
	public @ResponseBody Result addBill(Bill bill,
			@RequestParam(value="amountStr",defaultValue="0.0",required=false)String amountStr,
			@RequestParam(value="date",defaultValue="",required=false)String date,HttpSession session) {
		
		Users user = (Users) session.getAttribute("user");
		if (user!=null) {
			bill.setUserId(user.getId());
			bill.setUserName(user.getUsername());
		}
		return deviceGameService.creatBill(bill, date, amountStr);
	}
	
	@RequestMapping(value = "/bill_history")
	public String billHistory(
			@RequestParam(value="deviceGameId",defaultValue="0",required=false)Integer deviceGameId,
			@RequestParam(value="deviceName",defaultValue="0",required=false)String deviceName,
			@RequestParam(value="siteName",defaultValue="0",required=false)String siteName,
			 Model model)
	{    
		model.addAttribute("deviceGameId", deviceGameId);
		model.addAttribute("deviceName", deviceName);
		model.addAttribute("siteName", siteName);
		return "operate/bill_history";
	}
	
	
	@RequestMapping(value = "/bill_history_list")
	public @ResponseBody Page<BillVO> billHistoryList(@ModelAttribute Page<BillVO> page,
			BillVO billVO,HttpSession session)
	{    
		return deviceGameService.findBillList(page, billVO);
	}
	
}
