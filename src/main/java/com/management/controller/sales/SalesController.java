package com.management.controller.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.management.common.Page;
import com.management.model.vo.DeviceVO;
import com.management.model.vo.GameVO;
import com.management.model.vo.SiteVO;
import com.management.service.report.ReportService;
import com.management.service.sales.SaleService;

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
	
	@Autowired
	private SaleService saleService;
	
	@RequestMapping(value = "/device_sales")
	public String device()
	{    
		return "sales/device_sales";
	}
	
	@RequestMapping(value = "/device_sales_list")
	public @ResponseBody Page<DeviceVO> deviceList (Page<DeviceVO> page,DeviceVO record)
	{
		return saleService.getDeviceSales(page, record);
	}
	
	
	@RequestMapping(value = "/device_sales_details")
	public String deviceSalesDetails(String deviceCode,Model model)
	{   
		model.addAttribute("deviceCode", deviceCode);
		return "sales/device_sales_details";
	}
	
	
	@RequestMapping(value = "/device_sales_details_list")
	public @ResponseBody Page<GameVO> deviceSalesDetails (Page<GameVO> page,GameVO record)
	{
		return saleService.getDeviceGamesSales(page, record);
	}
	
	
	@RequestMapping(value = "/site_sales")
	public String siteSales()
	{    
		return "sales/site_sales";
	}
	
	@RequestMapping(value = "/site_sales_list")
	public @ResponseBody Page<SiteVO> siteSalesList (Page<SiteVO> page,SiteVO record)
	{
		return saleService.getBySiteByAccountAndSiteName(page, record);
	}
}
