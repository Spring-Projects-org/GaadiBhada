package com.spring.boot.security.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sub_lot_book")
public class SubLotBook {

	
	@Id
	private long sub_lot_id;
	private long lot_id;
	private String receiver;
	private int total_qty;
	private int total_fare;
	
	
	public long getSub_lot_id() {
		return sub_lot_id;
	}
	public void setSub_lot_id(long sub_lot_id) {
		this.sub_lot_id = sub_lot_id;
	}
	public long getLot_id() {
		return lot_id;
	}
	public void setLot_id(long lot_id) {
		this.lot_id = lot_id;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public int getTotal_qty() {
		return total_qty;
	}
	public void setTotal_qty(int total_qty) {
		this.total_qty = total_qty;
	}
	public int getTotal_fare() {
		return total_fare;
	}
	public void setTotal_fare(int total_fare) {
		this.total_fare = total_fare;
	}
}
