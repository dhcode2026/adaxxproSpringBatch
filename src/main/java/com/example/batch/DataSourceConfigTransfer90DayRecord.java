//package com.example.batch;
//
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
//import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
//import org.springframework.transaction.PlatformTransactionManager;
//import javax.sql.DataSource;
//
//@Configuration
//public class DataSourceConfigTransfer90DayRecord {
//
////    / ---------- SOURCE DB ----------
//    @Primary
//    @Bean(name = "sourceDataSource")
//    public DataSource sourceDataSource() {
//        BasicDataSource ds = new BasicDataSource();
//        ds.setDriverClassName("org.postgresql.Driver");
//        ds.setUrl("jdbc:postgresql://source-host:5432/sourcedb");
//        ds.setUsername("source_user");
//        ds.setPassword("source_pass");
//        ds.setInitialSize(2);
//        ds.setMaxTotal(10);
//        return ds;
//    }
//
//    // ---------- TARGET DB (sample server) ----------
//    @Bean(name = "targetDataSource")
//    public DataSource targetDataSource() {
//        BasicDataSource ds = new BasicDataSource();
//        ds.setDriverClassName("org.postgresql.Driver");
//        ds.setUrl("jdbc:postgresql://sample-server:5432/targetdb");
//        ds.setUsername("target_user");
//        ds.setPassword("target_pass");
//        ds.setInitialSize(2);
//        ds.setMaxTotal(10);
//        return ds;
//    }
//
//    // ---------- BATCH METADATA DB ----------
//    @Bean(name = "batchDataSource")
//    public DataSource batchDataSource(@Qualifier("sourceDataSource") DataSource sourceDataSource) {
//        return sourceDataSource;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(
//            @Qualifier("batchDataSource") DataSource batchDataSource) {
//        return new DataSourceTransactionManager(batchDataSource);
//    }
//
//    @Bean
//    public JobRepository jobRepository(@Qualifier("batchDataSource") DataSource batchDataSource,
//                                       PlatformTransactionManager transactionManager) throws Exception {
//
//        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
//        factory.setDataSource(batchDataSource);
//        factory.setTransactionManager(transactionManager);
//        factory.setDatabaseType("postgres");
//        factory.afterPropertiesSet();
//
//        return factory.getObject();
//    }
//
//    @Bean
//    public Object initializeBatchSchema(@Qualifier("batchDataSource") DataSource batchDataSource) {
//        try {
//            ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//            populator.addScript(new ClassPathResource("org/springframework/batch/core/schema-postgresql.sql"));
//            populator.setContinueOnError(true);
//            DatabasePopulatorUtils.execute(populator, batchDataSource);
//        } catch (Exception e) {
//            System.err.println("Failed to initialize Spring Batch schema: " + e.getMessage());
//        }
//        return new Object();
//    }
//}
