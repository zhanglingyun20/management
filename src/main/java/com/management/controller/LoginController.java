package com.management.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.management.common.Result;
import com.management.model.Users;
import com.management.service.user.UserService;

/**
 * 
 * <b>Descriptions:</b>
 * <p>
 * </p>
 * 
 * @author sawyer
 * @createDate 2016年8月6日
 * @file SignController.java
 * @package com.management.controller
 * @project management
 * @version 1.0
 *
 */
@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/")
	public String index(HttpSession session)
	{
		if (session.getAttribute("user")!=null) {
			return "index";
		}
		return  "login";
	}
	
	@RequestMapping(value = "/check_sign")
	public @ResponseBody Result checkSign(Users user, HttpSession session)
	{
		Result result = null;
		if (user != null) {
			result = userService.sign(user);
			if (Result.Code.SUCCESS.getValue().equals(result.getCode())) {
				return  result;
			}
		}
		return result;
	}

	@RequestMapping(value = "/login")
	public String sign(@ModelAttribute Users user, HttpSession session, Model model)
	{
		if (user != null) {
			Result result = userService.sign(user);
			if (Result.Code.SUCCESS.getValue().equals(result.getCode())) {
				Users users = (Users)result.getContent();
				session.setAttribute("user", users);
				session.setAttribute("userType", users.getUserType());
				return "redirect:/";
			}
			model.addAttribute("result", result);
		}
		return "login";
	}

	@RequestMapping(value = "/sign_out")
	public String signOut(Users user, HttpSession session)
	{
		if (user != null) {
			session.removeAttribute("user");
			session.removeAttribute("userType");
		}
		return "redirect:/";
	}

}
