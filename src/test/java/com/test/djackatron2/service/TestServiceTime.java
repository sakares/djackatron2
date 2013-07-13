package com.test.djackatron2.service;

import static org.junit.Assert.assertTrue;

import org.joda.time.LocalTime;
import org.junit.Test;

public class TestServiceTime {

	@Test
	public void testServiceAvailable() {
		//given
		LocalTime openService = new LocalTime(6,0);
		LocalTime closeService = new LocalTime(22,0);
		LocalTime testTime = new LocalTime(12, 0);
		TimeService timeService = new TimeService(openService, closeService);
		
		//when
		boolean result = timeService.isServiceAvilable(testTime);
		
		//then
		assertTrue(result);
	}

}
