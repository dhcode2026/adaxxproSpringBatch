package com.example.batch.kibanapublisher;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@StepScope
public class KibanaPublisherProcessor implements ItemProcessor<PublishInventory, PublishInventory> {


    @Override
    public PublishInventory process(PublishInventory item) throws Exception {
        return item;
    }


}
