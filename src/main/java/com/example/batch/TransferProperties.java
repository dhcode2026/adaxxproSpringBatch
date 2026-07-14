//package com.example.batch;
//
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Component;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//@Component
//public class TransferProperties {
//
//
//    private final Properties props = new Properties();
//    private int days;
//    private List<TableConfig> tables;
//
//    public TransferProperties() {
//        load("transfer.properties");
//    }
//
//    private void load(String classpathFile) {
//        try (InputStream in = new ClassPathResource(classpathFile).getInputStream()) {
//            props.load(in);
//        } catch (IOException e) {
//            throw new IllegalStateException("Failed to load " + classpathFile, e);
//        }
//
//        this.days = Integer.parseInt(props.getProperty("transfer.days", "90"));
//
//        String tableKeysRaw = props.getProperty("transfer.tables", "");
//        if (tableKeysRaw.trim().isEmpty()) {
//            throw new IllegalStateException("transfer.tables must list at least one table key");
//        }
//
//        String[] tableKeys = tableKeysRaw.split(",");
//        this.tables = new ArrayList<>();
//
//        for (String key : tableKeys) {
//            key = key.trim();
//            String name = props.getProperty("table." + key + ".name");
//            String dateColumn = props.getProperty("table." + key + ".dateColumn");
//
//            if (name == null || dateColumn == null) {
//                throw new IllegalStateException(
//                        "Missing config for table key '" + key + "': " +
//                                "expected table." + key + ".name and table." + key + ".dateColumn");
//            }
//            tables.add(new TableConfig(name.trim(), dateColumn.trim()));
//        }
//    }
//
//    public int getDays() { return days; }
//    public List<TableConfig> getTables() { return tables; }
//}
