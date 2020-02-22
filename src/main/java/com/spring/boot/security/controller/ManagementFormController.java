package com.spring.boot.security.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.boot.security.dto.BoxDetailsData;
import com.spring.boot.security.dto.DestinationDetailsData;
import com.spring.boot.security.dto.ItemDetailsData;
import com.spring.boot.security.dto.TraderData;
import com.spring.boot.security.traderData.Trader;
import com.spring.boot.security.utils.SecurityUtils;

@Controller
public class ManagementFormController {
	
	@Autowired
	TraderData traderData;
	@Autowired
	ItemDetailsData itemDetailsData;
	@Autowired
	BoxDetailsData boxDetailsData;
	@Autowired
	DestinationDetailsData destinationDetailsData;
	
	@RequestMapping(value="/management")
	public String managementPage(Model model)
	{
		SecurityUtils securityUtils=new SecurityUtils();
		String username=securityUtils.getUser();
        List<Trader> traders=traderData.getAllTradersList();
		model.addAttribute("username", username);
		//Map<Integer,String> traders=fetchTrader()
	      
	      model.addAttribute("traders", traders);
	      model.addAttribute("items", itemDetailsData.getAllItems());
	      model.addAttribute("boxTypes", boxDetailsData.getAllBoxType());
	      model.addAttribute("destinations", destinationDetailsData.getDestinations());
		
	      return "management";
		
		
	}

}
