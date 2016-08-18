package com.management.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.management.common.Page;
import com.management.mapper.SiteMapper;
import com.management.model.Site;
import com.management.model.Users;
import com.management.service.user.UserService;

public class UserServiceTest extends BaseServiceTest {

	@Autowired
	private UserService userService;
	@Autowired
	private SiteMapper siteMapper;

	@Test
	public void testSelectByAccount()
	{
		Users user = userService.selectByAccount("zly@163.com");
		assertEquals("zly@163.com", user.getAccount());
	}
	
	@Test
	public void testAddUser()
	{
//		for (int i = 4; i < 23; i++) {
//			Users user = new Users(null, "zly"+i+"@163.com", "zly"+i, "123456"+i, Users.State.NORMAL.getValue(), new Date());
//			userService.registerUser(user);
//		}
		Site site = new Site();
		site.setCity("哈哈");
		siteMapper.insertSelective(site);
	}
	@Test
	public void testFindUsersByPage()
	{
//		Page<Users> page = new Page<Users>(1, 10);
//		Users user = new Users();
//		user.setAccount("123456");
//		user.setUsername("");
//		page = userService.findUsersByAccountAndUsername(page, user);
//		System.out.println(page);
	}
}
