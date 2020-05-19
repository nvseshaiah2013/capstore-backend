package com.cg.capstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.capstore.entities.MerchantDetails;

public interface MerchantRepository extends JpaRepository<MerchantDetails, String> {

}
