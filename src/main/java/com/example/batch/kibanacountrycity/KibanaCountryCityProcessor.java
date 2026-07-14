package com.example.batch.kibanacountrycity;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@StepScope
public class KibanaCountryCityProcessor implements ItemProcessor<KibanaCountryCity, KibanaCountryCity> {

    @Override
    public KibanaCountryCity process(KibanaCountryCity item) throws Exception {
        return item;
    }
}
