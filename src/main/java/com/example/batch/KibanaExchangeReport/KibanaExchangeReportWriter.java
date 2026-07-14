package com.example.batch.KibanaExchangeReport;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

@StepScope
public class KibanaExchangeReportWriter implements ItemWriter<KibanaExchangeReport> {

    public KibanaExchangeReportWriter(JdbcBatchItemWriter<KibanaExchangeReport> delegate) {
        this.delegate = delegate;
    }

    private final JdbcBatchItemWriter<KibanaExchangeReport> delegate;

    @Override
    public void write(Chunk<? extends KibanaExchangeReport> chunk) throws Exception {

        delegate.write(chunk);
    }
}
