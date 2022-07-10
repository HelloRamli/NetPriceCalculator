package com.savings.global.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;

import com.savings.global.exceptions.NoDataFoundException;
import com.savings.global.service.INetPriceCalculatorService;
import com.savings.global.service.ITaxRateProviderService;


public class NetPriceCalculatorServiceImpl implements INetPriceCalculatorService {
	
	ITaxRateProviderService taxRateProviderService = new TaxRateProviderServiceImpl();

	@Override
	public BigDecimal calculateNetPrice(BigDecimal grossPrice, String countryISO) throws NoDataFoundException {
		MathContext m = new MathContext(2);
		return grossPrice.subtract(grossPrice.multiply(taxRateProviderService.getVatDetails(countryISO), m),m);
	}

}
