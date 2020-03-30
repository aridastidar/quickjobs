package com.intuit.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.intuit.entity.Bid;
import com.intuit.entity.Buyer;
import com.intuit.entity.Project;
import com.intuit.entity.Seller;
import com.intuit.service.BidService;
import com.intuit.service.BuyerService;
import com.intuit.service.ProjectService;
import com.intuit.service.SellerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class QuickjobsController{

    private SellerService sellerService;
    private BuyerService buyerService;
    private ProjectService projectService;
    private BidService bidService;

    @Autowired
    public QuickjobsController(SellerService sellerService, BuyerService buyerService, ProjectService projectService, BidService bidService) {
        this.sellerService = sellerService;
        this.buyerService = buyerService;
        this.projectService = projectService;
        this.bidService = bidService;
    }

    @GetMapping("/sellers")
    public List<Seller> getSellers() {
        return sellerService.getAllSellers();
    }

    @GetMapping("/sellers/{id}")
    public ResponseEntity<Seller> getSeller(@PathVariable("id") Long id) {
        Optional<Seller> seller = sellerService.getSellerById(id);
        if (!seller.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(seller.get(), HttpStatus.OK);
        }
    }

    @PostMapping("/sellers")
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) {
        sellerService.saveSeller(seller);
        return new ResponseEntity<>(seller,HttpStatus.CREATED);
    }

    @GetMapping("/buyers")
    public List<Buyer> getBuyers() {
        return buyerService.getAllBuyers();
    }

    @GetMapping("/buyers/{id}")
    public ResponseEntity<Buyer> getBuyer(@PathVariable("id") Long id) {
        Optional<Buyer> buyer = buyerService.getBuyerById(id);
        if (!buyer.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(buyer.get(), HttpStatus.OK);
        }
    }

    @PostMapping("/buyers")
    public ResponseEntity<Buyer> createBuyer(@RequestBody Buyer buyer) {
        buyerService.saveBuyer(buyer);
        return new ResponseEntity<>(buyer,HttpStatus.CREATED);
    }

    @GetMapping("/projects")
    public List<Project> getProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/projects/{id}")
    public ResponseEntity<Project> getProject(@PathVariable("id") Long id) {
        Optional<Project> project = projectService.getProjectById(id);
        if (!project.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(project.get(), HttpStatus.OK);
        }
    }

    @PostMapping("/projects")
    public ResponseEntity<String> createProject(@Valid @RequestBody Project project) {
        projectService.saveProject(project);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/bids")
    public List<Bid> getBids() {
        return bidService.getAllBids();
    }

    @GetMapping("/bids/{id}")
    public ResponseEntity<Bid> getBid(@PathVariable("id") Long id) {
        Optional<Bid> bid = bidService.getBidById(id);
        if (!bid.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(bid.get(), HttpStatus.OK);
        }
    }

    @PostMapping("/projects/{projectId}/bids")
    public ResponseEntity<String> createBid(@PathVariable("projectId") Long projectId, @Valid @RequestBody Bid bid) {
        bidService.saveBid(projectId, bid);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
}