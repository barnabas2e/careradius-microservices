package com.exl.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.exl.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
	
	Optional<Member> findByMemberId(String memberId);
	
	@Query("select m.formattedName from Member m where m.internalId in (:pInternalIdList)")
	List<String> findByInternalIdList(@Param("pInternalIdList") List<Long> internalIdList);
	
	@Query("select m.formattedName from Member m where m.memberId in (:pMemberIdList)")
	List<String> findByMemberIdList(@Param("pMemberIdList") List<String> memberIdList);
	
	@Query("select m.memberId, m.formattedName from Member m where m.memberId = (:pMemberId)")
	String findById(@Param("pMemberId") String memberId);

}
