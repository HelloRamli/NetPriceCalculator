package com.savings.global.repository.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.savings.global.exceptions.NoDataFoundException;

@DisplayName("Tax Rate Provider Repository Test")
class TaxRateProviderRepositoryImplTest {
	
	TaxRateProviderRepositoryImpl taxRateProviderRepositoryImpl;

	@BeforeEach
	void setUp() throws Exception {
		taxRateProviderRepositoryImpl = new TaxRateProviderRepositoryImpl();
	}

	@DisplayName("Valid Scenario")
	@Test
	void should_pass_with_valid_inputs() throws NoDataFoundException {		
		BigDecimal actualResult = taxRateProviderRepositoryImpl.fetchTaxRate("DE");
		
		assertEquals(new BigDecimal(0.19),actualResult);		
	}
	
	@DisplayName("Exception Scenario 1")
	@Test
	void should_fail_with_exception_when_country_not_found()  {		
		Throwable t = assertThrows(NoDataFoundException.class, ()->taxRateProviderRepositoryImpl.fetchTaxRate("D"));
		
		assertEquals("No Data found for this country",t.getMessage());		
	}
	
	@DisplayName("Exception Scenario 2")
	@Test
	void should_fail_with_exception_when_country_is_empty()  {		
		Throwable t = assertThrows(NoDataFoundException.class, ()->taxRateProviderRepositoryImpl.fetchTaxRate(null));
		
		assertEquals("Country is empty",t.getMessage());		
	}
	
	
	@AfterEach
	void tearDown() throws Exception {
		taxRateProviderRepositoryImpl=null;
	}

}
