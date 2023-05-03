package com.learn.batch;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.learn.batch.task.ReceiverTask;

@SpringBootTest
public class ReceiverTaskTest {
	
	
	@SuppressWarnings("unused")
	final private static Logger logger = LoggerFactory.getLogger(ReceiverTaskTest.class);
	
	@Autowired
	StringRedisTemplate template;
	
	@Autowired
	ReceiverTask receiverTask;
	
	@Test
	public void receiverTest() throws Exception {
		
		assertThat(receiverTask.getCount() == 0);
		template.convertAndSend("message", "Hello from Redis!");
		
		// 메시지 확인을 위한 잠시 멈춤
		receiverTask.getLatch().await(5000, TimeUnit.MILLISECONDS);
		
		assertThat(receiverTask.getCount() == 1);
		assertThat("Hello from Redis!".equals(receiverTask.getMessage()));
		
		template.convertAndSend("message", "Hello World");
		
		receiverTask.getLatch().await(5000, TimeUnit.MILLISECONDS);
		
		assertThat(receiverTask.getCount() == 2);
		assertThat("Hello World".equals(receiverTask.getMessage()));
		
	}
	
	
	
}
