package com.example.batch.kibanacountrycity;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.stereotype.Component;

@StepScope
public class KibanaCountryCityWriter implements ItemWriter<KibanaCountryCity> {

    private final JdbcBatchItemWriter<KibanaCountryCity> delegate;

    public KibanaCountryCityWriter(JdbcBatchItemWriter<KibanaCountryCity> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void write(Chunk<? extends KibanaCountryCity> chunk) throws Exception {

        delegate.write(chunk);
    }
}
