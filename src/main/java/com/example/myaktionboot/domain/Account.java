package com.example.myaktionboot.domain;

import com.sun.istack.NotNull;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Embeddable
public class Account {

    @Pattern(regexp = "\\b[A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){4}(?!(?:[ ]?[0-9]){3})(?:[ ]?[0-9]{1,2})?\\b")
    private String iban;

    @Size(min = 5, max = 60)
    private String name;

    @Size(min = 4, max = 40)
    private String nameOfBank;

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOfBank() {
        return nameOfBank;
    }

    public void setNameOfBank(String nameOfBank) {
        this.nameOfBank = nameOfBank;
    }

    @Override
    public String toString() {
        return "Account [iban=" + iban + ", name=" + name + ", nameOfBank=" + nameOfBank + "]";
    }
}
