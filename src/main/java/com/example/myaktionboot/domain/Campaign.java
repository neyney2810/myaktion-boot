package com.example.myaktionboot.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Campaign {

    @Id
    @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Size(min = 4, max = 30)
    private String name;

    @Min(1)
    private double donationMinimum;

    @Min(10)
    private double targetAmount;

    @Transient
    private double amountDonatedSoFar;

    @JsonIgnore
    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Donation> donations = new LinkedList<Donation>();

    public void addDonation(Donation donation) {
        donations.add(donation);
    }

    @Embedded
    @AttributeOverrides({ @AttributeOverride(name = "name", column = @Column(name = "account_name")) })
    private Account account = new Account();

    public double getAmountDonatedSoFar() {
        return amountDonatedSoFar;
    }

    public void setAmountDonatedSoFar(double amountDonatedSoFar) {
        this.amountDonatedSoFar = amountDonatedSoFar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDonationMinimum() {
        return donationMinimum;
    }

    public void setDonationMinimum(double donationMinimum) {
        this.donationMinimum = donationMinimum;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(double targetAmount) {
        this.targetAmount = targetAmount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    @Override
    public String toString() {
        return "Campaign [account=" + account + ", donationMinimum=" + donationMinimum + ", donations=" + donations
                + ", id=" + id + ", name=" + name + ", targetAmount=" + targetAmount + "]";
    }
}
