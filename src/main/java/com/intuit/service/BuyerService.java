package com.intuit.service;


import java.util.List;
import java.util.Optional;

import com.intuit.entity.Buyer;



public interface BuyerService {

    Optional<Buyer> getBuyerById(Long id);
    List<Buyer> getAllBuyers();
    void saveBuyer(Buyer buyer);


}
