package com.management.service;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.management.mapper.GameRunRecordMapper;
import com.management.model.GameRunRecord;

public class GameRunRecordServiceTest extends BaseServiceTest {

	@Autowired
	private GameRunRecordMapper gameRunRecordMapper;

	@Test
	public void testAdd()
	{
		GameRunRecord record = new GameRunRecord(null, "account", "deviceMac", "deviceCode", "deviceName", "gameCode", "gameName", "gameProcess", new Date(), 1, new Date(), new Date());
		gameRunRecordMapper.insert(record);
	}
}
