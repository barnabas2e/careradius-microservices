package com.exl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exl.model.Member;
import com.exl.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	private MemberRepository memberRepository;
	
	@Autowired
	public void setMemberRepository(MemberRepository pMemberRepository) {
		this.memberRepository = pMemberRepository;
	}
	
	@Override
	public Member save(Member pMember) {
		return memberRepository.save(pMember);
	}

	@Override
	public List<String> findMemberByInternalId(List<Long> internalIdList) {
		return memberRepository.findByInternalIdList(internalIdList);
	}

	@Override
	public Member findMember(String pMemberId) {
		return memberRepository.findByMemberId(pMemberId).orElse(null);
	}

	@Override
	public void deleteMember(String memmberId) {
		Member lExistingMember = findMember(memmberId);
		if (lExistingMember == null) {
			// member does not exist, bail out		
			return;
		}
		
		memberRepository.deleteById(lExistingMember.getInternalId());
	}

}
