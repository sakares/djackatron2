package com.test.djackatron2.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestFeePolicy {

	@Test
	public void testEditableFee() {
		FeePolicy feePolicy = new FeePolicy();
		feePolicy.setFee(10.0);
		assertEquals(10.0, feePolicy.getFee(),0.0);
	}

	@Test
	public void testFeeReturnAsPolicy5Bath() {
		// init
		FeePolicy feePolicy = new FeePolicy();
		double result = 0.0;

		// given
		feePolicy.setAmount(1.0);

		// when
		result = feePolicy.calculateFee();

		// then
		assertEquals(5.0, result, 0.0);

		
		// given
		feePolicy.setAmount(10.0);

		// when
		result = feePolicy.calculateFee();

		// then
		assertEquals(5.0, result, 0.0);

		
		// given
		feePolicy.setAmount(1000.0);

		// when
		result = feePolicy.calculateFee();

		// then
		assertEquals(5.0, result, 0.0);
	}

}
