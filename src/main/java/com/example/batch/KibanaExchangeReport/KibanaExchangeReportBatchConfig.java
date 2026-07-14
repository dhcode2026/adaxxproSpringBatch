package com.example.batch.KibanaExchangeReport;

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
@ComponentScan(basePackages = "com.example.batch.kibanaexchangereport")
public class KibanaExchangeReportBatchConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @StepScope
    public KibanaExchangeReportReader kibanaExchangeReportReader(RestTemplate restTemplate) {
        return new KibanaExchangeReportReader(restTemplate);
    }

    @Bean
    @StepScope
    public KibanaExchangeReportProcessor kibanaExchangeReportProcessor() {
        return new KibanaExchangeReportProcessor();
    }

    @Bean
    @StepScope
    public KibanaExchangeReportWriter kibanaExchangeReportWriter(DataSource dataSource) {
        JdbcBatchItemWriter<KibanaExchangeReport> jdbcWriter = new JdbcBatchItemWriter<>();
        jdbcWriter.setDataSource(dataSource);
        jdbcWriter.setSql(
                "INSERT INTO kibana_exchange_report (" +
                        " total_request, total_response, name, total_fill_rate, " +
                        "impression, revenue, media_ecpm, clicks, cmp_bid, platform_margin, " +
                        "media_spend, created_at, updated_at, hour" +
                        ") VALUES (" +
                        ":totalRequest, :totalResponse, :name, :totalFillRate, " +
                        ":impression, :revenue, :mediaEcpm, :clicks, :cmpBid, :platformMargin, " +
                        ":mediaSpend, :createdAt, :updatedAt, :hour" +
                        ")"
        );
        jdbcWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        jdbcWriter.afterPropertiesSet();
        return new KibanaExchangeReportWriter(jdbcWriter);
    }

    @Bean
    public Step kibanaExchangeReportStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            KibanaExchangeReportReader kibanaExchangeReportReader,
            KibanaExchangeReportProcessor kibanaExchangeReportProcessor,
            KibanaExchangeReportWriter kibanaExchangeReportWriter) {
        return new StepBuilder("kibanaExchangeReportStep", jobRepository)
                .<KibanaExchangeReport, KibanaExchangeReport>chunk(50, transactionManager)
                .reader(kibanaExchangeReportReader)
                .processor(kibanaExchangeReportProcessor)
                .writer(kibanaExchangeReportWriter)
                .build();
    }

    @Bean
    public Job kibanaExchangeReportJob(JobRepository jobRepository,
                                  @Qualifier("kibanaExchangeReportStep") Step kibanaExchangeReportStep) {
        return new JobBuilder("kibanaExchangeReportJob", jobRepository)
                .start(kibanaExchangeReportStep)
                .build();
    }
}
