package com.test.djackatron2.service;

import org.joda.time.LocalTime;

import com.test.djackatron2.exception.InsufficientFundException;
import com.test.djackatron2.model.Account;

public class TransferService {

	private TimeService timeService;
	private FeePolicy feePolicy;
	private AccountRepository accRepository;

	public TimeService getTimeService() {
		return timeService;
	}

	public void setTimeService(TimeService timeService) {
		this.timeService = timeService;
	}

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

	public void transferMoney(double amount, long senderID, long receiverID) {

		if(this.timeService!=null&&this.timeService.isServiceAvilable(LocalTime.now())){
			
		}
		
//		if(!(this.timeService.isServiceAvilable(LocalTime.now())))
//				throw new ServiceTimeException();
			
		
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
		
//		System.out.println("senderBalance = " + senderBalance);
//		System.out.println("amount = " + amount);
//		System.out.println("feeRate = " + feeRate);

		sender.setBalance(senderBalance - amount - feeRate);
		receiver.setBalance(receiverBalance + amount);
	}

}
