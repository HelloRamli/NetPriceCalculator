package com.savings.global.repository.impl;

import java.math.BigDecimal;
import java.util.Objects;

import com.savings.global.exceptions.NoDataFoundException;
import com.savings.global.repository.ITaxRateProviderRepository;

public class TaxRateProviderRepositoryImpl implements ITaxRateProviderRepository {

	@Override
	public BigDecimal fetchTaxRate(String country) throws NoDataFoundException {
		
		if(Objects.isNull(country)) {
			throw new NoDataFoundException("Country is empty");
		}
		
		BigDecimal d = ITaxRateProviderRepository.getVatInfo().get(country);
		
		if(Objects.nonNull(d)){ 
			return d;
		}else {
			throw new NoDataFoundException("No Data found for this country");
		}
		
	}

}
