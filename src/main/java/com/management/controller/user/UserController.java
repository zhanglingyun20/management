package com.management.controller.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.management.common.Page;
import com.management.common.Result;
import com.management.model.Site;
import com.management.model.Users;
import com.management.service.user.UserService;

/**
 * 
 * @author <a href="mailto:sawyer@wolaidai.com">sawyer</a>
 * @date 2016年8月4日
 */
@Controller
@RequestMapping(value="/user")
public class UserController {

	private static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/index")
	public String index()
	{    
		return "user/user_index";
	}
	
	@RequestMapping(value = "/list")
	public @ResponseBody Page<Users> list(@ModelAttribute Page<Users> page,
			Users users)
	{    
		return userService.findUsersByAccountAndUsername(page, users);
	}
	
	
	@RequestMapping(value = "/add")
	public @ResponseBody Result add(Users user,Site site)
	{
		try {
			return userService.addUser(user, site);
		} catch (Exception e) 
		{
			logger.error("add user error{}", e);
		}
		return Result.failed("保存失败");
	}
	
	@RequestMapping(value = "/user/edit/{id}")
	public String edit(@PathVariable("id")Integer id,Model model)
	{
		Users user = userService.selectById(id);
		model.addAttribute("user", user);
		return "user/edit";
	}
	
	@RequestMapping(value = "/user/dialog/{id}")
	public @ResponseBody Users dialog(@PathVariable("id")Integer id,Model model)
	{
		return userService.selectById(id);
	}
}
