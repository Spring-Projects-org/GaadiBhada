package com.spring.boot.security.forms.data;

public class TableQuery {
	
	public static String getUpdateDistributionQuery()
	{
		return "select \r\n" + 
				"challanBook.challan_id,challanBook.challan_date,challanBook.truck_no,sourceDetails.source_name,"
				+ "destinationDetails.destination,traderDetails.trader_name,trader_mark,item.item_name,boxDetails.box_name,"
				+ "boxDetails.total_wt,lotBook.total_qty,lotBook.lot_id,subQuery.tot_bal\r\n" + 
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
				"on boxDetails.box_id=lotBook.box_id"+
				" inner join "+
				" (SELECT lotBook.lot_id,case when totDistributed is null then lotBook.total_qty-0 else lotBook.total_qty-sub_lot.totDistributed end as tot_bal FROM lot_book lotBook left join("+
				" SELECT lot_id,sum(total_qty) as totDistributed FROM sub_lot_book group by 1)"+
				" sub_lot on sub_lot.lot_id=lotBook.lot_id ) subQuery on subQuery.lot_id=lotBook.lot_id where subQuery.tot_bal !=0 "	
				;
	}
	public static String getUpdateModalQuery()
	{
		return "SELECT lotBook.lot_id,lotBook.total_qty,case when totDistributed is null then lotBook.total_qty-0 else lotBook.total_qty-sub_lot.totDistributed end as qtyAvl,lotBook.receiver FROM lot_book lotBook left join  \r\n" + 
				"(\r\n" + 
				"SELECT lot_id,sum(total_qty) as totDistributed FROM sub_lot_book group by 1\r\n" + 
				") sub_lot on sub_lot.lot_id=lotBook.lot_id where lotBook.lot_id=?";
	}
}
