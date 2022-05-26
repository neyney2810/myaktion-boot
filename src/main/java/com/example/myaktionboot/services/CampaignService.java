package com.example.myaktionboot.services;

import com.example.myaktionboot.domain.Campaign;
import com.example.myaktionboot.domain.CampaignRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    private Logger log = LoggerFactory.getLogger(CampaignService.class);

    public Campaign addCampaign(Campaign campaign) {
        Campaign savedCampaign = campaignRepository.save(campaign);
        log.trace("Saved campaign: "+ savedCampaign.toString());
        return savedCampaign;
    }

    public List<Campaign> getCampaigns() {
        List<Campaign> savedCampaigns = campaignRepository.findAll();
        log.trace("Read campaigns: "+ savedCampaigns.toString());
        return savedCampaigns;
    }
}
