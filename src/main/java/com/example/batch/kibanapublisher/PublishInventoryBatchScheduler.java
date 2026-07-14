package com.example.batch.kibanapublisher;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PublishInventoryBatchScheduler {

    private JobLauncher jobLauncher;
    private Job kibanaPublisherJob;

    public PublishInventoryBatchScheduler(JobLauncher jobLauncher, @Qualifier("kibanaPublisherJob") Job kibanaPublisherJob) {
        this.jobLauncher = jobLauncher;
        this.kibanaPublisherJob = kibanaPublisherJob;
    }

    @Scheduled(cron = "0 15 * * * *")
    public void runkibanaPublisherJob() {
        try {

            System.out.println("Running KibanaPublisherJob");
            JobParameters params = new JobParametersBuilder()
                    .addLong("runTimestamp", System.currentTimeMillis())
                    .toJobParameters();

            var result = jobLauncher.run(kibanaPublisherJob, params);

            System.out.println("Job Status: " + result.getStatus());
            System.out.println("Exit Code: " + result.getExitStatus());
            System.out.println("Finished KibanaPublisherJob");


        } catch (Exception e) {

            throw new RuntimeException(e);
         }
    }

}
