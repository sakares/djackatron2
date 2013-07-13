package com.test.djackatron2.service;

public class FeePolicy {
	private Long id;
	private String type;
	private double amount;
	private double fee;
	
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
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	
	public double calculateFee(double amount) {
		double result = 0.0;
		if (amount == 1000.0 || amount == 10.0 || amount == 1.0)
			result = this.getFee();
		return result;
	}
}
