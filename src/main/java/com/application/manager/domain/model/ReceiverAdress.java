package com.application.manager.domain.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class ReceiverAdress {
	private String receiverName;
	private String receiverStreet;
	private String receiverCity;
	
	public ReceiverAdress() {}
	
	public String getReceiverName() {
		return receiverName;
	}
	
	public void setReceiverName(String name) {
		this.receiverName = name;
	}
	
	public String getReceiverStreet() {
		return receiverStreet;
	}
	
	public void setReceiverStreet(String street) {
		this.receiverStreet = street;
	}
	
	public String getReceiverCity() {
		return receiverCity;
	}
	
	public void setReceiverCity(String city) {
		this.receiverCity = city;
	}
}
