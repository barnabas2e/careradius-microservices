package com.exl.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exl.model.User;
import com.exl.repository.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {

	private UserRepository userRepository;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User lUser = userRepository.findByUsername(username).orElse(null);
		if (lUser == null) {
			throw new UsernameNotFoundException(username);
		}
		Set<GrantedAuthority> lAuthorities = new HashSet<>();
		
		return new org.springframework.security.core.userdetails.User(username, lUser.getPassword(), lAuthorities);
	}

}
