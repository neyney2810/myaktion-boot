package com.example.myaktionboot.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("SELECT d FROM Donation d where d.campaign.id = ?1")
    public List<Donation> findDonationsByCampaignId(Long id);
}
