package com.intuit.service;

import java.util.List;
import java.util.Optional;

import com.intuit.entity.Seller;
import com.intuit.repository.SellerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SellerServiceImpl implements SellerService{

    SellerRepository sellerRepository;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public List<Seller> getAllSellers() {
       return sellerRepository.findAll();
    }

    @Override
    public Optional<Seller> getSellerById(Long id) {
        Optional<Seller> seller = sellerRepository.findById(id);
        return seller;
    }

    @Override
    public void saveSeller(Seller seller) {
        sellerRepository.save(seller);
    }
}
