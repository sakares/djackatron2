package com.test.djackatron2.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value=Parameterized.class)
public class TestFeePolicy {
	private double fixedRate, amount, expectedFee;
	
	public TestFeePolicy(double fixedRate, double amount, double expectedFee) {
		this.fixedRate = fixedRate;
		this.amount = amount;
		this.expectedFee = expectedFee;
	}
	
	@Parameters
	public static Collection<Object[]> primeNumbers() {
		return Arrays
				.asList(new Object[][] {
						{ 5, 1, 5 },
						{ 5, 10, 5 },
						{ 5, 1000, 5 },
						{ 10, 1, 10 },
						{ 10, 10, 10 },
						{ 10, 1000, 10 },
				});
	}
	

	@Test
	public void testEditableFee() {
		FeePolicy feePolicy = new FeePolicy();
		feePolicy.setFee(10.0);
		assertEquals(10.0, feePolicy.getFee(),0.0);
	}

	@Test
	public void testFeePolicy() {
		// given
		FeePolicy feePolicy = new FeePolicy();
		feePolicy.setFee(fixedRate);
		
		//when
		feePolicy.setAmount(amount);
		double result = feePolicy.calculateFee(amount);
		
		// then
		assertEquals(expectedFee, result, 0.0);
	}

}
