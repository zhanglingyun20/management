package com.management.controller.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.management.common.Page;
import com.management.model.vo.DeviceReportVO;
import com.management.service.report.ReportService;

/**
 * 
 * @author <a href="mailto:sawyer@wolaidai.com">sawyer</a>
 * @date 2016年8月9日
 */
@Controller
@RequestMapping(value="/report")
public class ReportController
{

	@Autowired
	private ReportService reportService;
	
	@RequestMapping(value = "/site")
	public String site()
	{    
		return "report/site";
	}
	
	@RequestMapping(value = "/device")
	public String device()
	{    
		return "report/report_device";
	}

	@RequestMapping(value = "/store")
	public String store()
	{
		return "report/report_store";
	}

	@RequestMapping(value = "/city")
	public String city()
	{
		return "report/report_city";
	}

	@RequestMapping(value = "/province")
	public String province()
	{
		return "report/report_province";
	}

	@RequestMapping(value = "/all")
	public String all()
	{
		return "report/report_all";
	}
	/**
	 * MiHalo
	 * 查询游戏运行的次数，可以查询多少分钟以上的有运行多少次
	 * */
	@RequestMapping(value = "/game")
	public String game()
	{
		return "report/report_game";
	}
	
	
	@RequestMapping(value = "/device_list")
	public @ResponseBody Page<DeviceReportVO> deviceList (Page<DeviceReportVO> page,DeviceReportVO record)
	{
		return reportService.findDeviceReports(page, record);
	}
	
	
	@RequestMapping(value = "/device_details")
	public String deviceDetails(DeviceReportVO record,Model model)
	{   
		model.addAttribute("record", record);
		return "report/device_details";
	}
	
	@RequestMapping(value = "/details_list")
	public @ResponseBody Page<DeviceReportVO> detailsList (Page<DeviceReportVO> page,DeviceReportVO record)
	{
		return reportService.findDeviceReportsDetailsByDateAndUniqueGame(page, record);
	}
}
