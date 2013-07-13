package com.test.djackatron2.service;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.test.djackatron2.exception.InsufficientFundException;
import com.test.djackatron2.model.Account;

public class TestExceptionCaseTransfer {

	private Account srcAcc;
	private Account desAcc;
	private double flatFeePolicy = 5d;
	private TransferService transferService;
	
	@Before
	public void setup() {
		srcAcc = new Account(1l, "name1", 100d);
		desAcc = new Account(2l, "name2", 0d);
		transferService = new TransferService();
		
		FeePolicy feePolicy = mock(FeePolicy.class);
		when(feePolicy.calculateFee(anyDouble())).thenReturn(flatFeePolicy);
		
		AccountRepository accRepository = mock(AccountRepository.class);
		when(accRepository.find(srcAcc.getId())).thenReturn(srcAcc);
		when(accRepository.find(desAcc.getId())).thenReturn(desAcc);
		
		transferService.setFeePolicy(feePolicy);
		transferService.setAccRepository(accRepository);
	}
	
	@Test(expected=InsufficientFundException.class)
	public void testTransferWithInsufficientFund() {
		//given
		double amount = 100d;
		
		//when
		transferService.transferMoney(amount, srcAcc.getId(), desAcc.getId());
		
		//then
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTransferWhenZeroFund() {
		//given
		double amount = 0d;
		
		//when
		transferService.transferMoney(amount, srcAcc.getId(), desAcc.getId());
		
		//then
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTransferWhenNegativeFund() {
		//given
		double amount = -1d;
		
		//when
		transferService.transferMoney(amount, srcAcc.getId(), desAcc.getId());
		
		//then
		fail();
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testTransferBelowMinimumRate() {
		//given
		double amount = 5d;
		
		//when
		transferService.transferMoney(amount, srcAcc.getId(), desAcc.getId());
		
		//then
		fail();
	}
	
	

}
