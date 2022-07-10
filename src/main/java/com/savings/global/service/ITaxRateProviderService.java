package com.savings.global.service;

import java.math.BigDecimal;

import com.savings.global.exceptions.NoDataFoundException;
import com.savings.global.repository.ITaxRateProviderRepository;
import com.savings.global.repository.impl.TaxRateProviderRepositoryImpl;

public interface ITaxRateProviderService {

	public BigDecimal getVatDetails(final String country) throws NoDataFoundException;

}
