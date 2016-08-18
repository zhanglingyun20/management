package com.management.service.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageHelperService {

	@Value("${user.already.exist}")
	private String userAlreadyExist;

	@Value("${user.account.cannot.null}")
	private String userAccountCannotNull;

	@Value("${system.db.error}")
	private String systemDbError;

	@Value("${token.invalid}")
	private String tokenInvalid;

	@Value("${unknown.account}")
	private String unknownAccount;

	@Value("${game.record.data.format.error}")
	private String gameRecordDataFormatError;
	
	@Value("${user.password.error}")
	private String userPasswordError;
	
	public String getUserAlreadyExist()
	{
		return userAlreadyExist;
	}

	public String getUserAccountCannotNull()
	{
		return userAccountCannotNull;
	}

	public String getSystemDbError()
	{
		return systemDbError;
	}

	public String getTokenInvalid()
	{
		return tokenInvalid;
	}

	public String getUnknownAccount()
	{
		return unknownAccount;
	}

	public String getGameRecordDataFormatError()
	{
		return gameRecordDataFormatError;
	}

	public String getUserPasswordError()
	{
		return userPasswordError;
	}
	
	

}
