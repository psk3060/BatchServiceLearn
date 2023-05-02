package com.learn.batch.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LoggingScheduledTasks {
	
	final private static Logger logger = LoggerFactory.getLogger(LoggingScheduledTasks.class);
	
	final private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 5초 마다 수행
	 */
	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime() {
		logger.info("The time now is {}", sdf.format(new Date()));
	}
	
}
