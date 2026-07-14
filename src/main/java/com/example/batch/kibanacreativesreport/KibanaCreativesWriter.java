package com.example.batch.kibanacreativesreport;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

@StepScope
public class KibanaCreativesWriter implements ItemWriter<KibanaCreativesReport> {

    private final JdbcBatchItemWriter<KibanaCreativesReport> delegate;

    public KibanaCreativesWriter(JdbcBatchItemWriter<KibanaCreativesReport> delegate) {
        this.delegate = delegate;
    }


    @Override
    public void write(Chunk<? extends KibanaCreativesReport> chunk) throws Exception {

        delegate.write(chunk);
    }
}
