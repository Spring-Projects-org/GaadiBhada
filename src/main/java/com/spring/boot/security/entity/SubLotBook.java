package com.spring.boot.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sub_lot_book")
public class SubLotBook {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sub_lot_id;
	private int lot_id;
	private String receiver;
	private int total_qty;
	private int total_fare;
	@Column(name="crte_tms")
	private String createTimeStamp;
	@Column(name="session_id")
	private String sessionId;
	
	public int getSub_lot_id() {
		return sub_lot_id;
	}
	public void setSub_lot_id(int sub_lot_id) {
		this.sub_lot_id = sub_lot_id;
	}
	public int getLot_id() {
		return lot_id;
	}
	public void setLot_id(int lot_id) {
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
	public String getCreateTimeStamp() {
		return createTimeStamp;
	}
	public void setCreateTimeStamp(String createTimeStamp) {
		this.createTimeStamp = createTimeStamp;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
