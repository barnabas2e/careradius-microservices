package com.exl.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exl.intercomm.MemberClient;
import com.exl.intercomm.UserClient;
import com.exl.model.Authorization;
import com.exl.model.ContactMethod;
import com.exl.model.Transaction;
import com.exl.service.AuthorizationService;

@RestController
@RequestMapping("/service")
public class AuthorizationController {

	private AuthorizationService authorizationService;
	private UserClient userClient;
	private MemberClient memberClient;	
	
	@Autowired
	private DiscoveryClient discoveryClient;	
	
	@Autowired
	private Environment env;
	
	@Value("${spring.application.name}")
	private String serviceId;
		
	@Autowired
	public void setAuthorizationService(AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}
	
	@Autowired
	public void setUserClient(UserClient userClient) {
		this.userClient = userClient;
	}
	
	@Autowired
	public void setMemberClient(MemberClient memberClient) {
		this.memberClient = memberClient;
	}
	
	@GetMapping("/port")
	private String getPort() {
		return "Authorization Service is running at port : " +env.getProperty("local.server.port");
	}
	
	@GetMapping("/instances")
	public ResponseEntity<?> getInstances() {
		return ResponseEntity.ok(discoveryClient.getInstances(serviceId));	
	}
	
	@GetMapping("/member/{memberInternalId}")
	public ResponseEntity<?> findTransactionOfMember(@PathVariable Long memberInternalId) {		
		return ResponseEntity.ok(authorizationService.findTransactionOfMember(memberInternalId));
	}
	
	// not used in the UI for now
	@GetMapping("/auth/{authorizationInternalId}")
	public ResponseEntity<?> findMemberAuthorizations(@PathVariable Long authorizationInternalId) {
		List<Transaction> transactions = authorizationService.findTransactionOfAuthorization(authorizationInternalId);
		if (CollectionUtils.isEmpty(transactions)) {
			return ResponseEntity.notFound().build();
		}		
		List<Long> memberUidList = transactions.parallelStream()
				.map(t -> t.getMemberInternalId()).collect(Collectors.toList());
		List<String> memberList = memberClient.getMemberName(memberUidList);		
		return ResponseEntity.ok(memberList);
	}
	
	@Transactional
	@PostMapping("/initiate")
	public ResponseEntity<?> saveTransaction(@RequestBody Transaction transaction) {
		// add a logic to validate if auth reference number already exist, and throw http status of conflict
		
		authorizationService.saveAuthorization(transaction.getAuthorization());
		
		transaction.setCreateDate(LocalDateTime.now());
		transaction.setAuthorization(authorizationService.findAuthorization(transaction.getAuthorization().getReferenceNumber()));		
		return new ResponseEntity<>(authorizationService.saveTransaction(transaction), HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> findAllAuthorizations() {
		return ResponseEntity.ok(authorizationService.findAllAuthorization());
	}
	
	@GetMapping("find/{referenceNumber}")
	public ResponseEntity<?> findAuth(@PathVariable String referenceNumber) {
		return ResponseEntity.ok(authorizationService.findAuthorization(referenceNumber));
	}
}
