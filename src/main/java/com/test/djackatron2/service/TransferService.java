package com.test.djackatron2.service;

import com.test.djackatron2.model.Account;

public class TransferService {

	private FeePolicy feePolicy;
	private AccountRepository accRepository;

	public AccountRepository getAccRepository() {
		return accRepository;
	}

	public void setAccRepository(AccountRepository accRepository) {
		this.accRepository = accRepository;
	}

	public FeePolicy getFeePolicy() {
		return feePolicy;
	}

	public void setFeePolicy(FeePolicy feePolicy) {
		this.feePolicy = feePolicy;
	}

	public void setAccountRepository(AccountRepository accRepository) {
		this.accRepository = accRepository;
	}

	public void transferMoney(double amount, long senderID, long receiverID) {
		double feeRate = this.feePolicy.calculateFee(amount);
		Account sender = this.accRepository.find(senderID);
		sender.setBalance(sender.getBalance() - amount - feeRate);
		Account receiver = this.accRepository.find(receiverID);
		receiver.setBalance(receiver.getBalance() + amount);
	}

}
