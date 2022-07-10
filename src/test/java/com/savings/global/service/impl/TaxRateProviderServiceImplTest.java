package com.savings.global.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.savings.global.exceptions.NoDataFoundException;
import com.savings.global.repository.ITaxRateProviderRepository;
import com.savings.global.service.ITaxRateProviderService;

@DisplayName("Tax Rate Provider Service")
@ExtendWith(MockitoExtension.class)
class TaxRateProviderServiceImplTest {

	@Mock
	ITaxRateProviderRepository repos;
	
	@InjectMocks
	ITaxRateProviderService taxRateProviderService = new TaxRateProviderServiceImpl();

	@DisplayName("Valid Scenario")
	@Test
	void should_work_under_valid_scenarios() throws NoDataFoundException {
		
		when(repos.fetchTaxRate(any(String.class))).thenReturn(BigDecimal.valueOf(0.19));
		
		BigDecimal actualResult = taxRateProviderService.getVatDetails("R");
		
		assertEquals(BigDecimal.valueOf(0.19),actualResult);
		verify(repos,times(1)).fetchTaxRate(any(String.class));
		
	}
	
	@DisplayName("Exception Scenario 1")
	@Test
	void should_throw_exception_when_country_not_found() throws NoDataFoundException  {
		
		when(repos.fetchTaxRate(any(String.class))).thenThrow(new NoDataFoundException("No Data found for this country"));
		
		Throwable T = assertThrows(NoDataFoundException.class,()->taxRateProviderService.getVatDetails("R"));
			
		assertEquals("No Data found for this country", T.getMessage());
		verify(repos,times(1)).fetchTaxRate(any(String.class));
		
	}
	
	@DisplayName("Exception Scenario 2")
	@Test
	void should_throw_exception_when_country_is_empty() throws NoDataFoundException  {
		
		when(repos.fetchTaxRate(null)).thenThrow(new NoDataFoundException("Country is empty"));
		
		Throwable T = assertThrows(NoDataFoundException.class,()->taxRateProviderService.getVatDetails(null));

		assertEquals("Country is empty", T.getMessage());
		verify(repos,times(0)).fetchTaxRate(any(String.class));

		
	}

}
