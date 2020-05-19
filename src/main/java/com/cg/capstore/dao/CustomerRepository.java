package com.cg.capstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.capstore.entities.CustomerDetails;

public interface CustomerRepository extends JpaRepository<CustomerDetails, String> {

}
