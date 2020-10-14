package com.exl.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exl.model.Authorization;
import com.exl.model.Transaction;
import com.exl.repository.AuthorizationRepository;
import com.exl.repository.TransactionRepository;

@Service
@Transactional
public class AuthorizationServiceImpl implements AuthorizationService {

	private AuthorizationRepository authorizationRepository;
	private TransactionRepository transactionRepository;
	
	@Autowired
	public void setAuthorizationRepository(AuthorizationRepository authorizationRepository) {
		this.authorizationRepository = authorizationRepository;
	}
	
	@Autowired
	public void setTransactionRepository(TransactionRepository transactionRepository) {
		this.transactionRepository = transactionRepository;
	}
	
	@Override
	public List<Authorization> findAllAuthorization() {
		return authorizationRepository.findAll();
	}

	@Override
	public Authorization findAuthorization(Long AuthorizationInternalId) {
		return authorizationRepository.findById(AuthorizationInternalId).orElse(null);
	}
	
	@Override
	public List<Transaction> findTransactionOfAuthorization(Long authorizationInternalId) {
		return transactionRepository.findAllByAuthorizationInternalId(authorizationInternalId);
	}

	@Override
	public List<Transaction> findTransactionOfMember(Long memberInternalId) {
		return transactionRepository.findAllByMemberInternalId(memberInternalId);
	}

	@Override
	@Transactional
	public Transaction saveTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	@Override
	public Authorization saveAuthorization(Authorization authorization) {
		return authorizationRepository.save(authorization);
	}

	@Override
	public Authorization findAuthorization(String referenceNumber) {
		if (referenceNumber == null) {
			return null;
		}
		return authorizationRepository.findAuthorizationByReferenceNumber(referenceNumber);
	}

}
