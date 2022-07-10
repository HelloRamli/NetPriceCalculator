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
import com.savings.global.service.INetPriceCalculatorService;
import com.savings.global.service.ITaxRateProviderService;

@DisplayName("Net Price Calculator Service")
@ExtendWith(MockitoExtension.class)
class NetPriceCalculatorServiceImplTest {
	
	@Mock
	ITaxRateProviderService taxRateProviderService;
	
	@InjectMocks
	INetPriceCalculatorService service = new NetPriceCalculatorServiceImpl();	

	@DisplayName("Valid Scenario")
	@Test
	void shoud_pass_valid_scenarios() throws NoDataFoundException {
		when(taxRateProviderService.getVatDetails(any(String.class))).thenReturn(BigDecimal.valueOf(0.19));
		
		BigDecimal actualResult = service.calculateNetPrice(BigDecimal.valueOf(100), "DF");
		
		assertEquals(BigDecimal.valueOf(81),actualResult);
		verify(taxRateProviderService,times(1)).getVatDetails("DF");
	}
	
	
	@DisplayName("Exception Scenario 1")
	@Test
	void should_throw_exception_when_country_not_found() throws NoDataFoundException  {
		
		when(taxRateProviderService.getVatDetails(any(String.class))).thenThrow(new NoDataFoundException("No Data found for this country"));
		
		Throwable T = assertThrows(NoDataFoundException.class,()->service.calculateNetPrice(BigDecimal.valueOf(100),"R"));
			
		assertEquals("No Data found for this country", T.getMessage());
		verify(taxRateProviderService,times(1)).getVatDetails(any(String.class));
		
	}
	
	@DisplayName("Exception Scenario 2")
	@Test
	void should_throw_exception_when_country_is_empty() throws NoDataFoundException  {
		
		when(taxRateProviderService.getVatDetails(null)).thenThrow(new NoDataFoundException("Country is empty"));
		
		Throwable T = assertThrows(NoDataFoundException.class,()->taxRateProviderService.getVatDetails(null));

		assertEquals("Country is empty", T.getMessage());
		verify(taxRateProviderService,times(0)).getVatDetails(any(String.class));

		
	}

}
