package client;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import client.GUI;

class GUITest {

	/*
	 * @Test void test() { fail("Not yet implemented"); }
	 */

	@BeforeAll
	static void setUp() {
		new GUI();
	}
	
	@Test
	void testSetOutputSetOutputEqualToInput() {
		String input = "23";
		String expected = "23";
		
		GUI.setOutput(input);
		String actual = GUI.getOutput();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testSetOutputSetOutputZeroIfInputError() {
		String input = "ERROR";
		String expected = "0";
		
		GUI.setOutput(input);
		String actual = GUI.getOutput();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testSetOutputSetFormulaErrorIfInputError() {
		String input = "ERROR";
		String expected = "ERROR";
		
		GUI.setFormula(input);
		String actual = GUI.getFormula();
		
		assertEquals(expected, actual);
	}

}
