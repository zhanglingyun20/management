package com.management.service;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.management.JUnit4ClassRunner;

@Transactional
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-context.xml" })
public class BaseServiceTest {

	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void testMyDao()
	{
		try {
			// testDao.doSomething();
		}
		catch (Exception e) {
			fail("Test failed!");
		}
	}

	@Ignore
	public void testOtherSpringObject()
	{
		fail("Not yet implemented");
	}
}