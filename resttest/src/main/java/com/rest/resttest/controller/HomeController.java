package com.rest.resttest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.resttest.domain.Message;
import com.rest.resttest.service.MessageService;

@RestController
public class HomeController {

	@Autowired
	private MessageService messageService;
	
	@RequestMapping("/")
	public String welcome() {//Welcome page, non-rest
		return "Welcome to RestTemplate Example.";
	}

	@RequestMapping("/hello/{player}")
	public Message message(@PathVariable String player) {//REST Endpoint.
		return messageService.getMessage(player);
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	
	
}
