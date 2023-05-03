package com.learn.batch.task;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Message 처리 객체 
 */
public class ReceiverTask {

	final private static Logger logger = LoggerFactory.getLogger(ReceiverTask.class);
	
	private AtomicInteger counter = new AtomicInteger();
	
	private String message;
	
	/**
	 * 쓰레드 세입한 카운트다운
	 */
	private CountDownLatch latch;
	
	 public ReceiverTask(CountDownLatch latch) {
		 this.latch = latch;
	 }
	
	/**
	 * Redis Message 처리 메소드 
	 * @param message
	 */
	public void receiveMessageProc(String message) {
		logger.info("Received <{}>", message);
		
		counter.incrementAndGet();
		this.message = message;
		latch.countDown();
		
	}
	
	/**
	 * 호출 횟수
	 * @return
	 */
	public int getCount() {
		return counter.get();
		
	}
	
	public String getMessage() {
		return this.message;
	}

	public CountDownLatch getLatch() {
		return latch;
	}

	public void setLatch(CountDownLatch latch) {
		this.latch = latch;
	}
	
	
}
