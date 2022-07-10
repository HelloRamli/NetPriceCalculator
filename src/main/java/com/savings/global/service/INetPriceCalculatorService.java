package com.savings.global.service;

import java.math.BigDecimal;
import java.math.MathContext;

import com.savings.global.exceptions.NoDataFoundException;

public interface INetPriceCalculatorService {
	
	public BigDecimal calculateNetPrice(BigDecimal grossPrice, String countryISO) throws NoDataFoundException;
	
}
