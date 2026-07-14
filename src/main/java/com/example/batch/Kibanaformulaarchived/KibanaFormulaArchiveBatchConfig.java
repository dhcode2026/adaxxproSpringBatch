package com.example.batch.Kibanaformulaarchived;

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
@ComponentScan(basePackages = "com.example.batch.Kibanaformulaarchived")
public class KibanaFormulaArchiveBatchConfig {

    @Bean
    @StepScope
    public KibanaFormulaArchiveReader kibanaFormulaArchiveReader(RestTemplate restTemplate, JdbcTemplate jdbcTemplate)
    {
        return new KibanaFormulaArchiveReader(restTemplate, jdbcTemplate);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    @StepScope
    public KibanaFormulaArchiveProcessor kibanaFormulaArchiveProcessor()
    {
        return new KibanaFormulaArchiveProcessor();
    }

    // FIX: bean name changed from "writer" -> "KibanaFormulaArchiveWriter"
    // so it matches the @Qualifier used in kibanaFormulaArchiveWriter() below.
    @Bean(name = "KibanaFormulaArchiveWriter")
    @StepScope
    public JdbcBatchItemWriter<KibanaFormulaArchive> writer(DataSource dataSource) {
        System.out.println("✓ Creating JdbcBatchItemWriter bean");
        JdbcBatchItemWriter<KibanaFormulaArchive> writer = new JdbcBatchItemWriter<>();

        writer.setDataSource(dataSource);
        writer.setSql(
                "INSERT INTO Kibana_formula_archive (" +
                        "campaign_id, total_clicks, total_request, conversion, total_pixels, total_cost, total_win, total_response, " +
                        "ctr, cvr, win_percentage, impressions_won, total_revenue, ecpm, media_spend, epc, total_rpm, roas, revenue_per_conversion, " +
                        "total_ecpc, total_ecpa, completion_rate, start_count, percent25, percent50, complete_count, percent75, " +
                        "bid_publisher, platform_fee, platform_ecpm, media_ecpm, platform_margin, platform_spend, total_spend, " +
                        "exchange, created_at, updated_at, date" +
                        ") VALUES (" +
                        " :campaignId, :totalClicks, :totalRequest, :conversion, :totalPixels, :totalCost, :totalWin, :totalResponse, " +
                        ":ctr, :cvr, :winPercentage, :impressionsWon, :totalRevenue, :ecpm, :mediaSpend, :epc, :totalRpm, :roas, :revenuePerConversion, " +
                        ":totalEcpc, :totalEcpa, :completionRate, :startCount, :percent25, :percent50, :completeCount, :percent75, " +
                        ":bidPublisher, :platformFee, :platformEcpm, :mediaEcpm, :platformMargin, :platformSpend, :totalSpend, " +
                        ":exchange, :createdAt, :updatedAt, :date" +
                        ")"
        );

        writer.setItemSqlParameterSourceProvider(
                new BeanPropertyItemSqlParameterSourceProvider<>()
        );

        writer.afterPropertiesSet();
        return writer;
    }

    @Bean
    public KibanaFormulaArchiveWriter kibanaFormulaArchiveWriter(
            @Qualifier("KibanaFormulaArchiveWriter") JdbcBatchItemWriter<KibanaFormulaArchive> jdbcWriter)
    {
        System.out.println("✓ Creating KibanaFormulaArchiveWriter bean");
        return new KibanaFormulaArchiveWriter(jdbcWriter);
    }

    @Bean
    public Job KibanaFormulaArchivedJob(JobRepository jobRepository,
                                        @Qualifier("kibanaFormulaArchiveStep") Step kibanaFormulaArchivedStep)
    {
        System.out.println("✓ Creating KibanaFormulaArchivedJob bean");
        return new JobBuilder("KibanaFormulaArchivedJob",jobRepository)
                .start(kibanaFormulaArchivedStep)
                .build();
    }

    @Bean
    public Step kibanaFormulaArchiveStep(JobRepository jobRepository,
                                         PlatformTransactionManager platformTransactionManager,
                                         KibanaFormulaArchiveReader kibanaFormulaArchiveReader,
                                         KibanaFormulaArchiveProcessor kibanaFormulaArchiveProcessor,
                                         KibanaFormulaArchiveWriter kibanaFormulaArchiveWriter
    )
    {
        System.out.println("✓ Creating KibanaFormulaArchiveStep bean");
        return new StepBuilder("kibanaFormulaArchiveStep",jobRepository)
                .<KibanaFormulaArchive,KibanaFormulaArchive>chunk(50,platformTransactionManager)
                .reader(kibanaFormulaArchiveReader)
                .processor(kibanaFormulaArchiveProcessor)
                .writer(kibanaFormulaArchiveWriter)
                .build();
    }
}