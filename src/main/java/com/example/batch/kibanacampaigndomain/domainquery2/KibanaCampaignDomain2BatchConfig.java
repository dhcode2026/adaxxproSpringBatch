package com.example.batch.kibanacampaigndomain.domainquery2;

import com.example.batch.kibanacampaigndomain.KibanaCampaignDomain;
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
@ComponentScan(basePackages = "com.example.batch.kibanacampaigndomain.domainquery2")
public class KibanaCampaignDomain2BatchConfig {

    @Bean
    @StepScope
    public KibanaCampaignDomain2Reader kibanaCampaignDomain2Reader(RestTemplate restTemplate, JdbcTemplate jdbcTemplate)
    {
        System.out.println("✓ Creating KibanaCampaignDomain2Reader bean");
        return new KibanaCampaignDomain2Reader(restTemplate, jdbcTemplate);
    }

    @Bean
    @StepScope
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @StepScope
    public KibanaCampaignDomain2Processor kibanaCampaignDomain2Processor()
    {
        return new KibanaCampaignDomain2Processor();
    }

    @Bean
    @StepScope
    public KibanaCampaignDomain2Writer kibanaCampaignDomain2Writer(@Qualifier("writer") JdbcBatchItemWriter<KibanaCampaignDomain> jdbcWriter)
    {
        System.out.println("✓ Creating KibanaCampaignDomainWriter bean");
        return new KibanaCampaignDomain2Writer(jdbcWriter);
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
    public Job kibanaCampaignDomain2Job(JobRepository jobRepository,
                                       @Qualifier("kibanaCampaignDomain2Step") Step kibanaCampaignDomain2Step)
    {
        System.out.println("✓ Creating kibanaCampaignDomain2Job bean");
        return new JobBuilder("kibanaCampaignDomain2Job",jobRepository)
                .start(kibanaCampaignDomain2Step)
                .build();
    }

    @Bean
    public Step kibanaCampaignDomain2Step(JobRepository jobRepository,
                                         PlatformTransactionManager platformTransactionManager,
                                         KibanaCampaignDomain2Reader kibanaCampaignDomain2Reader,
                                         KibanaCampaignDomain2Processor kibanaCampaignDomain2Processor,
                                         KibanaCampaignDomain2Writer kibanaCampaignDomain2Writer
    )
    {
        System.out.println("✓ Creating KibanaCampaignDomain2Step bean");
        return new StepBuilder("kibanaCampaignDomain2Step",jobRepository)
                .<KibanaCampaignDomain,KibanaCampaignDomain>chunk(50,platformTransactionManager)
                .reader(kibanaCampaignDomain2Reader)
                .processor(kibanaCampaignDomain2Processor)
                .writer(kibanaCampaignDomain2Writer)
                .build();
    }
}
