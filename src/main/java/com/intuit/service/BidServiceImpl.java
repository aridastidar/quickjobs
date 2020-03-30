package com.intuit.service;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.intuit.entity.Bid;
import com.intuit.entity.Project;
import com.intuit.repository.BidRepository;
import com.intuit.repository.ProjectRepository;
import com.intuit.util.QuickjobsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


@Service
public class BidServiceImpl implements BidService{

    BidRepository bidRepository;
    ProjectRepository projectRepository;

    @Autowired
    public BidServiceImpl(BidRepository bidRepository, ProjectRepository projectRepository) {
        this.bidRepository = bidRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<Bid> getBidById(Long id) {
        Optional<Bid> bid = bidRepository.findById(id);
        return bid;
    }

    @Override
    public List<Bid> getAllBids() {
        return bidRepository.findAll();
    }

    @Override
    public void saveBid(Long projectId, @RequestBody Bid bid) {
        //Check if the project is present
        if (projectRepository.findById(projectId).isPresent()) {

            Project project = projectRepository.findById(projectId).get();

            Date lastApplyDate = project.getLastApplyDate();

            if (lastApplyDate.after(new Date())) {
                if (bidRepository.findByBuyerId(bid.getBuyerId()) == null) {
                    if (Double.isNaN(project.getMinBid())){
                        saveBid(projectId, bid, project);
                    }else if(bid.getBid() < project.getMinBid()){
                        saveBid(projectId, bid, project);
                    }else {
                        throw new QuickjobsException("Bid higher than current bid of: " + project.getMinBid());
                    }
                }else{
                    Bid savedBid = bidRepository.findByBuyerId(bid.getBuyerId());
                    if(bid.getBid() < project.getMinBid()){
                        savedBid.setBid(bid.getBid());
                        bidRepository.save(savedBid);
                        calculateMinimumBid(project);
                    }else {
                        throw new QuickjobsException("Bid higher than the current minimum bid: " + project.getMinBid());
                    }

                }
            } else {
                throw new QuickjobsException("Deadline has already been passed for projectId: " + projectId);
            }
        } else {
            throw new QuickjobsException("Project id is invalid. Please enter correct project id.");
        }
    }

    private void saveBid(Long projectId, @RequestBody Bid bid, Project project) {
        if (bid.getBid() <= project.getMaxBudget()) {
            project.setMinBid(bid.getBid());
            projectRepository.save(project);
            bid.setProject(projectRepository.findById(projectId).get());
            bidRepository.save(bid);
            calculateMinimumBid(project);
        } else {
            throw new QuickjobsException("Please enter the bid less than or equal to the project maximum budget: " + project.getMaxBudget());
        }
    }

    private void calculateMinimumBid(Project project) {
        List<Bid> list = project.getBids();
        HashSet<Double> hashSet = new HashSet<>();
        Double value;
        for (int i = 0; i < list.size(); i++) {
            value = list.get(i).getBid();
            if (value != null) {
                hashSet.add(value);
            }
        }
        if (!hashSet.isEmpty()) {
            Object obj = Collections.min(hashSet);
            project.setMinBid(new Double(obj.toString()));
            projectRepository.save(project);
        }
    }

}
