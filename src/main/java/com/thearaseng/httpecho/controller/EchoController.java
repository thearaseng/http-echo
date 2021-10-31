package com.thearaseng.httpecho.controller;

import com.thearaseng.httpecho.model.MessageModel;
import com.thearaseng.httpecho.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

	@Autowired
	private EchoService echoService;

	@GetMapping("/")
	public MessageModel echo() {
		return this.echoService.getMessage();
	}

}
