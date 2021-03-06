package com.app.campaignnavigator.config;

import com.app.campaignnavigator.entity.Campaign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Configuration
public class CampaignLoader {

    private static final Logger logger = LoggerFactory.getLogger(CampaignLoader.class);

    @Value("${file}")
    String filePath;

    @Bean(name = "campaign")
    public Set<Campaign> getCampaign() {

        Set<Campaign> campaignSet = new LinkedHashSet<>();


        try {
            File file=new File(filePath);
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            StringBuffer sb=new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                logger.info(line);
                String[] campaignTokens = line.split(" ");
                Campaign campaign = new Campaign();
                campaign.setName(campaignTokens[0]);
                String[] segments = new String[campaignTokens.length - 1];
                int segmentIndex = 0;
                for (int i = 1; i < campaignTokens.length; i++) {
                    segments[segmentIndex] = campaignTokens[i];
                    segmentIndex++;
                }

                campaign.setSegments(segments);
                campaignSet.add(campaign);
            }
            fr.close();
        } catch (IOException e) {
           logger.error(e.getLocalizedMessage());
        }
        return campaignSet;

    }
}