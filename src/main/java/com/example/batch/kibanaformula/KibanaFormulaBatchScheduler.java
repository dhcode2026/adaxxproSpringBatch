package com.example.batch.kibanaformula;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KibanaFormulaBatchScheduler {

    private JobLauncher jobLauncher;
    private Job KibanaFormulaJob;

    public KibanaFormulaBatchScheduler(JobLauncher jobLauncher, @Qualifier("KibanaFormulaJob") Job kibanaFormulaJob) {
        this.jobLauncher = jobLauncher;
        this.KibanaFormulaJob = kibanaFormulaJob;
    }

    @Scheduled(cron = "0 5/15 * * * *")
    public void runKibanaFormulaJob() {
        try {
            System.out.println("Running KibanaFormulaJob");
            JobParameters params = new JobParametersBuilder()
                    .addLong("runTimestamp", System.currentTimeMillis())
                    .toJobParameters();

            var result = jobLauncher.run(KibanaFormulaJob, params);

            System.out.println("Job Status: " + result.getStatus());
            System.out.println("Exit Code: " + result.getExitStatus());
            System.out.println("✓ Finished KibanaFormulaJob");
            System.out.println("========================================\n");
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
