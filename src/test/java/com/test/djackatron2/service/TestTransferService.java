package com.test.djackatron2.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.Collection;

import org.joda.time.LocalTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.test.djackatron2.model.Account;

@RunWith(value = Parameterized.class)
public class TestTransferService {
	private Account srcAcc, desAcc;
	private double flatFeePolicy, amount, srcBalance, desBalance;

	public TestTransferService(Account srcAcc, Account desAcc,
			double flatFeePolicy, double amount, double srcBalance,
			double desBalance) {
		this.srcAcc = srcAcc;
		this.desAcc = desAcc;
		this.flatFeePolicy = flatFeePolicy;
		this.amount = amount;
		this.srcBalance = srcBalance;
		this.desBalance = desBalance;
	}

	@Parameters
	public static Collection<Object[]> primeNumbers() {
		return Arrays.asList(new Object[][] {
				{ new Account(1l, "name1", 100d), new Account(2l, "name2", 0d),
						5d, 30d, 65d, 30d },
				{ new Account(1l, "name1", 100d), new Account(2l, "name2", 0d),
						10d, 50d, 40d, 50d }, });
	}

//	@Test
//	public void testTransferMoney() {
//		// given
//		TransferService service = new TransferService();
//
//		FeePolicy feePolicy = mock(FeePolicy.class);
//		when(feePolicy.calculateFee(anyDouble())).thenReturn(flatFeePolicy);
//
//		AccountRepository accRepository = mock(AccountRepository.class);
//
//		when(accRepository.find(srcAcc.getId())).thenReturn(srcAcc);
//		when(accRepository.find(desAcc.getId())).thenReturn(desAcc);
//
//		service.setFeePolicy(feePolicy);
//		service.setAccRepository(accRepository);
//
//		// when
//		service.transferMoney(amount, srcAcc.getId(), desAcc.getId());
//
//		// then
//		assertEquals(desBalance, desAcc.getBalance(), 0d);
//		assertEquals(srcBalance, srcAcc.getBalance(), 0d);
//	}

	@Test
	public void testTransferWithTimeServiceWhenAvailableService() {
		// given
		TransferService service = new TransferService();

		FeePolicy feePolicy = mock(FeePolicy.class);
		when(feePolicy.calculateFee(anyDouble())).thenReturn(flatFeePolicy);

		AccountRepository accRepository = mock(AccountRepository.class);

		when(accRepository.find(srcAcc.getId())).thenReturn(srcAcc);
		when(accRepository.find(desAcc.getId())).thenReturn(desAcc);
		
		TimeService timeService = mock(TimeService.class);
		when(timeService.isServiceAvilable(any(LocalTime.class))).thenReturn(true);

		service.setFeePolicy(feePolicy);
		service.setTimeService(timeService);
		service.setAccRepository(accRepository);

		// when
		service.transferMoney(amount, srcAcc.getId(), desAcc.getId());

		// then
		assertEquals(desBalance, desAcc.getBalance(), 0d);
		assertEquals(srcBalance, srcAcc.getBalance(), 0d);
		
		verify(timeService).isServiceAvilable(any(LocalTime.class));
	}
}
