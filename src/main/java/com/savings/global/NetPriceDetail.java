package com.savings.global;

import java.math.BigDecimal;

import com.savings.global.exceptions.NoDataFoundException;
import com.savings.global.service.INetPriceCalculatorService;
import com.savings.global.service.impl.NetPriceCalculatorServiceImpl;

public class NetPriceDetail { 
	
	private static final String FR = "FR";
	private static final String DE = "DE";
	 
	INetPriceCalculatorService service = new NetPriceCalculatorServiceImpl();
	
	public BigDecimal getNetPrice(double grossPrice, String country) throws NoDataFoundException {
		return service.calculateNetPrice(new BigDecimal(grossPrice), country); 
	}


}
