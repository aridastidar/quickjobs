package com.intuit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class Buyer {

    @Id
    @GeneratedValue
    private Long  id;

    @JsonInclude
    private String buyerName;

    public Buyer() {
        super();
    }

    public Buyer(String buyerName) {
        super();
        this.buyerName = buyerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Buyer{");
        sb.append("id=").append(id);
        sb.append(", buyerName='").append(buyerName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
