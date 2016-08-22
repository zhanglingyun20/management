package com.management.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.management.common.Result;
import com.management.common.SpringContextUtil;
import com.management.mapper.UsersMapper;

public class Users {
	/**
     * 
     */
	private Integer id;

	/**
	 * 账号
	 */
	private String account;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 用户状态
	 */
	private String state;

	/**
	 * 创建时间
	 */
	private Date createTime;

	private String userType;

	public enum State {
		NORMAL("normal", "正常"), FORBIDDEN("forbidden", "禁用"), INACTIVE(
				"inactive", "未激活");
		private final String text;
		private final String value;

		State(String value, String text) {
			this.value = value;
			this.text = text;
		}

		public String getValue() {
			return value;
		}

		public String getText() {
			return text;
		}

	}

	public enum Type {
		SYSTEM("system"), DEVICE("site"), SHAREHOLDERS("shareholders");
		private final String value;

		Type(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Users() {
		super();
	}

	public Users(Integer id, String account, String username, String password,
			String state, Date createTime, String userType) {
		super();
		this.id = id;
		this.account = account;
		this.username = username;
		this.password = password;
		this.state = state;
		this.createTime = createTime;
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", account=" + account + ", username="
				+ username + ", password=" + password + ", state=" + state
				+ ", createTime=" + createTime + ", userType=" + userType + "]";
	}


	public static Boolean validataUser(Users user){
		if (user==null||StringUtils.isEmpty(user.getAccount())
				||StringUtils.isEmpty(user.getPassword())
				||StringUtils.isEmpty(user.getUsername())
				||StringUtils.isEmpty(user.getUserType())) {
			Result.failed("");
			return false;
		}
		return getUserTypes().contains(user.getUserType());
	}
	
	
	public static List<String> getUserTypes(){
		List<String> typeList = new ArrayList<String>();
		Type types[] = Users.Type.values();
		for (Type item : types) {
			typeList.add(item.getValue());
		}
		return typeList;
	}
	public static void main(String[] args) {
		System.out.println(getUserTypes().contains("system"));
	}
}