package com.example.batch.kibanacampaigndomain.domainquery2;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class KibanaCampaignDomain2BatchScheduler {

    private JobLauncher jobLauncher;
    private Job kibanaCampaignDomain2Job;

    public KibanaCampaignDomain2BatchScheduler(JobLauncher jobLauncher,@Qualifier("kibanaCampaignDomain2Job") Job kibanaCampaignDomain2Job) {
        this.jobLauncher = jobLauncher;
        this.kibanaCampaignDomain2Job = kibanaCampaignDomain2Job;
    }

//    @Scheduled(cron = "0 30 10 * * *")
    public void runKibanaCampaignDomain2Job() {
        try {
            System.out.println("\n========================================");
            System.out.println(" Starting KibanaCampaignDomain2 Job");
            System.out.println("========================================");

            JobParameters params = new JobParametersBuilder()
                    .addLong("runTimestamp", System.currentTimeMillis())
                    .toJobParameters();

            var result = jobLauncher.run(kibanaCampaignDomain2Job, params);

            System.out.println("Job Status: " + result.getStatus());
            System.out.println("Exit Code: " + result.getExitStatus());
            System.out.println("✓ Finished KibanaCampaignDomain2 Job");
            System.out.println("========================================\n");

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
