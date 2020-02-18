package com.spring.boot.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lot_book")
public class LotBook {

	@Id
	@GeneratedValue
	@Column(name="lot_id")
	private int lotId;
	@Column(name="challan_id")
	private int challanId;
	@Column(name="trader_id")
	private int traderId;
	@Column(name="item_id")
	private int itemId;
	@Column(name="total_qty")
	private int totalQty;
	@Column(name="total_wt")
	private int totalWt;
	@Column(name="box_id")
	private int boxId;
	
	
	public int getLotId() {
		return lotId;
	}
	
	
	public void setLotId(int lotId) {
		this.lotId = lotId;
	}
	public int getChallanId() {
		return challanId;
	}
	public void setChallanId(int challanId) {
		this.challanId = challanId;
	}
	public int getTraderId() {
		return traderId;
	}
	public void setTraderId(int traderId) {
		this.traderId = traderId;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}
	public int getTotalWt() {
		return totalWt;
	}
	public void setTotalWt(int totalWt) {
		this.totalWt = totalWt;
	}
	public int getBoxId() {
		return boxId;
	}
	public void setBoxId(int boxId) {
		this.boxId = boxId;
	}
	
	
	
	
}
