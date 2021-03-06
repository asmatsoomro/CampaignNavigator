package com.app.campaignnavigator.service;

import com.app.campaignnavigator.entity.Campaign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CampaignService {


    private final static Logger logger = LoggerFactory.getLogger(CampaignService.class);

    private static final String NO_CAMPAIGN = "no campaign";

    @Autowired
    @Qualifier("campaign")
    private Set<Campaign> campaignSet;

    Map<String, Integer> segementLoad = new HashMap<>();


    public String getAssociatedSegments(String[] desiredSegments) {
        List<String> maxCampaigns = new ArrayList<>();
        desiredSegments = ignoreDuplicateElements(desiredSegments);
        int max = 0;
        String largestAssociatedSegment = NO_CAMPAIGN;
        Iterator<Campaign> itr = campaignSet.iterator();
        while (itr.hasNext()) {
            Campaign campaign = itr.next();
            logger.info("campaign:: {}", campaign.getName());
            String[] campaignSegments = campaign.getSegments();
            int identicalElements = 0;
            for (int i = 0; i < campaignSegments.length; i++) {
                for (int j = 0; j < desiredSegments.length; j++) {

                    if (campaignSegments[i].equals(desiredSegments[j])) {
                        identicalElements++;
                    }

                }
            }
            if (identicalElements >= max && identicalElements != 0) {
                maxCampaigns.add(campaign.getName());
                max = identicalElements;
                largestAssociatedSegment = campaign.getName();
            }

            logger.info("Number of identical elements for campaign {} == {}, maximum = {}", campaign.getName(), identicalElements, max);
        }


        logger.info("max {}", max);
        if (!largestAssociatedSegment.equals(NO_CAMPAIGN)) {
            largestAssociatedSegment = balanceLoadBetweenCampaigns(maxCampaigns, largestAssociatedSegment);
        }
        return largestAssociatedSegment;
    }

    private String balanceLoadBetweenCampaigns(List<String> maxCampaigns, String largestAssociatedSegment) {
        int min = segementLoad.get(maxCampaigns.get(0)) != null ? segementLoad.get(maxCampaigns.get(0)) : 0;
        for (int i = 0; i < maxCampaigns.size(); i++) {
            Integer loadVal = segementLoad.get(maxCampaigns.get(i));
            if (loadVal == null) {
                loadVal = 0;
            }
            if (loadVal <= min) {
                min = loadVal;
                largestAssociatedSegment = maxCampaigns.get(i);
            }


        }
        int count = segementLoad.containsKey(largestAssociatedSegment) ? segementLoad.get(largestAssociatedSegment) : 0;
        segementLoad.put(largestAssociatedSegment, ++count);
        return largestAssociatedSegment;
    }

    // duplicate elements to be completely ignored, i.e. {"3", "5", "5"} -> {"3"}
    private String[] ignoreDuplicateElements(String[] arr) {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i].equals(arr[j])) {
                    arr[i] = null;
                    arr[j] = null;

                }
            }
        }
        for (String strTemp : arr){
            if (strTemp != null) {
                linkedHashSet.add(strTemp);
            }
        }

        return linkedHashSet.toArray(new String[]{});
    }


}
