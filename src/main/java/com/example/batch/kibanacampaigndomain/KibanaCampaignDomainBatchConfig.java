package com.example.batch.kibanacampaigndomain;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;
import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.example.batch.kibanacampaigndomain")
public class KibanaCampaignDomainBatchConfig {

    @Bean
    @StepScope
    public KibanaCampaignDomainReader kibanaCampaignDomainReader(RestTemplate restTemplate,JdbcTemplate jdbcTemplate)
    {
        System.out.println("✓ Creating KibanaCampaignDomainReader bean");
        return new KibanaCampaignDomainReader(restTemplate, jdbcTemplate);
    }

    @Bean
    @StepScope
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @StepScope
    public KibanaCampaignDomainProcessor kibanaCampaignDomainProcessor()
    {
        return new KibanaCampaignDomainProcessor();
    }

    @Bean
    @StepScope
    public KibanaCampaignDomainWriter kibanaCampaignDomainWriter(@Qualifier("writer") JdbcBatchItemWriter<KibanaCampaignDomain> jdbcWriter)
    {
        System.out.println("✓ Creating KibanaCampaignDomainWriter bean");
        return new KibanaCampaignDomainWriter(jdbcWriter);
    }

    @Bean(name = "writer")
    public JdbcBatchItemWriter<KibanaCampaignDomain> writer(DataSource dataSource) {
        System.out.println("✓ Creating JdbcBatchItemWriter bean");
        JdbcBatchItemWriter<KibanaCampaignDomain> writer = new JdbcBatchItemWriter<>();

        writer.setDataSource(dataSource);
        writer.setSql(
                "INSERT INTO kibana_campaign_domain (" +
                        " campaign_id, request_id, creative_id, domain_type, domain, " +
                        "clicks, conversion_type, cpm_bid, impression, video_start, video_25, video_50, video_75, video_100, " +
                        "exchange, impression_update_time, clicks_update_time, conversion_update_time, video_update_time, " +
                        "created_at, updated_at" +
                        ") VALUES (" +
                        " :campaignId, :requestId, :creativeId, :domainType, :domain, " +
                        ":clicks, :conversionType, :cpmBid, :impression, :videoStart, :video25, :video50, :video75, :video100, " +
                        ":exchange, :impressionUpdateTime, :clicksUpdateTime, :conversionUpdateTime, :videoUpdateTime, " +
                        ":createdAt, :updatedAt" +
                        ")"
        );

        writer.setItemSqlParameterSourceProvider(
                new BeanPropertyItemSqlParameterSourceProvider<>()
        );

        writer.afterPropertiesSet();
        return writer;
    }

    @Bean
    public Job kibanaCampaignDomainJob(JobRepository jobRepository,
                                       @Qualifier("kibanaCampaignDomainStep") Step kibanaCampaignDomainStep)
    {
        System.out.println("✓ Creating kibanaCampaignDomainJob bean");
        return new JobBuilder("kibanaCampaignDomainJob",jobRepository)
                .start(kibanaCampaignDomainStep)
                .build();
    }

    @Bean
    public Step kibanaCampaignDomainStep(JobRepository jobRepository,
                                         PlatformTransactionManager platformTransactionManager,
                                         KibanaCampaignDomainReader kibanaCampaignDomainReader,
                                      KibanaCampaignDomainProcessor kibanaCampaignDomainProcessor,
                                      KibanaCampaignDomainWriter kibanaCampaignDomainWriter
    )
    {
        System.out.println("✓ Creating KibanaCampaignDomainStep bean");
        return new StepBuilder("kibanaCampaignDomainStep",jobRepository)
                .<KibanaCampaignDomain,KibanaCampaignDomain>chunk(50,platformTransactionManager)
                .reader(kibanaCampaignDomainReader)
                .processor(kibanaCampaignDomainProcessor)
                .writer(kibanaCampaignDomainWriter)
                .build();
    }


}

