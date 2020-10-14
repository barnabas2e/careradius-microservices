package com.exl.service;

import java.util.List;

import com.exl.model.User;

public interface UserService {

	User save(User user);
	
	User findByUsername(String username);
	
	List<String> findUsers(List<Long> idList);
}
