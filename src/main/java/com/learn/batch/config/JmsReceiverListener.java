package com.learn.batch.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.learn.batch.service.model.EmailJmsVo;

import jakarta.jms.BytesMessage;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.ObjectMessage;
import jakarta.jms.TextMessage;

@Component
public class JmsReceiverListener {
	
	final static Logger logger = LoggerFactory.getLogger(JmsReceiverListener.class);
	
	private EmailJmsVo receivedEmail;
	
	/**
	 * 1. jmsTemplate.convertAndSend를 이용하여  JMS 브로커로 전송(@JmsListener의 destination)
	 * 2. @JmsListener에서 메시지 수신
	 * @param email
	 */
	/*
	@JmsListener(destination = "jmsMailBox", containerFactory = "myJmsContainerFactory", concurrency = "1")
	public void receiveMessage(EmailJmsVo email) {
		logger.error("Received Jms Message<{}>", email.toString());
		this.receivedEmail = email;
		
	}
	*/
	
	@JmsListener(destination = "jmsMailBox", containerFactory = "myJmsContainerFactory", concurrency = "1")
	public void receiveMessage(Message message) {
		
		EmailJmsVo email = null;
		
		try {
			String messageID = message.getJMSMessageID();
			
			if( message instanceof TextMessage ) {
				
				TextMessage textMessage = (TextMessage) message;
				
				String json = textMessage.getText();
				
				email = EmailJmsVo.isValue(json);
				
			}
			
			if( email == null ) {
				throw new Exception("Null Pointer Email Body");
				
			}
			
			logger.error("Received Jms Message<{}>", email.toString());
			
			email.setMesssgeId(messageID);
			this.receivedEmail = email;
			
		} catch (JMSException jmse) {
			logger.error("receiveMessage jmsexception is => ", jmse);
			
		} catch(Exception e) {
			logger.error("receiveMessage exception is => ", e);
			
		}
		
	}
	
	public EmailJmsVo getEmail() {
		return receivedEmail;
	}
	
}
