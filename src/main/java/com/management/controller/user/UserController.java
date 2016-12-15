package com.management.controller.user;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.management.common.Page;
import com.management.common.Result;
import com.management.model.Site;
import com.management.model.Users;
import com.management.model.vo.UserTypeSelect;
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
	
	@RequestMapping(value = "/change_pwd")
	public @ResponseBody Result changePwd(
			@RequestParam(value="account",defaultValue="",required=false)String account,
			@RequestParam(value="password",defaultValue="",required=false)String password,
			@RequestParam(value="newPwd",defaultValue="",required=false)String newPwd,
			@RequestParam(value="newPwdConfirm",defaultValue="",required=false)String newPwdConfirm)
	{
		
		try {
			return userService.changePwd(account, password, newPwd, newPwdConfirm);
		} catch (Exception e) {
			logger.error("changePwd error{}", e);
		}
		return Result.failed("服务异常");
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable("id")Integer id,Model model)
	{
		Users user = userService.selectById(id);
		model.addAttribute("user", user);
		return "user/edit";
	}
	
	
	
	@RequestMapping(value = "/dialog/{id}")
	public @ResponseBody Users dialog(@PathVariable("id")Integer id)
	{
		return userService.selectById(id);
	}
	
	
	@RequestMapping(value = "/opt/{state}")
	public @ResponseBody Result forbidden(@RequestParam(value="ids",defaultValue="",required=false)String ids,
			@PathVariable("state")String state)
	{
		return userService.updateUserStateByIds(state, ids);
	}
	
	
	@RequestMapping(value = "/getUserType")
	public @ResponseBody List<UserTypeSelect> shareholders(String userType)
	{    
		return userService.getUserByUserType(userType);
	}
}
