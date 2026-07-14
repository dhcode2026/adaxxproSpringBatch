package com.example.batch.kibanaformula;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

@StepScope
public class KibanaFormulaWriter implements ItemWriter<KibanaFormula> {

    private final JdbcBatchItemWriter<KibanaFormula> delegate;

    public KibanaFormulaWriter(JdbcBatchItemWriter<KibanaFormula> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void write(Chunk<? extends KibanaFormula> chunk) throws Exception {

        delegate.write(chunk);
    }
}
