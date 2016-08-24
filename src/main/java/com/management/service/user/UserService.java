package com.management.service.user;

import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.management.common.Page;
import com.management.common.Result;
import com.management.common.util.DataUtils;
import com.management.common.util.MD5Util;
import com.management.mapper.SiteMapper;
import com.management.mapper.UsersMapper;
import com.management.model.Site;
import com.management.model.Users;
import com.management.service.common.MessageHelperService;

/**
 * 
 * <b>Descriptions:</b>
 * <p>
 * </p>
 * 
 * @author sawyer
 * @createDate 2016年8月5日
 * @file UserServices.java
 * @package com.management.service.user
 * @project management
 * @version 1.0
 *
 */
@Service
public class UserService {

	@Autowired
	private UsersMapper usersMapper;
	
	@Autowired
	private SiteMapper siteMapper;

	@Autowired
	private MessageHelperService messageHelperService;

	@Transactional
	public Result addUser(Users user,Site site)
	{
		if (Users.validataUser(user)) {
			Users record = selectByAccount(user.getAccount());
			if (record!=null) 
			{
				return Result.failed("账号已存在");
			}
			user.setState(Users.State.NORMAL.getValue());
			user.setCreateTime(new Date());
			user.setPassword(MD5Util.MD5(user.getPassword()));
			usersMapper.insert(user);
			//场地
			if (Users.Type.SITE.getValue().equals(user.getUserType())) {
				if (Objects.isNull(site)) {
					throw new IllegalArgumentException("参数有误");
				}
				site.setSiteCode(DataUtils.generateSiteCode());
				site.setCreateTime(new Date());
				site.setParentId(0);
				site.setUserId(user.getId());
				siteMapper.insertSelective(site);
			}
			return Result.success();
		}
		return Result.failed("参数有误");
	}
	
	@Transactional
	public Result registerUser(Users user)
	{
		if (user == null || StringUtils.isEmpty(user.getAccount())) { return Result.failed(messageHelperService.getUserAccountCannotNull()); }
		user.setCreateTime(new Date());
		user.setState(Users.State.NORMAL.getValue());
		usersMapper.insert(user);
		return Result.success();
	}

	public Page<Users> findUsersByAccountAndUsername(Page<Users> page,Users users){
		return page.bulid(usersMapper.findUsersByAccountAndUsername(page, users));
	}
	public Users selectByAccount(String account)
	{
		if (StringUtils.isEmpty(account)) { return null; }
		return usersMapper.selectByAccount(account);
	}
	
	public Users selectById(Integer id)
	{
		return usersMapper.selectByPrimaryKey(id);
	}
	

	public Result updateByAccountSkipNull(Users record)
	{
		int count = usersMapper.updateByAccountSelective(record);
		if (count >= 0) { return Result.success(); }
		return Result.failed(messageHelperService.getSystemDbError());
	}
	
	/**
	 * 
	 * <b>用户登录:</b>
	 * <p></p>
	 * @author sawyer
	 * @param user
	 * @return
	 * @createDate 2016年8月7日
	 *
	 */
	public Result sign(Users user)
	{
		if (user==null||StringUtils.isEmpty(user.getAccount())||StringUtils.isEmpty(user.getPassword())) {
			return Result.failed(messageHelperService.getUserAlreadyExist());
		}
		user.setPassword(MD5Util.MD5(user.getPassword()));
		Users record = usersMapper.selectByAccountAndPwd(user.getAccount(), user.getPassword());
		if (record==null) {
			return Result.failed(messageHelperService.getUserPasswordError());
		}
		if (!Users.State.NORMAL.getValue().equals(record.getState())) {
			String reason = "please try again later";
			if (Users.State.FORBIDDEN.getValue().equals(record.getState())) {
				reason = Users.State.FORBIDDEN.getText();
			}else if(Users.State.INACTIVE.getValue().equals(record.getState())){
				reason = Users.State.INACTIVE.getText();
			}
			return Result.failed(reason);
		}
		return Result.success().addObject(record);
	}
	
	
	public Result getUserByUserType(String userType){
		
		return Result.success().addObject(usersMapper.getUserByUserType(userType));
	}
}
