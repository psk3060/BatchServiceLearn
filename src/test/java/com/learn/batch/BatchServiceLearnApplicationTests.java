package com.learn.batch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.learn.batch.task.LoggingScheduledTasks;

import static org.assertj.core.api.Assertions.assertThat;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

import org.awaitility.Durations;

@SpringBootTest
class BatchServiceLearnApplicationTests {
	
	@SpyBean LoggingScheduledTasks task;

	@Test
	public void contextLoads() {
		assertThat(task).isNotNull();
	}
	
	@Test
	public void reportCurrentTime() {

		await()
			// 10 초 동안 수행
			.atMost(Durations.TEN_SECONDS).untilAsserted(() -> {
				// 최소 호출 횟수 : 2
				verify(task, atLeast(2)).reportCurrentTime();
			});
	}
	
	
	
}
