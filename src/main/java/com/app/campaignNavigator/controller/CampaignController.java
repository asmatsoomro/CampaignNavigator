package com.app.campaignNavigator.controller;

import com.app.campaignNavigator.service.CampaignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/campaign")
public class CampaignController {

    private final static Logger logger = LoggerFactory.getLogger(CampaignController.class);

    private CampaignService campaignService;

    @Autowired
    public CampaignController(CampaignService campaignService){
        this.campaignService = campaignService;
    }

    @GetMapping("/navigate")
    @ResponseBody
    public ResponseEntity<String> getAssociatedSegments(@RequestParam(name = "segments") String segments) {

        String[] desiredSegments = segments.split(" ");
        String associatedCampaign = campaignService.getAssociatedSegments(desiredSegments);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(associatedCampaign);
    }

}
