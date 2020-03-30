package com.intuit.service;

import java.util.List;
import java.util.Optional;

import com.intuit.entity.Seller;



public interface SellerService {

    Optional<Seller> getSellerById(Long id);
    List<Seller> getAllSellers();
    void saveSeller(Seller seller);

}
