package com.example.myaktionboot.controller;

import com.example.myaktionboot.domain.Campaign;
import com.example.myaktionboot.domain.CampaignRepository;
import com.example.myaktionboot.domain.Donation;
import com.example.myaktionboot.domain.DonationRepository;
import com.example.myaktionboot.exception.CampaignNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DonationController {
    private final CampaignRepository campaignRepository;
    private final DonationRepository donationRepository;

    public DonationController(CampaignRepository campaignRepository, DonationRepository donationRepository) {
        this.campaignRepository = campaignRepository;
        this.donationRepository = donationRepository;
    }

    @PostMapping("/campaigns/{id}/donations")
    public Donation addDonationToCampaign(@RequestBody Donation donation, @PathVariable Long id) {
        //Check if there is a campaign
        Optional<Campaign> foundCampaign = campaignRepository.findById(id);
        if (foundCampaign.isEmpty()) {
            throw new CampaignNotFoundException("Campaign not found!");
        }

        Campaign campaign = foundCampaign.get();
        campaign.addDonation(donation);
        donation.setCampaign(campaign);
        Donation savedDonation = donationRepository.save(donation);

        return savedDonation;
    }

    @GetMapping("/campaigns/{id}/donations")
    public List<Donation> getDonationsOfCampaign(@PathVariable Long id) {
        return donationRepository.findDonationsByCampaignId(id);
    }
}
