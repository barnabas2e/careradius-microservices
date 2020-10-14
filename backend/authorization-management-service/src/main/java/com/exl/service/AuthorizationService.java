package com.exl.service;

import java.util.List;

import com.exl.model.Authorization;
import com.exl.model.Transaction;

public interface AuthorizationService {

	List<Authorization> findAllAuthorization();
	
	Authorization findAuthorization(Long AuthorizationInternalId);
	
	Authorization findAuthorization(String referenceNumber);
	
	List<Transaction> findTransactionOfAuthorization(Long authorizationInternalId);
	
	List<Transaction> findTransactionOfMember(Long memberInternalId);
	
	Authorization saveAuthorization(Authorization authorization);
	
	Transaction saveTransaction(Transaction transaction);
} 
