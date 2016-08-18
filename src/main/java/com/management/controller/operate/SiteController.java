package com.management.controller.operate;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.management.common.Page;
import com.management.model.vo.SiteVO;
import com.management.service.operate.SiteService;


@Controller
@RequestMapping(value="/site")
public class SiteController {

	private static Logger logger = Logger.getLogger(SiteController.class);
	
	@Autowired
	private SiteService siteService;
	
	@RequestMapping(value = "/index")
	public String index()
	{    
		return "operate/site_index";
	}
	
	@RequestMapping(value = "/site_list")
	public @ResponseBody Page<SiteVO> list(@ModelAttribute Page<SiteVO> page,
			SiteVO siteVO)
	{    
		return siteService.findSites(page, siteVO);
	}
	
}
