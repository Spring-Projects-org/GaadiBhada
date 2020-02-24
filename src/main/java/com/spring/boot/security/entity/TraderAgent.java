package com.spring.boot.security.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="trader_agent")
public class TraderAgent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="agent_id")
	private int agentId;
	@Column(name="agent_name")
	private String agentName;
	@Column(name="agent_mark")
	private String agentMmark;
	@Column(name="agent_address")
	private String agentAddress;
	
	
	public int getAgentId() {
		return agentId;
	}
	public void setAgentId(int agentId) {
		this.agentId = agentId;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getAgentMmark() {
		return agentMmark;
	}
	public void setAgentMmark(String agentMmark) {
		this.agentMmark = agentMmark;
	}
	public String getAgentAddress() {
		return agentAddress;
	}
	public void setAgentAddress(String agentAddress) {
		this.agentAddress = agentAddress;
	}

	

}
