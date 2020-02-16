package com.spring.boot.security.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.spring.boot.security.forms.ChallanBookForm;
import com.spring.boot.security.traderData.Trader;
import com.spring.boot.security.utils.SecurityUtils;

@Controller
public class FormDataController {

	/*@RequestParam Date date, @RequestParam String truckNo, @RequestParam String driverName,
	@RequestParam String driverMobile,@RequestParam Integer[] traderId,@RequestParam Integer[] itemId,
	@RequestParam Integer[] boxId,@RequestParam Integer[] totalQty,@RequestParam Integer[] totalWt*/
	@RequestMapping(value="/management/saveChallan")
	public String saveChallan(@RequestParam Date date, @RequestParam String truckNo, @RequestParam String driverName,
			@RequestParam String driverMobile,@RequestParam Integer[] traderId,@RequestParam Integer[] itemId,
			@RequestParam Integer[] boxId,@RequestParam Integer[] totalQty,@RequestParam Integer[] totalWt)
	{
		SecurityUtils securityUtils=new SecurityUtils();
		
		System.out.println(totalQty[0]);
		System.out.println(totalQty[1]);
		System.out.println(totalWt[0]);
		
	      return "management";
		
		
	}
}
