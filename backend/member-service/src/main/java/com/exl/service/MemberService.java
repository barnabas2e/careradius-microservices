package com.exl.service;

import java.util.List;

import com.exl.model.Member;


public interface MemberService {

	Member save(Member pMember);
	
	//List<String> findMemberById(List<String> memberIdList);
	
	List<String> findMemberByInternalId(List<Long> memberIdList);
	
	Member findMember(String pMemberId);
	
	void deleteMember(String memmberId);
}
