package com.spring.boot.security.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.security.entity.TraderAgent;
import com.spring.boot.security.repository.TraderAgentRespository;

@Service
public class TraderAgentDetails {
	
	@Autowired
	TraderAgentRespository traderAgentRespository;
	
	public List<TraderAgent> getAllTraderAgent()
	{
		
		return traderAgentRespository.findAll();
	}

}
