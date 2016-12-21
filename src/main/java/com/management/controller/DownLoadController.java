package com.management.controller;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import com.management.model.vo.DeviceVO;
import com.management.model.vo.GameVO;
import com.management.model.vo.SiteVO;
<<<<<<< HEAD
=======
import com.management.service.sales.SaleService;
>>>>>>> ca61390cf317c22e2bb1deb449a2676ae5832008
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.management.service.common.ExcelService;

/**
 * 
 * @author sawyer
 *
 */
@Controller
<<<<<<< HEAD
@RequestMapping("download")
=======
@RequestMapping("/download")
>>>>>>> ca61390cf317c22e2bb1deb449a2676ae5832008
public class DownLoadController {

	@Autowired
	private ExcelService excelService;
<<<<<<< HEAD

	@RequestMapping("/finance")
	public void downloadFinance(
=======
	/*@RequestMapping("/download")
	public void download(
>>>>>>> ca61390cf317c22e2bb1deb449a2676ae5832008
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
<<<<<<< HEAD

=======
	}*/

	@RequestMapping("/finance")
	public void downloadFinance(HttpServletResponse response){
		download("finance",response,null);
	}
	@RequestMapping("/sitesales")
	public void downloadSitesales(HttpServletResponse response,SiteVO record){
		download("sitesales",response,record);
	}
	@RequestMapping("/devicesales")
	public void downloadDevicesales(HttpServletResponse response,DeviceVO record){
		download("devicesales",response,record);
	}
	@RequestMapping("/salesdetail")
	public void downloadSalesdetail(HttpServletResponse response,GameVO record){
		download("salesdetail",response,record);
	}

	public void download( String download_type, HttpServletResponse response,Object object) {

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;fileName=" + download_type+String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls");
		HSSFWorkbook workBook = excelService.reportExcel(download_type,object);
		try {
			OutputStream os = response.getOutputStream();
			workBook.write(os);
			// 这里主要关闭。
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
>>>>>>> ca61390cf317c22e2bb1deb449a2676ae5832008
	}

}
