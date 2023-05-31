package com.learn.batch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.learn.batch.service.model.PersonTestVO;

// @Component
class JobCompletionNotificationListener implements JobExecutionListener {
	
	
	final private static Logger logger = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * Job 완료 이후
	 */
	@Override
	public void afterJob(JobExecution jobExecution) {
		// Job이 완료되었다면
		if( jobExecution.getStatus() == BatchStatus.COMPLETED ) {
			logger.info("JOB FINISHED! Time to verify the results");
			
			jdbcTemplate.query("SELECT first_name, last_name FROM people", 
					(rs, row) -> new PersonTestVO(
							rs.getString("first_name")
							, rs.getString(2)
					)
			)
			.forEach(person -> logger.info("Found <{{}}> in the database.", person));;
			
		}
		
		JobExecutionListener.super.afterJob(jobExecution);
	}
	
}
