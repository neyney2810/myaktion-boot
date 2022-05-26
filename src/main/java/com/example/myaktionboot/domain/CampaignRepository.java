package com.example.myaktionboot.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {

    @Query("SELECT sum(d.amount) from Donation d where d.campaign.id = ?1")
    public double getAmountDonatedSoFar(Long id);
}
