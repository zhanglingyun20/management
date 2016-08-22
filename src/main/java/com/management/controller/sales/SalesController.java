package com.management.controller.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.management.common.Page;
import com.management.model.vo.DeviceDetailsSalesVO;
import com.management.model.vo.DeviceSalesVO;
import com.management.service.report.ReportService;

/**
 * 
 * @author <a href="mailto:sawyer@wolaidai.com">sawyer</a>
 * @date 2016年8月9日
 */
@Controller
@RequestMapping(value="/sales")
public class SalesController
{

	@Autowired
	private ReportService reportService;
	
	@RequestMapping(value = "/device_sales")
	public String device()
	{    
		return "sales/device_sales";
	}
	
	@RequestMapping(value = "/device_sales_list")
	public @ResponseBody Page<DeviceSalesVO> deviceList (Page<DeviceSalesVO> page,DeviceSalesVO record)
	{
		return reportService.findDeviceSales(page, record);
	}
	
	
	@RequestMapping(value = "/device_sales_details")
	public String deviceSalesDetails(String deviceCode,Model model)
	{   
		model.addAttribute("deviceCode", deviceCode);
		return "sales/device_sales_details";
	}
	
	
	@RequestMapping(value = "/device_sales_details_list")
	public @ResponseBody Page<DeviceDetailsSalesVO> deviceSalesDetails (Page<DeviceDetailsSalesVO> page,DeviceDetailsSalesVO record)
	{
		return reportService.findDeviceSalesDetails(page, record);
	}
}
