package com.learn.batch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.learn.batch.service.model.EmailJmsVo;

@Component
public class JmsReceiverListener {
	
	final static Logger logger = LoggerFactory.getLogger(JmsReceiverListener.class);
	
	@JmsListener(destination = "jmsMailBox", containerFactory = "myJmsContainerFactory")
	public void receiveMessage(EmailJmsVo email) {
		logger.error("Received Jms Message<{}>", email.toString());
		
	}
	
}
