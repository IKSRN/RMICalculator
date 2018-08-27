package client;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import client.GUI;
import client.GUIHelp;

class GUIHelpTest {
	
	@BeforeAll
	static void setUp() {
		new Client();
		try {
			Client.createConnection();
			new GUI();
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testAddPointToOutput() {
		Character expected = '.';

		GUIHelp.addPoint();
		Character actual = GUI.getOutput().charAt(GUI.getOutput().length() - 1);

		assertEquals(expected, actual);
	}

	@Test
	void testAddPointNotAddToOutputIfOutputContainPoint() {
		Character expected = '.';

		GUIHelp.addPoint();
		Character actual = GUI.getOutput().charAt(GUI.getOutput().length() - 1);

		assertEquals(expected, actual);
	}

	@Test
	void testAddPointWithZeroToOutputIfLastPressedIsOperator() {
		String expected = "0.";

		GUI.setOutput("3");
		GUIHelp.setLastPressedButton('+');
		GUIHelp.addPoint();
		String actual = GUI.getOutput();

		assertEquals(expected, actual);
	}
	
	@Test
	void testAddPointSetLastButtonPoint() {
		Character expected = '.';
		
		GUIHelp.addPoint();
		Character actual = GUIHelp.getLastPressedButton();
		
		assertEquals(expected, actual);
	}

	@Test
	void testAddOperatorAndOutputToFormula() {
		Character input = '+';
		String expected = "0+";

		GUIHelp.addOperatorAndOutputToFormula(input);
		boolean actual = GUI.getFormula().endsWith(expected);

		assertTrue(actual);
	}
	
	@Test
	void testAddOperatorReplaceIfFormulaEqualsZero() {
		Character input = '+';
		String expected = "0+";
		
		GUI.setOutput("0");
		GUI.setFormula("0");
		GUIHelp.setLastPressedButton(null);
		GUIHelp.addOperatorAndOutputToFormula(input);
		String actual = GUI.getFormula();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testAddOperatorReplaceIfFormulaIsError() {
		Character input = '+';
		String expected = "0+";
		
		GUI.setFormula("ERROR");
		GUI.setOutput("0");
		GUIHelp.setLastPressedButton(null);
		GUIHelp.addOperatorAndOutputToFormula(input);
		String actual = GUI.getFormula();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testAddOperatorAndOutputToFormulaAddNegativeOutputWithBrackets() {
		String inputOutput = "-3";
		Character inputOperator = '+';
		String expected = "(-3)+";
		
		GUI.setOutput(inputOutput);
		GUIHelp.addOperatorAndOutputToFormula(inputOperator);
		String actual = GUI.getFormula();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testAddOperatorAndOutputToFormulaAddIfOutputLastCharacterIsNotPoint() {
		String input = "3.";
		Character inputOperator = '+';
		String expected = "0";
		
		GUI.setOutput(input);
		GUIHelp.addOperatorAndOutputToFormula(inputOperator);
		String actual = GUI.getFormula();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testAddOperatorAndOutputToFormulaAddIfOutputLastCharacterIsNotMinus() {
		String input = "-";
		Character inputOperator = '+';
		String expected = "0";
		
		GUI.setOutput(input);
		GUIHelp.addOperatorAndOutputToFormula(inputOperator);
		String actual = GUI.getFormula();
		
		assertEquals(expected, actual);
	}

	@Test
	void testSetNegativeSetMinusBeforeOutput() {
		String input = "0";
		String expected = "-0";
		
		GUI.setOutput(input);
		GUIHelp.setNegative();
		String actual = GUI.getOutput();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testSetNegativeSetMinusBeforeOutputIfOutputNotContainMinus() {
		String input = "0";
		String expected = "-0";
		
		GUI.setOutput(input);
		GUIHelp.setNegative();
		String actual = GUI.getOutput();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testSetNegativeRemoveMinusBeforeOutputIfOutputContainMinus() {
		String input = "-0";
		String expected = "0";
		
		GUI.setOutput(input);
		GUIHelp.setNegative();
		String actual = GUI.getOutput();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testAddBracketsAddBrackets() {
		String input = "-3";
		String expected = "(-3)";
		
		String actual = GUIHelp.addBracketsIfNegative(input);;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testAddBracketsAddBracketsOnlyIfOutputContainMinus() {
		String input = "3";
		String expected = "3";
		
		GUI.setOutput(input);
		String actual = GUIHelp.addBracketsIfNegative(input);;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testAppendText() {
		String base = "in";
		String input = "put";
		String expected = "input";
		
		String actual = GUIHelp.appendText(base, input);
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testAddDigit() {
		Character input = '1';
		String expected = "11";
		
		GUI.setOutput("1");
		GUIHelp.setLastPressedButton(null);
		GUIHelp.addDigit(input);
		String actual = GUI.getOutput();
		
		assertEquals(expected, actual);
	}
	
	void testAddDigitReplaceIfLastPressedIsOperator() {
		Character input = '1';
		String expected = "1";
		
		GUIHelp.setLastPressedButton('+');
		GUIHelp.addDigit(input);
		String actual = GUI.getOutput();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testAddDigitReplaceIfOutPutIsZero() {
		Character input = '1';
		String expected = "1";
		
		GUI.setOutput("0");
		GUIHelp.addDigit(input);
		String actual = GUI.getOutput();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testAddDigitSetLastPressedButtonDigit() {
		Character input = '1';
		Character expected = '1';
		
		GUIHelp.setLastPressedButton('+');
		GUIHelp.addDigit(input);
		Character actual = GUIHelp.getLastPressedButton();
		
		assertEquals(expected, actual);
	}
	
	//funktioniert nicht wegen Nullpointer
	@Test
	void testEquateSetOutput2ForInput1Plus1() {
		String inputFormula = "1+";
		String inputOutput = "1";
		String expected = "2.0";
		
		GUI.setFormula(inputFormula);
		GUI.setOutput(inputOutput);
		GUIHelp.equate();
		String actual = GUI.getOutput();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testLastButtonIsOperator() {
		boolean condition = GUIHelp.lastButtonIsOperatorOrEquals('+');
		
		assertTrue(condition);
	}
	
	@Test
	void testLastButtonIsOperatorReturnFalseIfCharacterIsNotOperator() {
		boolean condition = GUIHelp.lastButtonIsOperatorOrEquals('1');
		
		assertFalse(condition);
	}
	
	@Test
	void testLastButtonIsOperatorReturnFalseIfCharacterIsEmpty() {
		boolean condition = GUIHelp.lastButtonIsOperatorOrEquals(null);
		
		assertFalse(condition);
	}
	
	@Test
	void testClearEntrySetOutputZero() {
		String expected = "0";
		
		GUIHelp.clearEntry();
		String actual = GUI.getOutput();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testClearSetOutputAndFormulaZero() {
		String expectedOutput = "0";
		String expectedFormula = "0";
		
		GUIHelp.clear();
		String actualOutput = GUI.getOutput();
		String actualFormula = GUI.getFormula();
		
		assertEquals(expectedOutput, actualOutput);
		assertEquals(expectedFormula, actualFormula);
	}
	
	@Test
	void testBackReturn12ForInput123() {
		String input = "123";
		String expected = "12";
		
		GUI.setOutput(input);;
		GUIHelp.back();
		String actual = GUI.getOutput();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testBackReturn0IfLengthSmaller2(){
		String input = "1";
		String expected = "0";
		
		GUI.setOutput(input);
		GUIHelp.back();
		String actual = GUI.getOutput();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testBackReturn0IfOutputIsNegativeDigit() {
		String input = "-3";
		String expected = "0";
		
		GUI.setOutput(input);
		GUIHelp.back();
		String actual = GUI.getOutput();
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testRemoveBracketsReturnNumberForNumber() {
		String input = "335";
		String expected = "335";
		
		String actual = GUIHelp.addBracketsIfNegative(input);
		
		assertEquals(expected, actual);
	}
}
