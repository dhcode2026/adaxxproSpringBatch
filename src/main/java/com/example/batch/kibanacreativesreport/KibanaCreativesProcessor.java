package com.example.batch.kibanacreativesreport;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
@StepScope
public class KibanaCreativesProcessor implements ItemProcessor<KibanaCreativesReport, KibanaCreativesReport> {


    @Override
    public KibanaCreativesReport process(KibanaCreativesReport item) throws Exception {
        return item;
    }
}
