package com.savings.global.service.impl;

import java.math.BigDecimal;

import com.savings.global.exceptions.NoDataFoundException;
import com.savings.global.repository.ITaxRateProviderRepository;
import com.savings.global.repository.impl.TaxRateProviderRepositoryImpl;
import com.savings.global.service.ITaxRateProviderService;

public class TaxRateProviderServiceImpl implements ITaxRateProviderService {
	
	ITaxRateProviderRepository repos = new TaxRateProviderRepositoryImpl();	

	@Override
	public BigDecimal getVatDetails(String country) throws NoDataFoundException {
		return repos.fetchTaxRate(country);
	}

}
