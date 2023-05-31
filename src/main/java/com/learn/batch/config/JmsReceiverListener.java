package com.learn.batch.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.learn.batch.service.model.EmailJmsVo;

@Component
public class JmsReceiverListener {
	
	final static Logger logger = LoggerFactory.getLogger(JmsReceiverListener.class);
	
	private EmailJmsVo receivedEmail;
	
	/**
	 * 1. jmsTemplate.convertAndSend를 이용하여  JMS 브로커로 전송(@JmsListener의 destination)
	 * 2. @JmsListener에서 메시지 수신
	 * @param email
	 */
	@JmsListener(destination = "jmsMailBox", containerFactory = "myJmsContainerFactory", concurrency = "1")
	public void receiveMessage(EmailJmsVo email) {
		logger.error("Received Jms Message<{}>", email.toString());
		this.receivedEmail = email;
	}
	
	public EmailJmsVo getEmail() {
		return receivedEmail;
	}
	
}
