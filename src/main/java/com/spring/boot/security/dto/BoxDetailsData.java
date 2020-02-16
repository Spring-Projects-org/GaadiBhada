package com.spring.boot.security.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.security.formData.BoxDetails;
import com.spring.boot.security.repository.BoxDetailsRepository;

@Service
public class BoxDetailsData {

	@Autowired
	BoxDetailsRepository boxDetailsRepository;
	
	public List<BoxDetails> getAllBoxType()
	{
		
		return boxDetailsRepository.findAll();
	}
	
}
