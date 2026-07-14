package com.example.batch.KibanaExchangeReport;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;


@StepScope
public class KibanaExchangeReportProcessor implements ItemProcessor<KibanaExchangeReport, KibanaExchangeReport> {
    @Override
    public KibanaExchangeReport process(KibanaExchangeReport item) throws Exception {
        return item;
    }
}
