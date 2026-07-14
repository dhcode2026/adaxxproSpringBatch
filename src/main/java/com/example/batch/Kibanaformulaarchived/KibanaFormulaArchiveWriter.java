package com.example.batch.Kibanaformulaarchived;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

@StepScope
public class KibanaFormulaArchiveWriter implements ItemWriter<KibanaFormulaArchive> {


    private final JdbcBatchItemWriter<KibanaFormulaArchive> delegate;

    public KibanaFormulaArchiveWriter(JdbcBatchItemWriter<KibanaFormulaArchive> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void write(Chunk<? extends KibanaFormulaArchive> chunk) throws Exception {

        System.out.println("KibanaFormulaArchiveWriter is writing");
        delegate.write(chunk);
        System.out.println("✓ Finished writing chunk of size: " + chunk.getItems().size());
    }
}
