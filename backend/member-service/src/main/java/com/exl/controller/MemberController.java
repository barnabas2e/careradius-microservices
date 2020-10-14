package com.exl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exl.model.Member;
import com.exl.service.MemberService;

@RestController
@RequestMapping("/service")
public class MemberController {
	
    private MemberService memberService; 
	 
    @Autowired
    public void setMemberService(MemberService memberService) {
    	this.memberService = memberService;
	}	

	@GetMapping("/test")
	public String test() {
		return "Member Service is running";
	}
	
	@GetMapping("/retrieve/{memberid}")
	public ResponseEntity<?> findMember(@PathVariable("memberid") String memberId) {
		return ResponseEntity.ok(memberService.findMember(memberId));		
	}
	
	@PostMapping("/names")
	public ResponseEntity<?> getNamesOfMembers(@RequestBody List<Long> memberInternalId) {
		return ResponseEntity.ok(memberService.findMemberByInternalId(memberInternalId));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> saveMember(@RequestBody Member member) {
		return new ResponseEntity<>(memberService.save(member), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/remove/{memberId}")
	public void deleteMember(String memberId) {
		memberService.deleteMember(memberId);
	}	
}
