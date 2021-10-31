package com.thearaseng.httpecho.service.impl;

import com.thearaseng.httpecho.model.MessageModel;
import com.thearaseng.httpecho.service.EchoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EchoServiceImpl implements EchoService {

	@Value("${app.message}")
	private String message;
	@Value("${app.parent-endpoint}")
	private String parentEndpoint;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public MessageModel getMessage() {

		MessageModel messageModel = new MessageModel();

		if (StringUtils.isEmpty(parentEndpoint)) {
			messageModel.setMessage(message);
			return messageModel;
		}

		ResponseEntity<MessageModel> response = restTemplate.getForEntity(parentEndpoint, MessageModel.class);
		MessageModel parentMessage = response.getBody();
		messageModel.setMessage(message + " " + parentMessage.getMessage());
		return messageModel;
	}

}
