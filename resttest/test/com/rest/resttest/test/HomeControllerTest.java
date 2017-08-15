package com.rest.resttest.test;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.rest.resttest.config.MVCConfiguration;
import com.rest.resttest.controller.HomeController;
import com.rest.resttest.domain.Message;
import com.rest.resttest.service.MessageService;

@RunWith(value = SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { MVCConfiguration.class })
public class HomeControllerTest {

	
	private MockMvc mockMvc;
	/* used with MockMvcBuilders.webAppContextSetup
	 @Autowired
	private WebApplicationContext webApplicationContext;
	*/
	@Mock
	private MessageService messageServiceMock;
	@InjectMocks
	private HomeController controller;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
		// webAppContextSetup will load mvc configuration in test case while standalone will use mocks
		//mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void messageTest() throws Exception {
		Message expected = new Message("vivek", "Hello vivek");
		when(messageServiceMock.getMessage("vivek")).thenReturn(expected);
		mockMvc.perform(get("/hello/vivek.json")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.name", is("vivek"))).andExpect(jsonPath("$.text", is("Hello vivek")));
		verify(messageServiceMock, times(1)).getMessage(Mockito.anyString());
		verifyNoMoreInteractions(messageServiceMock);

	}

	@After
	public void tearDown() {
		Mockito.reset(messageServiceMock);
	}
}
