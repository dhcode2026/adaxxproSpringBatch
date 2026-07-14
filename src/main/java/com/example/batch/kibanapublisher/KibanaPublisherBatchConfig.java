package com.example.batch.kibanapublisher;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;
import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.example.batch.kibanapublisher")
public class KibanaPublisherBatchConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @StepScope
    public KibanaPublisherReader kibanaPublisherReader(RestTemplate restTemplate) {
        return new KibanaPublisherReader(restTemplate);
    }

    @Bean
    @StepScope
    public KibanaPublisherProcessor kibanaPublisherProcessor() {
        return new KibanaPublisherProcessor();
    }

    @Bean
    @StepScope
    public KibanaPublisherWriter kibanaPublisherWriter(DataSource dataSource) {
        JdbcBatchItemWriter<PublishInventory> jdbcWriter = new JdbcBatchItemWriter<>();
        jdbcWriter.setDataSource(dataSource);
        jdbcWriter.setSql(
                "INSERT INTO publish_inventory (" +
                        "publisher_id, adz_type, publisher_name, domain, category, country, region, city, " +
                        "exchange, format, device_type, device_make, device_model, device_os, device_os_version, ad_size, device_id, " +
                        "max_bid_floor, min_bid_floor, observedcpm, execution_date, created_at, updated_at" +
                        ") VALUES (" +
                        ":publisherId, :adzType, :publisherName, :domain, :category, :country, :region, :city, " +
                        ":exchange, :format, :deviceType, :deviceMake, :deviceModel, :deviceOs, :deviceOsVersion, :adSize, :deviceId, " +
                        ":maxBidFloor, :minBidFloor, :observedCPM, :executionDate, :createdAt, :updatedAt" +
                        ")"
        );
        jdbcWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        jdbcWriter.afterPropertiesSet();
        return new KibanaPublisherWriter(jdbcWriter);
    }

    @Bean
    public Step kibanaPublisherStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            KibanaPublisherReader kibanaPublisherReader,
            KibanaPublisherProcessor kibanaPublisherProcessor,
            KibanaPublisherWriter kibanaPublisherWriter) {
        return new StepBuilder("kibanaPublisherStep", jobRepository)
                .<PublishInventory, PublishInventory>chunk(50, transactionManager)
                .reader(kibanaPublisherReader)
                .processor(kibanaPublisherProcessor)
                .writer(kibanaPublisherWriter)
                .build();
    }

    @Bean
    public Job kibanaPublisherJob(JobRepository jobRepository,
                                  @Qualifier("kibanaPublisherStep") Step kibanaPublisherStep) {
        return new JobBuilder("kibanaPublisherJob", jobRepository)
                .start(kibanaPublisherStep)
                .build();
    }
}