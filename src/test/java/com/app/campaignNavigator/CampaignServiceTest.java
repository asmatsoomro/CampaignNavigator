package com.app.campaignNavigator;

import com.app.campaignNavigator.service.CampaignService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class CampaignServiceTest {

    @Autowired
    CampaignService campaignService;

    @Test
    @DisplayName("Verify that desired segments 3,4,5,10,2 and 200 belong to campaign_a")
    public void verifyThat_desiredSegmentsContaining3_4And10_belongToCampaignA(){

        String[] desiredSegments = new String[]{"3", "4","5","10", "2", "200"};
        String result =campaignService.getAssociatedSegments(desiredSegments);
        Assertions.assertEquals("campaign_a", result);
    }

    @Test
    @DisplayName("Verify that desired segments 3 belong to campaign_a")
    public void verifyThat_desiredSegmentsContaining3belongsToCampaignA(){

        String[] desiredSegments = new String[]{"3", "4","5","10", "2", "200"};
        String result =campaignService.getAssociatedSegments(desiredSegments);
        Assertions.assertEquals("campaign_a", result);
    }

    @Test
    @DisplayName("Verify that desired segments 9000,29833 and 65000  belong to no campaign")
    public void verifyThat_desiredSegmentsContaining9000_29833And65000belongToCampaignA(){

        String[] desiredSegments = new String[]{"9000", "29833","65000"};
        String result =campaignService.getAssociatedSegments(desiredSegments);
        Assertions.assertEquals("no campaign", result);
    }

    @Test
    @DisplayName("Verify that desired segments 1024,15,200,21,9,14 and 15  belong to campaign c")
    public void verifyThat_desiredSegmentsContaining1024_15_200_21_9_14And15_belongToCampaignC(){

        String[] desiredSegments = new String[]{ "1024", "15", "200", "21", "9", "14", "15"};
        String result =campaignService.getAssociatedSegments(desiredSegments);
        Assertions.assertEquals("campaign_c", result);
    }

    @Test
    @DisplayName("Verify that empty array belongs to no campaign")
    public void verifyThat_emptyArray_belongsToNoCampaign(){

        String[] desiredSegments = new String[]{ };
        String result =campaignService.getAssociatedSegments(desiredSegments);
        Assertions.assertEquals("no campaign", result);
    }
}
