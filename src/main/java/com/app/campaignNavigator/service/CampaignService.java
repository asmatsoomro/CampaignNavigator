package com.app.campaignNavigator.service;

import com.app.campaignNavigator.entity.Campaign;
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
    private List<Campaign> campaignSet;

    public String getAssociatedSegments(String[] desiredSegments){
        desiredSegments = ignoreDuplicateElements(desiredSegments);
        int max =0;
        String largestAssociatedSegment = NO_CAMPAIGN;
        Iterator<Campaign> itr = campaignSet.iterator();
        while (itr.hasNext()){
            Campaign campaign = itr.next();
            logger.info("campaign:: {}", campaign.getName());
            String[] campaignSegments = campaign.getSegments();
            int identicalElements = 0;
            for (int i=0; i<campaignSegments.length; i++){
                for (int j=0; j<desiredSegments.length; j++){

                    if (campaignSegments[i].equals(desiredSegments[j])){
                        identicalElements++;
                    }

                }
            }
            if (identicalElements >= max && identicalElements!=0){
                max = identicalElements;
                largestAssociatedSegment = campaign.getName();
            }
            logger.info("Number of identical elements for campaign {} == {}, maximum = {}", campaign.getName(), identicalElements, max);
        }

        logger.info("max {}", max);
        return largestAssociatedSegment;
    }

    // duplicate elements to be completely ignored, i.e. {"3", "5", "5"} -> {"3"}
    public String[] ignoreDuplicateElements(String[] arr){
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        for (int i=0; i<arr.length;i++){
            for (int j=i+1;j<arr.length;j++){
                if (arr[i].equals(arr[j])){
                    arr[i] = null;
                    arr[j] = null;

                }
            }
        }


        for (int i=0; i<arr.length; i++){
            if (arr[i]!=null){
                linkedHashSet.add(arr[i]);
            }
        }
        return linkedHashSet.toArray(new String[]{});
    }


}
