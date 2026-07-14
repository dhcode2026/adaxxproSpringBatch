package com.example.batch.kibanacountrycity;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KibanaCountryCityBatchScheduler {

    private JobLauncher jobLauncher;
    private Job kibanaCountryCityJob;

    public KibanaCountryCityBatchScheduler(JobLauncher jobLauncher, @Qualifier("kibanaCountryCityJob")Job kibanaCountryCityJob) {
        this.jobLauncher = jobLauncher;
        this.kibanaCountryCityJob = kibanaCountryCityJob;
    }

    @Scheduled(cron = "0 20 * * * ?")
   // At 20th minute of every hour, every day
    public void runKibanaCountryCityJob() {
        try {


            System.out.println("Starting KibanaCountryCityJob");
            JobParameters params = new JobParametersBuilder()
                    .addLong("runTimestamp", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(kibanaCountryCityJob, params);

            System.out.println("Finished KibanaCountryCityJob");


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
