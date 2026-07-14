package com.example.batch.kibanacountrycity;

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
@ComponentScan(basePackages = "com.example.batch.kibanacountrycity")
public class KibanaCountryCityBatchConfig {


    @Bean
    @StepScope
    public KibanaCountryCityReader kibanaCountryCityReader(RestTemplate restTemplate)
    {
        return new KibanaCountryCityReader(restTemplate);
    }

    @Bean
    @StepScope
    public KibanaCountryCityProcessor kibanaCountryCityProcessor()
    {
        return new KibanaCountryCityProcessor();
    }

    @Bean
    @StepScope
    public KibanaCountryCityWriter kibanaCountryCityWriter(JdbcBatchItemWriter<KibanaCountryCity> dataSource)
    {
        return new KibanaCountryCityWriter(dataSource);
    }

    @Bean
    public Job kibanaCountryCityJob(JobRepository  jobRepository,
                                    @Qualifier("kibanaCountryCityStep") Step kibanaCountryCityStep)
    {
        return new JobBuilder("kibanaCountryCityJob",jobRepository)
                .start(kibanaCountryCityStep)
                .build();
    }

    @Bean
    public Step kibanaCountryCityStep(JobRepository jobRepository,
                                      PlatformTransactionManager platformTransactionManager,
                                      KibanaCountryCityReader kibanaCountryCityReader,
                                      KibanaCountryCityProcessor kibanaCountryCityProcessor,
                                      KibanaCountryCityWriter kibanaCountryCityWriter
                                      )
    {
        return new StepBuilder("kibanaCountryCityStep",jobRepository)
                .<KibanaCountryCity,KibanaCountryCity>chunk(50,platformTransactionManager)
                .reader(kibanaCountryCityReader)
                .processor(kibanaCountryCityProcessor)
                .writer(kibanaCountryCityWriter)
                .build();
    }


    @Bean
    public JdbcBatchItemWriter<KibanaCountryCity> writer(DataSource dataSource) {
        JdbcBatchItemWriter<KibanaCountryCity> writer = new JdbcBatchItemWriter<>();

        writer.setDataSource(dataSource);
        writer.setSql(
                "INSERT INTO kibana_country_city (" +
                        " exchange, creative_id, adz_type, campaign_id, " +
                        "country, city, wins_docs, date" +
                        ") VALUES (" +
                        " :exchange, :creativeId, :adzType, :campaignId, " +
                        ":country, :city, :winsDocs, :date" +
                        ")"
        );

        writer.setItemSqlParameterSourceProvider(
                new BeanPropertyItemSqlParameterSourceProvider<>()
        );

        writer.afterPropertiesSet();
        return writer;
    }
}
