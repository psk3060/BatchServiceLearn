package com.learn.batch.config;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.learn.batch.task.ReceiverTask;

// @Configuration
public class RedisMessageConfiguration {
	
	@SuppressWarnings("unused")
	final private static Logger logger = LoggerFactory.getLogger(RedisMessageConfiguration.class);
	
	/**
	 * sendMessage를 message로 해야 함
	 * @param connectionFactory
	 * @param listenerAdapter
	 * @return
	 */
	@Bean
	public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
		
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter, new PatternTopic("message"));
		
		return container;
	}
	
	/**
	 *  메시지 리스너 등록
	 *  - 처리 메소드 명 입력 : receiveMessageProc  
	 * @param receiverTask
	 * @return
	 */
	@Bean
	public MessageListenerAdapter listenerAdapter(ReceiverTask receiverTask) {
		return new MessageListenerAdapter(receiverTask, "receiveMessageProc");
	}
	
	@Bean
	public StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
		return new StringRedisTemplate(connectionFactory);
	}
	
}
