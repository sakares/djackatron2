package com.test.djackatron2.service;

public class FeePolicy {
	private Long id;
	private String type;
	private double amount;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public boolean setFee(double fee) {
		this.setAmount(fee);
		if (this.getAmount() > 0 )
			return true;
		else 
			return false;
	}
	
	public double calculateFee() {
		double result = 0.0;
		if (this.getAmount() == 1000.0)
			result = 5.0;
		return result;
	}
}
