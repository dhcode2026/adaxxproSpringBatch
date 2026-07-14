package com.example.batch.kibanapublisher;


import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

@StepScope
public class KibanaPublisherWriter implements ItemWriter<PublishInventory> {

    private final JdbcBatchItemWriter<PublishInventory> delegate;

    public KibanaPublisherWriter(JdbcBatchItemWriter<PublishInventory> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void write(Chunk<? extends PublishInventory> chunk) throws Exception {
        delegate.write(chunk);
    }
}
