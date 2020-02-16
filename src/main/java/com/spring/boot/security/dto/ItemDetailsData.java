package com.spring.boot.security.dto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.security.formData.ItemDetails;
import com.spring.boot.security.repository.ItemDetailsRepository;

@Service
public class ItemDetailsData {

	@Autowired
	ItemDetailsRepository itemDetailsRepository;
	
	public List<ItemDetails> getAllItems()
	{
		
		return itemDetailsRepository.findAll();
	}
	
}
