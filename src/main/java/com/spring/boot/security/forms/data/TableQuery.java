package com.spring.boot.security.forms.data;

public class TableQuery {
	
	public String getUpdateDistributionQuery()
	{
		return "select \r\n" + 
				"challanBook.challan_id,challanBook.challan_date,challanBook.truck_no,sourceDetails.source_name,destinationDetails.destination,traderDetails.trader_name,trader_mark,item.item_name,boxDetails.box_name,boxDetails.total_wt,lotBook.total_qty\r\n" + 
				"from challan_book challanBook\r\n" + 
				"inner join source_details sourceDetails\r\n" + 
				"on sourceDetails.source_id=challanBook.source_id\r\n" + 
				"inner join\r\n" + 
				"destination_details destinationDetails\r\n" + 
				"on destinationDetails.destination_id=challanBook.destination_id\r\n" + 
				"inner join\r\n" + 
				"lot_book lotBook\r\n" + 
				"on lotBook.challan_id=challanBook.challan_id\r\n" + 
				"inner join trader_details traderDetails\r\n" + 
				"on traderDetails.trader_id=lotBook.trader_id\r\n" + 
				"inner join item_description item\r\n" + 
				"on item.item_id=lotBook.item_id\r\n" + 
				"inner join box_details boxDetails\r\n" + 
				"on boxDetails.box_id=lotBook.box_id";
	}

}
