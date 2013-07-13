package com.test.djackatron2.service;

import org.joda.time.LocalTime;

public class TimeService {

	private LocalTime openService;
	private LocalTime closeService;
	
	public TimeService(LocalTime openService, LocalTime closeService) {
		this.setOpenService(openService);
		this.setCloseService(closeService);
	}
	
	public LocalTime getOpenService() {
		return openService;
	}
	public void setOpenService(LocalTime openService) {
		this.openService = openService;
	}
	public LocalTime getCloseService() {
		return closeService;
	}
	public void setCloseService(LocalTime closeService) {
		this.closeService = closeService;
	}
	
	public boolean isServiceAvilable(LocalTime time) {
		int openService = this.getOpenService().getHourOfDay();
		int closeService = this.getCloseService().getHourOfDay();
		int currentTime = time.getHourOfDay();
		boolean result = false;
		
		if(currentTime > openService && currentTime < closeService)
			result = true;
			
		return result;
	}
}
