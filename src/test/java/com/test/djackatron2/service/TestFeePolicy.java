package com.test.djackatron2.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestFeePolicy {

	@Test
	public void testSpecifiableFee() {
		FeePolicy feePolicy = new FeePolicy();
		assertEquals("Fee must be specifiable", feePolicy.setFee(10.0), true );
	}
	
	@Test
	public void testFeeReturnAsPolicy5Bath() {
		// given
		double flatFee = 1000.0;
		FeePolicy feePolicy = new FeePolicy();
		assertEquals("Fee must be specifiable", feePolicy.setFee(flatFee), true );
		
		//when
		double result = feePolicy.calculateFee();
		
		//then
		assertEquals(5.0, result, 0.0);
	}
	
	
}
