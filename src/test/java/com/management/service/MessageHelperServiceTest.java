package com.management.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.management.service.common.MessageHelperService;

public class MessageHelperServiceTest extends BaseServiceTest {

	@Autowired
	private MessageHelperService messageHelperService;

	@Test
	public void testGetMessageProp()
	{
		String result = messageHelperService.getUserAlreadyExist();
		assertEquals("用户已存在", result);
		String result2 = messageHelperService.getUserPasswordError();
		System.out.println(result2);
	}
}
