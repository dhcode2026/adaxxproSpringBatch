package com.example.batch.kibanacreativesreport;

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
@ComponentScan(basePackages = "com.example.batch.kibanacreativesreport")
public class KibanaCreativesBatchConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    @StepScope
    public KibanaCreativesReader kibanaCreativesReader(RestTemplate restTemplate) {
        return new KibanaCreativesReader(restTemplate);
    }

    @Bean
    @StepScope
    public KibanaCreativesProcessor kibanaCreativesProcessor() {
        return new KibanaCreativesProcessor();
    }

    @Bean
    @StepScope
    public KibanaCreativesWriter kibanaCreativesWriter(DataSource dataSource) {
        JdbcBatchItemWriter<KibanaCreativesReport> jdbcWriter = new JdbcBatchItemWriter<>();
        jdbcWriter.setDataSource(dataSource);
        jdbcWriter.setSql(
                "INSERT INTO kibana_creatives_report (" +
                        " exchange_name, creative_id, total_win, " +
                        "total_request, total_response, clicks, created_at, updated_at" +
                        ") VALUES (" +
                        ":exchangeName, :creativeId, :totalWin, " +
                        ":totalRequest, :totalResponse, :clicks, :createdAt, :updatedAt" +
                        ")"
        );
        jdbcWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        jdbcWriter.afterPropertiesSet();
        return new KibanaCreativesWriter(jdbcWriter);
    }

    @Bean
    public Step kibanaCreativesStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            KibanaCreativesReader kibanaCreativesReader,
            KibanaCreativesProcessor kibanaCreativesProcessor,
            KibanaCreativesWriter kibanaCreativesWriter) {
        return new StepBuilder("kibanaCreativesStep", jobRepository)
                .<KibanaCreativesReport, KibanaCreativesReport>chunk(50, transactionManager)
                .reader(kibanaCreativesReader)
                .processor(kibanaCreativesProcessor)
                .writer(kibanaCreativesWriter)
                .build();
    }

    @Bean
    public Job kibanaCreativesJob(JobRepository jobRepository,
                                  @Qualifier("kibanaCreativesStep") Step kibanaCreativesStep) {
        return new JobBuilder("kibanaCreativesJob", jobRepository)
                .start(kibanaCreativesStep)
                .build();
    }
}
