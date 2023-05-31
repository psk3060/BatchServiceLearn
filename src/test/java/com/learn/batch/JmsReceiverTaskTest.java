package com.learn.batch;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsTemplate;

import com.learn.batch.config.JmsReceiverListener;
import com.learn.batch.service.model.EmailJmsVo;
import com.learn.batch.task.ReceiverTask;

import jakarta.jms.DeliveryMode;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;

@SpringBootTest
public class JmsReceiverTaskTest {
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Autowired
	ReceiverTask receiverTask;
    
	@Autowired
    private JmsReceiverListener jmsReceiverListener;
	
	final private static Logger logger = LoggerFactory.getLogger(JmsReceiverTaskTest.class);
	
	@Test
	public void receiverJmsTest() throws Exception {
		
		String messageText = "Hello, Artemis!";
		
		EmailJmsVo vo = new EmailJmsVo();
		
		vo.setFrom("from@dk.com");
		vo.setTo("info@example.com");
		vo.setBody(messageText);
        
        jmsTemplate.convertAndSend("jmsMailBox", vo);
        
        Thread.sleep(5000);
        
        // 마지막에 등록한 EMAIL
        /**
         * convertAndSend를 호출하자마자 ACK(완료, receive에서 확인 불가) 처리하여, 리스너에서 객체 보관
         */
        EmailJmsVo receivedEmail = jmsReceiverListener.getEmail();
        
        assertEquals(vo, receivedEmail);
        
        
	}
	

	
}
