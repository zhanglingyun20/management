package com.management.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author sawyer
 * @date 2016年8月13日
 */
@Controller()
@RequestMapping(value="/main")
public class MainController {

	
	@RequestMapping(value = "/code")
	public String home(HttpSession session)
	{
		return  "main/code";
	}
	
	@RequestMapping(value = "/main-menu")
	public String mainMenu(HttpSession session)
	{
		return  "main/main-menu";
	}
	
	@RequestMapping(value = "/second-menu")
	public String secondMenu(HttpSession session)
	{
		return  "main/second-menu";
	}
	
	@RequestMapping(value = "/dyna-menu")
	public String dynaMenu(HttpSession session)
	{
		return  "main/dyna-menu";
	}
}
