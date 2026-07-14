package com.example.batch.kibanacampaigndomain;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;

@StepScope
public class KibanaCampaignDomainProcessor implements ItemProcessor<KibanaCampaignDomain, KibanaCampaignDomain> {



    @Override
    public KibanaCampaignDomain process(KibanaCampaignDomain item) throws Exception {
        return item;
    }
}
