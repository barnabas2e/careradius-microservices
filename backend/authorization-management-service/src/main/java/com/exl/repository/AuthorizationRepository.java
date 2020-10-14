package com.exl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exl.model.Authorization;

public interface AuthorizationRepository extends JpaRepository<Authorization, Long> {

	Authorization findAuthorizationByReferenceNumber(String referenceNumber);
	
}
