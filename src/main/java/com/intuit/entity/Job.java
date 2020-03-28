package com.intuit.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name="job")
public class Job{
    private Long id;
    private String title;
    private String description;
    private int budget;
    private LocalDateTime lastApplyDate;
}