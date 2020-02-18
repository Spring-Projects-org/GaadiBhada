package com.spring.boot.security.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.spring.boot.security.dto.ChallanBookData;
import com.spring.boot.security.entity.BoxDetails;
import com.spring.boot.security.entity.ChallanBook;
import com.spring.boot.security.entity.LotBook;
import com.spring.boot.security.entity.SubLotBook;
import com.spring.boot.security.forms.ChallanBookForm;
import com.spring.boot.security.repository.BoxDetailsRepository;
import com.spring.boot.security.repository.LotBookRepository;
import com.spring.boot.security.traderData.Trader;
import com.spring.boot.security.utils.SecurityUtils;

@Controller
public class FormDataController {

	
	@Autowired
	ChallanBookData challanBookData;
	
	@Autowired
	BoxDetailsRepository boxDetailsRepository;
	
	@Autowired
	LotBookRepository lotBookRepository;
	
	@RequestMapping(value="/management/saveChallan")
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
	public String saveChallan(@RequestParam Date date, @RequestParam String truckNo, @RequestParam(required = false) String driverName,
			@RequestParam(required = false) String driverMobile,@RequestParam Integer[] traderId,@RequestParam Integer[] itemId,
			@RequestParam Integer[] boxId,@RequestParam Integer[] totalQty,@RequestParam String[] receiver) throws Exception
	{
		
		
		SecurityUtils securityUtils=new SecurityUtils();
		System.out.println("driverName:"+driverName);
		ChallanBook challanBook=new ChallanBook();
		challanBook.setDate(date);
		challanBook.setDriverName(driverName);
		challanBook.setTruckNo(truckNo);
		challanBook.setDriverMobile(driverMobile);
		challanBookData.saveChallanData(challanBook);
		
		for(int i=0;i<traderId.length;i++)
		{
			LotBook lotBook=new LotBook();
			lotBook.setChallanId(challanBook.getChallanId());
			lotBook.setTraderId(traderId[i]);
			lotBook.setItemId(itemId[i]);
			lotBook.setTotalQty(totalQty[i]);
			int wtPerBox=boxDetailsRepository.findById(boxId[i]).get().getTotal_wt();
			lotBook.setTotalWt(wtPerBox*totalQty[i]);
			lotBook.setBoxId(boxId[i]);
			lotBookRepository.save(lotBook);
			
			if(i==1)
			throw new Exception();
			
			SubLotBook subLotBook=new SubLotBook();
			subLotBook.setLot_id(lotBook.getLotId());
			subLotBook.setReceiver(receiver[i]);
			subLotBook.setTotal_fare(0);
			subLotBook.setTotal_qty(0);
			
			
		}
		return "management";
		
		
	}
}
