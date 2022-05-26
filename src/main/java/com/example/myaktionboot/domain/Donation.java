package com.example.myaktionboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Donation {

    @Id @GeneratedValue
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Min(1)
    private double amount;

    @NotNull
    private boolean receiptRequested;

    @Size(min = 5, max = 40)
    private String donorName;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status = com.example.myaktionboot.domain.Status.IN_PROCESS;

    @JsonIgnore
    @ManyToOne
    private Campaign campaign;

    @Embedded
    private Account account;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Basic
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Basic
    public boolean isReceiptRequested() {
        return receiptRequested;
    }

    public void setReceiptRequested(boolean receiptRequested) {
        this.receiptRequested = receiptRequested;
    }

    @Basic
    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    @Basic
    public com.example.myaktionboot.domain.Status getStatus() {
        return status;
    }

    public void setStatus(com.example.myaktionboot.domain.Status status) {
        status = status;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Donation [account=" + account + ", amount=" + amount + ", donorName=" + donorName + ", id=" + id
                + ", receiptRequested=" + receiptRequested + ", status=" + status + "]";
    }
}
