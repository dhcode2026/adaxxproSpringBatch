package com.example.batch.kibanacampaigndomain.domainquery2;

import com.example.batch.kibanacampaigndomain.KibanaCampaignDomain;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;

@StepScope
public class KibanaCampaignDomain2Processor implements ItemProcessor<KibanaCampaignDomain, KibanaCampaignDomain> {


    @Override
    public KibanaCampaignDomain process(KibanaCampaignDomain item) throws Exception {
        return item;
    }
}
