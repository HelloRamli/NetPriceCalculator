package com.savings.global;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.savings.global.exceptions.NoDataFoundException;


@DisplayName("Net Price Details")
public class NetPriceDetailTest {
	
	NetPriceDetail detail;
	
	@BeforeEach
	void setUp() throws Exception {	
		detail = new NetPriceDetail();
	}

	@DisplayName("Valid scenarios")
	@ParameterizedTest
	@MethodSource("inputValidScenarios")
	void should_pass_valid_Scenarios(double grossPrice, String country, BigDecimal expectedOutput ) throws NoDataFoundException {
		BigDecimal actualOutput = detail.getNetPrice(grossPrice, country); 		
		assertEquals(expectedOutput, actualOutput);
	}
	
	static Stream<Arguments> inputValidScenarios() {		
		MathContext mc = new MathContext(2);
	    return Stream.of(
	        Arguments.of(100,"DE",new BigDecimal(81)),
	        Arguments.of(1.99,"FR",new BigDecimal(1.6,mc))   	        
	    );
	}
	
	@DisplayName("Exception scenarios")
	@ParameterizedTest
	@MethodSource("inputExceptionScenarios")
	void should_create_exceptions(double grossPrice, String country, String expectedExceptionMessage) {
		
		Throwable t = assertThrows(NoDataFoundException.class, ()->detail.getNetPrice(grossPrice,country));
		
		assertEquals(expectedExceptionMessage,t.getMessage());
		
	}
	
	static Stream<Arguments> inputExceptionScenarios() {		
	    return Stream.of(
	        Arguments.of(100,null,"Country is empty"),
	        Arguments.of(1.99,"SOME", "No Data found for this country")   	        
	    );
	}
			
			
	
	
	
	
	
	

}
