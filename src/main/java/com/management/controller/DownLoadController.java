package com.management.controller;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

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
public class DownLoadController {

	@Autowired
	private ExcelService excelService;

	@RequestMapping("/download")
	public void download(
			@RequestParam(value = "download_type", defaultValue = "", required = false) String download_type,
			HttpServletResponse response) {

		response.setCharacterEncoding("utf-8");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;fileName=" + download_type+String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls");
		HSSFWorkbook workBook = excelService.reportExcel(download_type);
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
