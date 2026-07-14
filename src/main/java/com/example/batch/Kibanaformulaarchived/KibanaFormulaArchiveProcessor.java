package com.example.batch.Kibanaformulaarchived;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
@StepScope
public class KibanaFormulaArchiveProcessor implements ItemProcessor<KibanaFormulaArchive, KibanaFormulaArchive> {
    @Override
    public KibanaFormulaArchive process(KibanaFormulaArchive item) throws Exception {
        return item;
    }
}
