package com.test.djackatron2.model;

public class Account {
	private Long id;
	private String name;
	private double balance;
	
	
	public Account(Long id, String name, double balance) {
		this.setId(id);
		this.setName(name);
		this.setBalance(balance);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
}
