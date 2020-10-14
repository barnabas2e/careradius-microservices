package com.exl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exl.model.User;
import com.exl.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	 
	@Autowired
	public void setUserRepository(UserRepository pUserRepository) {
		this.userRepository = pUserRepository;
	}
	
	@Override
	public User save(User user) {
		//encode the username and password
		//user.setUsername(passwordEncoder.encode(user.getUsername()));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}

	@Override
	public List<String> findUsers(List<Long> idList) {
		return userRepository.findByIdList(idList);
	}

}
