package com.cg.capstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.capstore.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
	User findByUsername(String username);
}