package com.example.myaktionboot;

import com.example.myaktionboot.domain.Account;
import com.example.myaktionboot.domain.Campaign;
import com.example.myaktionboot.domain.Donation;
import com.example.myaktionboot.domain.Status;
import com.example.myaktionboot.services.CampaignService;
import com.example.myaktionboot.services.DonationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class MyaktionBootApplication {

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private DonationService donationService;

    private Logger log = LoggerFactory.getLogger(MyaktionBootApplication.class);

    @Bean
    CommandLineRunner initLoggerBean() {
        return args -> {

        //Exercise 1
            log.info("Adding Sample data to DB");
            Account account1 = new Account();
            account1.setName("Jogi Löw");
            account1.setNameOfBank("KSK Freiburg");
            account1.setIban("DE4112312312312345");
            System.out.println(account1);

            //Create a new campaign object
            Campaign campaign1 = new Campaign();
            campaign1.setName("Trikots A-Jugend");
            campaign1.setTargetAmount(1000d);
            campaign1.setDonationMinimum(1d);
            campaign1.setAccount(account1);

            //Create Donation object and save it to the db using the respective service class
            Account account2 = new Account();
            account2.setName("Hansi Flick");
            account2.setNameOfBank("KSK München");
            account2.setIban("DE4112444312312345");

            Donation donation1 = new Donation();
            donation1.setAccount(account2);
            donation1.setReceiptRequested(false);
            donation1.setDonorName("Hansi Flick");
            donation1.setAmount(100d);
            donation1.setStatus(Status.IN_PROCESS);
            donation1.setCampaign(campaign1);

            campaign1.addDonation(donation1);
            log.debug("Add campaign to DB");
            // Save the campaign object to the database
            Campaign campaign = campaignService.addCampaign(campaign1);

            //Create another Donation object
            Account account3 = new Account();
            account3.setName("Berti Vogts");
            account3.setNameOfBank("VoBa Gladbach");
            account3.setIban("DE4112444312314442");

            Donation donation2 = new Donation();
            donation2.setAccount(account3);
            donation2.setReceiptRequested(true);
            donation2.setDonorName("Berti Vogts");
            donation2.setAmount(200d);
            donation2.setStatus(Status.IN_PROCESS);

            log.debug("Add donation of campaign to DB");
            donationService.addDonation(donation2, campaign.getId());

            // Read data from db and print to screen
            List<Campaign> campaigns = campaignService.getCampaigns();
            log.debug("Read all campaigns again");
            log.trace(campaigns.toString());

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(MyaktionBootApplication.class, args);
    }

}
