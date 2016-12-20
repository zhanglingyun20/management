package com.management.controller.operate;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.management.common.Page;
import com.management.model.Users;
import com.management.model.vo.FinanceStatisticalVO;
import com.management.service.operate.FinanceStatisticalService;


@Controller
@RequestMapping(value="/finance")
public class FinanceController {

	private static Logger logger = Logger.getLogger(FinanceController.class);
	
	@Autowired
	private FinanceStatisticalService financeStatisticalService;
	
	@RequestMapping(value = "/index")
	public String index()
	{    
		return "operate/finance_index";
	}
	
	@RequestMapping(value = "/finance_list")
	public @ResponseBody Page<FinanceStatisticalVO> list(@ModelAttribute Page<FinanceStatisticalVO> page,
			FinanceStatisticalVO vo,HttpSession session)
	{    
		Users user = (Users) session.getAttribute("user");
		if (user!=null&&Users.Type.SITE.getValue().equals(user.getUserType())) {
			vo.setUserId(user.getId());
		}
		return financeStatisticalService.findFinanceStatisticalList(page, vo);
	}
}
