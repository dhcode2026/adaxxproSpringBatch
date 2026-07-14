//package com.example.batch;
//
//import org.springframework.batch.core.*;
//import org.springframework.batch.core.launch.support.SimpleJobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class TransferJobRunner {
//
//    private final JobRepository jobRepository;
//    private final Job transferJob;
//    private final TransferProperties transferProperties;
//
//    public TransferJobRunner(JobRepository jobRepository,
//                             Job transferJob,
//                             TransferProperties transferProperties) {
//        this.jobRepository = jobRepository;
//        this.transferJob = transferJob;
//        this.transferProperties = transferProperties;
//    }
//
//    public void runAllTables() throws Exception {
//        SimpleJobLauncher launcher = new SimpleJobLauncher();
//        launcher.setJobRepository(jobRepository);
//        launcher.afterPropertiesSet();
//
//        int days = transferProperties.getDays();
//        List<TableConfig> tables = transferProperties.getTables();
//
//        System.out.println("Starting transfer for " + tables.size() + " table(s), days=" + days);
//
//        for (TableConfig table : tables) {
//            JobParameters params = new JobParametersBuilder()
//                    .addString("tableName", table.getName())
//                    .addString("dateColumn", table.getDateColumn())
//                    .addLong("days", (long) days)
//                    .addLong("run.id", System.currentTimeMillis()) // ensures unique JobInstance per run
//                    .toJobParameters();
//
//            try {
//                JobExecution execution = launcher.run(transferJob, params);
//
//                long read = execution.getStepExecutions().stream()
//                        .mapToLong(StepExecution::getReadCount).sum();
//                long written = execution.getStepExecutions().stream()
//                        .mapToLong(StepExecution::getWriteCount).sum();
//                long skipped = execution.getStepExecutions().stream()
//                        .mapToLong(StepExecution::getSkipCount).sum();
//
//                System.out.println("Table: " + table.getName()
//                        + " | Status: " + execution.getStatus()
//                        + " | Read: " + read
//                        + " | Written: " + written
//                        + " | Skipped: " + skipped);
//
//            } catch (Exception e) {
//                System.err.println("Failed for table " + table.getName() + ": " + e.getMessage());
//                // continue with the next table instead of aborting the whole run
//            }
//        }
//
//        System.out.println("Transfer run complete.");
//    }
//}
