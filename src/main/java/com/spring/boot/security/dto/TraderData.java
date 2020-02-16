package com.spring.boot.security.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.security.repository.TraderRepository;
import com.spring.boot.security.traderData.Trader;

@Service
public class TraderData {

	@Autowired
	TraderRepository traderRepository;
	
	public List<Trader> getAllTradersList()
	{
		//return traderRepository.findByTrader_id(1);
		return  traderRepository.findAll();
	}
}
