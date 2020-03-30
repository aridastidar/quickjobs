package com.intuit.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue
    @Column(name = "project_id")
    private Long id;

    @NotNull
    @Size(min = 2)
    private String projectName;

    @NotNull
    @Size(min = 5)
    private String description;

    @DecimalMin("0.1")
    private Double maxBudget;

    private Double minBid = Double.NaN;

    @JsonFormat(pattern = "MM-dd-yyyy")
    private Date lastApplyDate;

    private Long sellerId;

    @OneToMany(mappedBy = "project")
    private List<Bid> bids;

    public Project() {
    }

    public Project(@NotNull @Size(min = 2) String projectName, @NotNull @Size(min = 5) String description, @DecimalMin("1.0") Double maxBudget, Double minBid, Date lastApplyDate, Long sellerId) {
        this.projectName = projectName;
        this.description = description;
        this.maxBudget = maxBudget;
        this.minBid = minBid;
        this.lastApplyDate = lastApplyDate;
        this.sellerId = sellerId;
    }
}
