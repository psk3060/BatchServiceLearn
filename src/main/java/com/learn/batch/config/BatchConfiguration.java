package com.learn.batch.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import com.learn.batch.service.model.PersonTestVO;

@Configuration
@SuppressWarnings({ "rawtypes", "unchecked" })
public class BatchConfiguration {
	
	final private static Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);
	
	/**
	 * 리소스 Read
	 * @return
	 */
	@Bean
	public FlatFileItemReader<PersonTestVO> reader() {
		return new FlatFileItemReaderBuilder()
					.name("personItemReader")
					.resource(new ClassPathResource("sample-data.csv"))
					.delimited()
					.names(new String[] {"firstName", "lastName"})
					.fieldSetMapper(new BeanWrapperFieldSetMapper<PersonTestVO>() {{
						setTargetType(PersonTestVO.class);
					}})
					.build()
		;
		
	}
	
	/**
	 * ItemProcessor Bean
	 * @return
	 */
	@Bean
	public PersonItemProcessor processor() {
		return new PersonItemProcessor();
	}
	
	/**
	 * Database Writer
	 * - chunk 단위의 List
	 * @param dataSource
	 */
	@Bean
	public JdbcBatchItemWriter<PersonTestVO> writer(DataSource dataSource) {

		return new JdbcBatchItemWriterBuilder()
					.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
					.sql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)")
					.dataSource(dataSource)
					.build()
		;
	}
	
	/**
	 * job 실행 -> step1 실행(chunk, reader, processor, writer 설정) -> job 완료  
	 */
	@Bean
	public Step firstSetting(JobRepository jobRepository, PlatformTransactionManager transactionManager, JdbcBatchItemWriter<PersonTestVO> writer) {
		return new StepBuilder("step1", jobRepository)
					.<PersonTestVO, PersonTestVO> chunk(10, transactionManager)
					.reader(reader())
					.processor(processor())
					.writer(writer)
					.build()
		;
	}
	
	
	/**
	 * Run Job
	 */
	@Bean
	public Job importUserJob(JobRepository jobRepository, JobCompletionNotificationListener listener, Step firstSetting) {
		return new JobBuilder("importUserJob", jobRepository)
					.incrementer(new RunIdIncrementer())
					.listener(listener)
					.flow(firstSetting)
					.end()
					.build();
	}
	
	/**
	 * ItemProcessor
	 *  - Reader로 부터 넘겨받은 데이터를 개별로 가공 또는 필터링(필수 아님)
	 */
	class PersonItemProcessor implements ItemProcessor<PersonTestVO, PersonTestVO> {

		@Override
		public PersonTestVO process(PersonTestVO item) throws Exception {
			
			final String firstName = item.getFirstName();
			final String lastName = item.getLastName();
			
			final PersonTestVO person = new PersonTestVO(firstName, lastName);
			
			logger.info("Converting (" + item + ") into (" + person + ")");
			
			return person;
		}
		
	}
	
}
