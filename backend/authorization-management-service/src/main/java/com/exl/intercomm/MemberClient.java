package com.exl.intercomm;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("member-service")
public interface MemberClient {

	@RequestMapping(method = RequestMethod.POST, value = "service/names", consumes = "application/json")
	public List<String> getMemberName(@RequestBody List<Long> memberInternalId);
	
	// deprecated
	@RequestMapping(method = RequestMethod.POST, value = "service/names", consumes = "application/json")
	public List<String> getMemberNamesByMemberId(@RequestBody List<String> memberIdList);
	
}
