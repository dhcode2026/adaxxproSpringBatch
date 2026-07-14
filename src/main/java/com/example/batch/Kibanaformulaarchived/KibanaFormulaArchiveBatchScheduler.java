package com.example.batch.Kibanaformulaarchived;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KibanaFormulaArchiveBatchScheduler {

    private JobLauncher jobLauncher;
    private Job KibanaFormulaArchivedJob;

    public KibanaFormulaArchiveBatchScheduler(JobLauncher jobLauncher, @Qualifier("KibanaFormulaArchivedJob") Job kibanaFormulaArchivedJob) {
        this.jobLauncher = jobLauncher;
        this.KibanaFormulaArchivedJob = kibanaFormulaArchivedJob;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void runKibanaFormulaJob() {
        try {
            System.out.println("Running KibanaFormulaArchivedJob");
            JobParameters params = new JobParametersBuilder()
                    .addLong("runTimestamp", System.currentTimeMillis())
                    .toJobParameters();

            var result = jobLauncher.run(KibanaFormulaArchivedJob, params);

            System.out.println("Job Status: " + result.getStatus());
            System.out.println("Exit Code: " + result.getExitStatus());
            System.out.println("✓ Finished KibanaFormulaArchivedJob");
            System.out.println("========================================\n");
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
