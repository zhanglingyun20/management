package com.management.controller;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import com.management.model.vo.DeviceVO;
import com.management.model.vo.GameVO;
import com.management.model.vo.SiteVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.management.service.common.ExcelService;

/**
 * 
 * @author sawyer
 *
 */
@Controller
@RequestMapping("download")
public class DownLoadController {

	@Autowired
	private ExcelService excelService;

	@RequestMapping("/finance")
	public void downloadFinance(
			@RequestParam(value = "download_type", defaultValue = "", required = false) String download_type,
			HttpServletResponse response) {
		HSSFWorkbook workBook = excelService.reportExcel(download_type,null);
		writeStream(response,workBook,download_type);

	}

	@RequestMapping("/siteSales")
	public void downloadSiteSales(
			@RequestParam(value = "download_type", defaultValue = "", required = false) String download_type,
			HttpServletResponse response,SiteVO record) {
		HSSFWorkbook workBook = excelService.reportExcel(download_type,record);
		writeStream(response,workBook,download_type);

	}

	@RequestMapping("/deviceSales")
	public void downloadDeviceSales(
			@RequestParam(value = "download_type", defaultValue = "", required = false) String download_type,
			HttpServletResponse response, DeviceVO record) {
		HSSFWorkbook workBook = excelService.reportExcel(download_type,record);
		writeStream(response,workBook,download_type);

	}

	@RequestMapping("/deviceSalesDetail")
	public void downloadDeviceSalesDetail(
			@RequestParam(value = "download_type", defaultValue = "", required = false) String download_type,
			HttpServletResponse response,GameVO record) {
		HSSFWorkbook workBook = excelService.reportExcel(download_type,record);
		writeStream(response,workBook,download_type);
	}


	private void writeStream(HttpServletResponse response,HSSFWorkbook workBook,String download_type){
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;fileName=" + download_type+String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls");
		try {
			OutputStream os = response.getOutputStream();
			workBook.write(os);
			// 这里主要关闭。
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
