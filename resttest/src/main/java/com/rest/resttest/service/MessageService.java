package com.rest.resttest.service;

import org.springframework.stereotype.Service;

import com.rest.resttest.domain.Message;

@Service
public class MessageService {
	public Message getMessage(String player){
		Message msg = new Message(player, "Hello " + player);
		return msg;
	}

}
