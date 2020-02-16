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
	private long lotId;
	@Column(name="challan_id")
	private long challanId;
	@Column(name="trader_id")
	private String traderId;
	@Column(name="item_id")
	private String itemId;
	@Column(name="total_qty")
	private int totalQty;
	@Column(name="total_wt")
	private int totalWt;
	@Column(name="box_id")
	private int boxId;
	public long getLotId() {
		return lotId;
	}
	
	
	public void setLotId(long lotId) {
		this.lotId = lotId;
	}
	public long getChallanId() {
		return challanId;
	}
	public void setChallanId(long challanId) {
		this.challanId = challanId;
	}
	public String getTraderId() {
		return traderId;
	}
	public void setTraderId(String traderId) {
		this.traderId = traderId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
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
