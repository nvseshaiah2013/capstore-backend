package com.cg.capstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.capstore.dao.ICustomerDao;
import com.cg.capstore.dao.UserRepository;
import com.cg.capstore.entities.User;

@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService, UserDetailsService{


	@Autowired
	private ICustomerDao customerDao;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
	
	@Override
	public String createNewUser( com.cg.capstore.request.UserDetails user) {
		return customerDao.createNewUser(user);
	}

}
