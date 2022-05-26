package com.example.myaktionboot.controller;

import com.example.myaktionboot.domain.Campaign;
import com.example.myaktionboot.domain.CampaignRepository;
import com.example.myaktionboot.domain.Donation;
import com.example.myaktionboot.exception.CampaignNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CampaignController {
    private final CampaignRepository campaignRepository;

    public CampaignController(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    //Aggregate root
    //tag::get-aggregate-root[]
    @GetMapping("/campaigns")
    public List<Campaign> all() {
        List<Campaign> campaigns = campaignRepository.findAll();
        campaigns.forEach(campaign -> {
            campaign.setAmountDonatedSoFar(campaignRepository.getAmountDonatedSoFar(campaign.getId()));
        });
        return campaigns;
    }
    // end::get-aggregate-root[]

    @PostMapping("/campaigns")
    public Campaign newCampaign(@RequestBody Campaign newCampaign) {
        return campaignRepository.save(newCampaign);
    }

    @GetMapping("/campaigns/{id}")

    public Campaign one(@PathVariable Long id) {
        //Check if there is a campaign
        Optional<Campaign> foundCampaign = campaignRepository.findById(id);
        if (foundCampaign.isEmpty()) {
            throw new CampaignNotFoundException("Campaign not found");
        }

        Campaign resultCampaign = foundCampaign.get();
        resultCampaign.setAmountDonatedSoFar(campaignRepository.getAmountDonatedSoFar(resultCampaign.getId()));
        return resultCampaign;
    }

    @PutMapping("/campaigns/{id}")
    public Campaign replaceCampaign(@RequestBody Campaign replaceCampaign, @PathVariable Long id) {
        return campaignRepository.findById(id)
                .map(campaign -> {
                    campaign.setName(replaceCampaign.getName());
                    campaign.setAccount(replaceCampaign.getAccount());
                    campaign.setDonations(replaceCampaign.getDonations());
                    campaign.setDonationMinimum(replaceCampaign.getDonationMinimum());
                    campaign.setTargetAmount(replaceCampaign.getTargetAmount());
                    return campaignRepository.save(campaign);
                })
                .orElseGet(() -> {
                   replaceCampaign.setId(id);
                   return campaignRepository.save(replaceCampaign);
                });
    }

    @DeleteMapping("/campaigns/{id}")
    public void deleteCampaign(@PathVariable Long id) {
        campaignRepository.deleteById(id);
    }

}

