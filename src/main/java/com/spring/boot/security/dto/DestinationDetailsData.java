package com.spring.boot.security.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.security.entity.DestinationDetails;
import com.spring.boot.security.repository.DestinationDetailsRepository;



@Service
public class DestinationDetailsData {
	
	@Autowired
	DestinationDetailsRepository destinationDetails;
	
	public List<DestinationDetails> getDestinations()
	{
		return destinationDetails.findAll();
	}

}
