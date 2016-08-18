package com.management.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-mvc.xml", "classpath:spring-context.xml" })
public class CreateProductControllerTest {
	@Resource(name = "/createProduct.htm")
	// CreateProductController createProductController;
	private MockHttpServletRequest request;
	private MockHttpServletResponse response;

	@Before
	public void before()
	{
		request = new MockHttpServletRequest();
		response = new MockHttpServletResponse();
		request.setCharacterEncoding("UTF-8");
	}

	@Test
	public void testToSearchPage()
	{
		// request.setRequestURI("createProduct.htm");
		// request.setMethod(HttpMethod.POST.name());
		ModelAndView mv = null;
		try {
			// mv = createProductController.toSearchPage(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("testToSearchPage failed!");
		}

		assertNotNull(mv);
		assertEquals(response.getStatus(), 200);
	}
}