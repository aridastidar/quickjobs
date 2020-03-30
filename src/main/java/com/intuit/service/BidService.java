package com.intuit.service;


import java.util.List;
import java.util.Optional;

import com.intuit.entity.Bid;


public interface BidService {

    Optional<Bid> getBidById(Long id);
    List<Bid> getAllBids();
    void saveBid(Long projectId, Bid bid);

}
