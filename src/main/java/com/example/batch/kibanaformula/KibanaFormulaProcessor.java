package com.example.batch.kibanaformula;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;

@StepScope
public class KibanaFormulaProcessor implements ItemProcessor<KibanaFormula, KibanaFormula> {

    @Override
    public KibanaFormula process(KibanaFormula item) throws Exception {
        return item;
    }
}
