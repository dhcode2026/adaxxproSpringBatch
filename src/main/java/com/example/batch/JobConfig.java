//package com.example.batch;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.database.JdbcCursorItemReader;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//import java.util.Map;
//
//@Configuration
//public class JobConfig {
//
//
//    @Bean
//    public Step transferStep(JobRepository jobRepository,
//                             PlatformTransactionManager transactionManager,
//                             JdbcCursorItemReader<Map<String, Object>> genericReader,
//                             ItemWriter<Map<String, Object>> genericWriter) {
//
//        return new StepBuilder("transferStep", jobRepository)
//                .<Map<String, Object>, Map<String, Object>>chunk(500, transactionManager)
//                .reader(genericReader)
//                .writer(genericWriter)
//                .faultTolerant()
//                .skip(Exception.class)
//                .skipLimit(10)
//                .build();
//    }
//
//    @Bean
//    public Job transferJob(JobRepository jobRepository, Step transferStep) {
//        return new JobBuilder("transferJob", jobRepository)
//                .start(transferStep)
//                .build();
//    }
//}
