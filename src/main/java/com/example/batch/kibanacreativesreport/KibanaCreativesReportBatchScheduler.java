package com.example.batch.kibanacreativesreport;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KibanaCreativesReportBatchScheduler {

    private JobLauncher jobLauncher;
    private Job kibanaCreativesJob;

    public KibanaCreativesReportBatchScheduler(JobLauncher jobLauncher, @Qualifier("kibanaCreativesJob") Job kibanaCreativesJob) {
        this.jobLauncher = jobLauncher;
        this.kibanaCreativesJob = kibanaCreativesJob;
    }

    @Scheduled(cron = "0 30 * * * ?")
    // Every hour at minute 30
    public void runkibanaCreativesJob() {
        try {
            System.out.println("Starting KibanaCreativesJob");

            JobParameters params = new JobParametersBuilder()
                    .addLong("runTimestamp", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(kibanaCreativesJob, params);
            System.out.println("Finished KibanaCreativesJob");


        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

}
