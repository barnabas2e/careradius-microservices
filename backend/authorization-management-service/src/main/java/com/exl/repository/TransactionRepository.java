package com.exl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exl.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findAllByAuthorizationInternalId(Long authorizationInternalId);
	
	List<Transaction> findAllByMemberInternalId(Long memberInternalId);
}
