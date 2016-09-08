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
import com.management.model.SiteBill;
import com.management.model.Users;
import com.management.model.vo.BillVO;
import com.management.model.vo.SiteBillVO;
import com.management.service.operate.SiteBillService;


@Controller
@RequestMapping(value="/bill")
public class SiteBillController {

	private static Logger logger = Logger.getLogger(SiteBillController.class);
	
	@Autowired
	private SiteBillService siteBillService;
	
	@RequestMapping(value = "/index")
	public String index()
	{    
		return "operate/site_bill";
	}
	
	@RequestMapping(value = "/site_bill_list")
	public @ResponseBody Page<SiteBillVO> list(@ModelAttribute Page<SiteBillVO> page,
			SiteBillVO siteBillVO,HttpSession session)
	{    
		Users user = (Users) session.getAttribute("user");
		if (user!=null&&Users.Type.SITE.getValue().equals(user.getUserType())) {
			siteBillVO.setUserId(user.getId());
		}
		return siteBillService.findSiteBillList(page, siteBillVO);
	}
	
	
	
	@RequestMapping(value = "/add_site_bill")
	public @ResponseBody Result addSiteBill(SiteBill siteBill,
			@RequestParam(value="amountStr",defaultValue="0.0",required=false)String amountStr,
			@RequestParam(value="date",defaultValue="",required=false)String date,HttpSession session) {
		
		Users user = (Users) session.getAttribute("user");
		if (user!=null) {
			siteBill.setUserId(user.getId());
			siteBill.setAccount(user.getAccount());
		}else
		{
			Result.failed("请登录");
		}
		return siteBillService.creatSiteBill(siteBill, date, amountStr);
	}
	
	
	
	@RequestMapping(value = "/update_remark")
	public @ResponseBody Result updateRemark(
			@RequestParam(value="id",defaultValue="0",required=false)String id,
			@RequestParam(value="remark",defaultValue="",required=false)String remark
			)
	{    
		return siteBillService.updateRemark(id, remark);
	}
	
}
