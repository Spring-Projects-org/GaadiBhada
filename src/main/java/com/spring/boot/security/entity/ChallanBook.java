package com.spring.boot.security.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="challan_book")

public class ChallanBook {
	
	@Id
	@Column(name="challan_id")
	@GeneratedValue
	private long challanId;
	@Column(name="challan_date")
	private Date date;
	@Column(name="truck_no")
	private String truckNo;
	@Column(name="driver_name")
	private String driverName;
	@Column(name="driver_mobile")
	private long driverMobile;
	
	
	public long getChallanId() {
		return challanId;
	}
	public void setChallanId(long challanId) {
		this.challanId = challanId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTruckNo() {
		return truckNo;
	}
	public void setTruckNo(String truckNo) {
		this.truckNo = truckNo;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public long getDriverMobile() {
		return driverMobile;
	}
	public void setDriverMobile(long driverMobile) {
		this.driverMobile = driverMobile;
	}
	

}
