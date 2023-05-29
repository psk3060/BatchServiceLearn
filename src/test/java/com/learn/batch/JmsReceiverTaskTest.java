package com.learn.batch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

import com.learn.batch.service.model.EmailJmsVo;

import jakarta.jms.TextMessage;

@SpringBootTest

public class JmsReceiverTaskTest {
	
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@SuppressWarnings("unused")
	final private static Logger logger = LoggerFactory.getLogger(JmsReceiverTaskTest.class);
	
	@Test
	public void receiverJmsTest() throws Exception {
		
		String messageText = "Hello, Artemis!";
        jmsTemplate.convertAndSend("jmsMailBox", new EmailJmsVo("from@dk.com", "info@example.com", "Hello, Artemis!"));
        
        EmailJmsVo receivedMessage = (EmailJmsVo) jmsTemplate.receive("jmsMailBox");
        String receivedText = receivedMessage.getBody();

        assertEquals(messageText, receivedText);
		
	}
}
