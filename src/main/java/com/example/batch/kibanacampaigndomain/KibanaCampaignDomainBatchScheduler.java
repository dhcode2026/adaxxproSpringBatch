package com.example.batch.kibanacampaigndomain;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KibanaCampaignDomainBatchScheduler {

    private JobLauncher jobLauncher;
    private Job kibanaCampaignDomainJob;

    public KibanaCampaignDomainBatchScheduler(JobLauncher jobLauncher,@Qualifier("kibanaCampaignDomainJob") Job kibanaCampaignDomainJob) {
        this.jobLauncher = jobLauncher;
        this.kibanaCampaignDomainJob = kibanaCampaignDomainJob;
    }

//    @Scheduled(cron = "0 59 15 * * *")
    public void runKibanaCampaignDomainJob() {
        try {
            System.out.println("\n========================================");
            System.out.println("🔄 Starting KibanaCampaignDomain Job");
            System.out.println("========================================");

            JobParameters params = new JobParametersBuilder()
                    .addLong("runTimestamp", System.currentTimeMillis())
                    .toJobParameters();

            var result = jobLauncher.run(kibanaCampaignDomainJob, params);

            System.out.println("Job Status: " + result.getStatus());
            System.out.println("Exit Code: " + result.getExitStatus());
            System.out.println("✓ Finished KibanaCampaignDomain Job");
            System.out.println("========================================\n");

        } catch (Exception e) {
            System.out.println("❌ ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
