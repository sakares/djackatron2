package com.test.djackatron2.service;

import com.test.djackatron2.exception.InsufficientFundException;
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

		if (amount == 0)
			throw new IllegalArgumentException();
		else if (amount < 0)
			throw new IllegalArgumentException();

		double feeRate = this.feePolicy.calculateFee(amount);
		double minimumTransferRate = 10d;
		
		if (amount < minimumTransferRate)
			throw new IllegalArgumentException();
		
		Account sender = this.accRepository.find(senderID);
		Account receiver = this.accRepository.find(receiverID);

		double senderBalance = sender.getBalance();
		double receiverBalance = receiver.getBalance();

		if (amount + feeRate > senderBalance)
			throw new InsufficientFundException();
		

		sender.setBalance(senderBalance - amount - feeRate);
		receiver.setBalance(receiverBalance + amount);
	}

}
