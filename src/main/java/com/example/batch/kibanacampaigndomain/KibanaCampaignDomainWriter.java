package com.example.batch.kibanacampaigndomain;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;

@StepScope
public class KibanaCampaignDomainWriter implements ItemWriter<KibanaCampaignDomain> {

    private final JdbcBatchItemWriter<KibanaCampaignDomain> delegate;

    public KibanaCampaignDomainWriter(JdbcBatchItemWriter<KibanaCampaignDomain> delegate) {
        this.delegate = delegate;
    }

    @Override
    public void write(Chunk<? extends KibanaCampaignDomain> chunk) throws Exception {

        delegate.write(chunk);
    }
}
