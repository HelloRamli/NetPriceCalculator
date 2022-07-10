package com.savings.global.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.savings.global.exceptions.NoDataFoundException;

public interface ITaxRateProviderRepository {	
	public BigDecimal fetchTaxRate(String country) throws NoDataFoundException;
	
	public static Map<String,BigDecimal> getVatInfo() {
		
		Map<String, BigDecimal> vatInfo = new HashMap<>();
		vatInfo.put("FR",  new BigDecimal(.20));
		vatInfo.put("DE",  new BigDecimal(.19));
		vatInfo.put("GB",  new BigDecimal(.33));
		vatInfo.put("US",  new BigDecimal(.23));
		vatInfo.put("MAL", new BigDecimal(.25));
		vatInfo.put("IN",  new BigDecimal(.30));
		
		return vatInfo;
	}

}
