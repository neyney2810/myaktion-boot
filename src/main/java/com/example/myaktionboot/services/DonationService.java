package com.example.myaktionboot.services;

import com.example.myaktionboot.domain.Campaign;
import com.example.myaktionboot.domain.CampaignRepository;
import com.example.myaktionboot.domain.Donation;
import com.example.myaktionboot.domain.DonationRepository;
import com.example.myaktionboot.exception.CampaignNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private CampaignRepository campaignRepository;

    private Logger log = LoggerFactory.getLogger(CampaignService.class);

    // Add donation to a given campaign id
    public Donation addDonation(Donation donation, Long campaignId) {
        //Check if there is a campaign
        Optional<Campaign> foundCampaign = campaignRepository.findById(campaignId);
        if (foundCampaign.isEmpty()) {
            log.error("Campaign not found!");
            throw new CampaignNotFoundException("Campaign not found!");
        }

        Campaign campaign = foundCampaign.get();
        donation.setCampaign(campaign);
        Donation savedDonation = donationRepository.save(donation);

        log.trace("Saved Donation: "+savedDonation.toString());
        return savedDonation;
    }
}
