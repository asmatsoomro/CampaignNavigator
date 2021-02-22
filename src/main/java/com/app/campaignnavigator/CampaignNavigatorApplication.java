package com.app.campaignnavigator;

import com.app.campaignnavigator.service.CampaignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CampaignNavigatorApplication {

    private final static Logger logger = LoggerFactory.getLogger(CampaignNavigatorApplication.class);

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(CampaignNavigatorApplication.class, args);

        CampaignService service = context.getBean(CampaignService.class);
        String[] arr = args;

        logger.info(service.getAssociatedSegments(arr));
    }

}
