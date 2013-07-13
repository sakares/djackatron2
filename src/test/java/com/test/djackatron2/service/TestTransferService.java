package com.test.djackatron2.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.test.djackatron2.model.Account;
import static org.mockito.Mockito.*;

public class TestTransferService {

	@Test
	public void testTransferMoney() {
		//given
		long senderID = 111;
		long receiverID = 222;
		double amount = 30d;
		double feeRate = 5d;
		
		Account sender = new Account();
		Account receiver = new Account();
		
		sender.setId(senderID);
		sender.setBalance(100d);
		
		receiver.setId(receiverID);
		receiver.setBalance(0d);
		
		TransferService service = new TransferService();
		FeePolicy feePolicy = mock(FeePolicy.class);
		when(feePolicy.calculateFee(anyDouble())).thenReturn(feeRate);
		
		AccountRepository accRepository = mock(AccountRepository.class);
		when(accRepository.find(senderID)).thenReturn(sender);
		when(accRepository.find(receiverID)).thenReturn(receiver);
		
		service.setFeePolicy(feePolicy);
		service.setAccountRepository(accRepository);
		
		//when
		service.transferMoney(amount, senderID, receiverID);
		
		//then
		assertEquals(amount, receiver.getBalance(), 0d);
		assertEquals(65d, sender.getBalance(), 0d);
	}
}
