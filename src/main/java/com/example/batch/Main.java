package com.example.batch;

import com.example.batch.KibanaExchangeReport.KibanaExchangeReportBatchConfig;
import com.example.batch.Kibanaformulaarchived.KibanaFormulaArchiveBatchConfig;
import com.example.batch.kibanacampaigndomain.KibanaCampaignDomainBatchConfig;
import com.example.batch.kibanacampaigndomain.domainquery2.KibanaCampaignDomain2BatchConfig;
import com.example.batch.kibanacountrycity.KibanaCountryCityBatchConfig;
import com.example.batch.kibanacreativesreport.KibanaCreativesBatchConfig;
import com.example.batch.kibanaformula.KibanaFormulaBatchConfig;
import com.example.batch.kibanapublisher.KibanaPublisherBatchConfig;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@EnableBatchProcessing
public class Main {


    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(
                        Main.class,
                        KibanaFormulaArchiveBatchConfig.class,
                        KibanaFormulaBatchConfig.class,
                        KibanaCampaignDomain2BatchConfig.class,
                        KibanaCampaignDomainBatchConfig.class,
                        KibanaExchangeReportBatchConfig.class,
                        KibanaCreativesBatchConfig.class,
                        KibanaPublisherBatchConfig.class,
                        KibanaCountryCityBatchConfig.class,
                        DataSourceConfig.class
                );

        ctx.registerShutdownHook();

        System.out.println("✓ Application started...");
        System.out.println("✓ Scheduling enabled...");
        System.out.println("✓ Batch processing enabled...");

        // Keep JVM alive so scheduler can run
        synchronized (Main.class) {
            try {
                Main.class.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
