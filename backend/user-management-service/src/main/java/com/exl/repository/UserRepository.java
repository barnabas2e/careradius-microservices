package com.exl.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exl.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	/**
	 * Find a user by username
	 * @param username
	 * 
	 * @return user if one exist otherwise null
	 */
	Optional<User> findByUsername(String username);
	
	/**
	 * Finds all user by list of user id's
	 * 
	 * @param idList
	 * 
	 * @return list of user's (formatted) name 
	 */
	@Query("select u.name from User u where u.id in (:pIdList)")
	List<String> findByIdList(@Param("pIdList") List<Long> idList);
}
