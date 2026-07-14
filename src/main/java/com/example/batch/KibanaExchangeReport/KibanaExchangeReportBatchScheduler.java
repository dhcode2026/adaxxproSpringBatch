package com.example.batch.KibanaExchangeReport;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KibanaExchangeReportBatchScheduler {

    private JobLauncher jobLauncher;
    private Job kibanaExchangeReportJob;

    public KibanaExchangeReportBatchScheduler(JobLauncher jobLauncher, @Qualifier("kibanaExchangeReportJob") Job kibanaExchangeReportJob) {
        this.jobLauncher = jobLauncher;
        this.kibanaExchangeReportJob = kibanaExchangeReportJob;
    }

//    @Scheduled(cron = "0 0 * * * ?")
    //At the start of every hour
    public void runKibanaExchangeReportJob() {
        try {

            System.out.println("Running KibanaExchangeReportJob");
            JobParameters params = new JobParametersBuilder()
                    .addLong("runTimestamp", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(kibanaExchangeReportJob, params);
            System.out.println("Finished KibanaExchangeReportJob");


        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
