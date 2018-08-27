package server;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import server.Calculation;

class CalculationTests {
	
	Calculation c;

	@BeforeEach
	void setUp() {
		c = new Calculation();
	}
	
	@Test
	void addNumberToResult() {
		double input = 3.0;
		double expected = 3.0;
		
		c.addNumberToResult(input);
		double actual = c.result;
		
		assertEquals(expected, actual);
	}

	@Test
	void subtractNumberFromResult() {
		double input = 3.0;
		double expected = -3.0;
		
		c.subtractNumberFromResult(input);
		double actual = c.result;
		
		assertEquals(expected, actual);
	}

	@Test
	void multiplyResultBy() {
		double input = 3.0;
		double expected = 3.0;
		
		c.result = 1.0;
		c.multiplyResultBy(input);
		double actual = c.result;
		
		assertEquals(expected, actual);
	}

	@Test
	void divideResultBy() {
		double input = 3.0;
		double expected = 1.0;
		
		c.result = 3.0;
		c.divideResultBy(input);
		double actual = c.result;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testExtractOperatorsFromFormula() {
		String input = "123.3-256.4:242.24+934.135X12.3";
		ArrayList<Character> expected = new ArrayList<>();
		expected.add('-');
		expected.add(':');
		expected.add('+');
		expected.add('X');
		
		ArrayList<Character> actual = c.extractOperatorsFromFormula(input);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void ExtractSameOperatorsFromFormula(){
		String input = "123.3+256.4+242.24+934.135+12.3";
		ArrayList<Character> expected = new ArrayList<>();
		expected.add('+');
		expected.add('+');
		expected.add('+');
		expected.add('+');
		
		ArrayList<Character> actual = c.extractOperatorsFromFormula(input);
		
		assertEquals(expected, actual);	}
	
	@Test
	void testExtractNumbersBySameOperator() {
		String input = "123.3+256.4+242.24+934.135+12.3";
		ArrayList<Double> expected = new ArrayList<>();
		expected.add(123.3);
		expected.add(256.4);
		expected.add(242.24);
		expected.add(934.135);
		expected.add(12.3);
		
		ArrayList<Double> actual = c.extractNumbersFromFormula(input);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testExtractNumbersFromFormulaByDifferentOperators() {
		String input = "123.3-256.4:242.24+934.135X12.3";
		ArrayList<Double> expected = new ArrayList<>();
		expected.add(123.3);
		expected.add(256.4);
		expected.add(242.24);
		expected.add(934.135);
		expected.add(12.3);
		
		ArrayList<Double> actual = c.extractNumbersFromFormula(input);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testExtractNumbersFromFormulaWithNegativeSubtraction() {
		String input = "50-10.0-(-10)";
		ArrayList<Double> expected = new ArrayList<>();
		expected.add(50.0);
		expected.add(10.0);
		expected.add(-10.0);
				
		ArrayList<Double> actual = c.extractNumbersFromFormula(input);		
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculateResultReturn400ForInput() {
		String input = "123.4+276.6";
		String expected = "400.0";
		
		String actual = c.calculateResult(c.extractNumbersFromFormula(input), 
				c.extractOperatorsFromFormula(input));
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculateResultReturn401ForInput() {
		String input = "123.4+277.6";
		String expected = "401.0";
		
		String actual = c.calculateResult(c.extractNumbersFromFormula(input), 
				c.extractOperatorsFromFormula(input));
		
		assertEquals(expected, actual);
	}
	
	
	
	@Test
	void testCalculateResultReturn400ForMultipleInput() {
		String input = "64.3+65.2+100-100+70.5";
		String expected = "200.0";
		
		String actual = c.calculateResult(c.extractNumbersFromFormula(input), 
				c.extractOperatorsFromFormula(input));
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculateResultReturn50ForMultipleInputWithNumbers() {
		String input = "50-10.0-(-10)";
		String expected = "50.0";
		
		String actual = c.calculateResult(c.extractNumbersFromFormula(input), c.extractOperatorsFromFormula(input));
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculateResultReturn400ForMultipleInputWithDifferentOperators() {
		String input = "64.3+65.2-120.5X100:100";
		String expected = "9.0";
		
		String actual = c.calculateResult(c.extractNumbersFromFormula(input), 
				c.extractOperatorsFromFormula(input));
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculateResultReturnErrorForInputEmptyString() {
		ArrayList<Character> inputOperators = new ArrayList<>();
		inputOperators.add(',');
		ArrayList<Double> inputNumbers = new ArrayList<>();
		inputNumbers.add(4.2);
		String expected = "ERROR";
		
		
		String actual = c.calculateResult(inputNumbers, inputOperators);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testDoMathsSimpleReturn8ForInput3Plus5() {
		String input = "3.0+5.0";
		String expected = "8.0";
		
		String actual = c.doMaths(input);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testDoMathsComplexReturnForInput() {
		String input = "3+3-3X3:3+(-3)-(-3)X(-3):(-3)";
		String expected = "3.0";
		
		String actual = c.doMaths(input);
		
		assertEquals(expected, actual);
	}

}
