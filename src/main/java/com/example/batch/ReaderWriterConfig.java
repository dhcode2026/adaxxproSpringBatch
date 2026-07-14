//package com.example.batch;
//
//
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.database.JdbcCursorItemReader;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.ColumnMapRowMapper;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.namedparam.SqlParameterSource;
//import javax.sql.DataSource;
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Configuration
//public class ReaderWriterConfig {
//
//    @Bean
//    @StepScope
//    public JdbcCursorItemReader<Map<String, Object>> genericReader(
//            @Qualifier("sourceDataSource") DataSource sourceDataSource,
//            @Value("#{jobParameters['tableName']}") String tableName,
//            @Value("#{jobParameters['dateColumn']}") String dateColumn,
//            @Value("#{jobParameters['days']}") Long days) {
//
//        String sql = "SELECT * FROM " + tableName + " WHERE " + dateColumn + " >= ?";
//
//        JdbcCursorItemReader<Map<String, Object>> reader = new JdbcCursorItemReader<>();
//        reader.setDataSource(sourceDataSource);
//        reader.setSql(sql);
//        reader.setPreparedStatementSetter((ps) ->
//                ps.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now().minusDays(days))));
//        reader.setRowMapper(new ColumnMapRowMapper());
//        reader.setName("genericReader-" + tableName);
//        return reader;
//    }
//
//    @Bean
//    @StepScope
//    public ItemWriter<Map<String, Object>> genericWriter(
//            @Qualifier("targetDataSource") DataSource targetDataSource,
//            @Value("#{jobParameters['tableName']}") String tableName) {
//
//        return items -> {
//            List<? extends Map<String, Object>> rows = items.getItems();
//            if (rows.isEmpty()) return;
//
//            List<String> columns = new ArrayList<>(rows.get(0).keySet());
//            String colList = String.join(", ", columns);
//            String paramList = columns.stream().map(c -> ":" + c).collect(Collectors.joining(", "));
//            String sql = "INSERT INTO " + tableName + " (" + colList + ") VALUES (" + paramList + ")";
//
//            NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(targetDataSource);
//            SqlParameterSource[] batchParams = rows.stream()
//                    .map(MapSqlParameterSource::new)
//                    .toArray(SqlParameterSource[]::new);
//
//            jdbcTemplate.batchUpdate(sql, batchParams);
//        };
//    }
//}
