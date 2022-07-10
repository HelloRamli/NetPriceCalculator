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
	
	public static void main(String[] args) {
		NetPriceDetail netPrice = new NetPriceDetail();
		try {
			System.out.println("NetPrice for DE: " + netPrice.getNetPrice(100, DE));
			System.out.println("NetPrice for FR: " +netPrice.getNetPrice(1.9, FR));
		} catch (NoDataFoundException e) {
			e.printStackTrace();
		}

	}


}
