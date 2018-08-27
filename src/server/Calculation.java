package server;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculation {

	double result;

	String doMaths(String formula) {
		return calculateResult(extractNumbersFromFormula(formula),
				extractOperatorsFromFormula(formula));
	}

	String calculateResult(ArrayList<Double> numbers, ArrayList<Character> operators) {
		
		addNumberToResult(numbers.get(0));
		
		for (int i = 0; i < operators.size();i++) {
			switch (operators.get(i)) {
			case '+': addNumberToResult(numbers.get(i+1)); break;
			case '-': subtractNumberFromResult(numbers.get(i+1)); break;
			case 'X': multiplyResultBy(numbers.get(i+1)); break;
			case ':':divideResultBy(numbers.get(i+1)); break;
			default: return CalculationConstants.ERROR_MESSAGE;
			}
		}
		
		return ""+result;
	}
	
	void addNumberToResult(double number) {
		result += number;
	}

	void subtractNumberFromResult(double number) {
		result -= number;
	}

	void multiplyResultBy(double number) {
		result *= number;
	}

	void divideResultBy(double number) {
		result /= number;
	}
	
	ArrayList<Double> extractNumbersFromFormula(String formula){
		ArrayList<Double> numbersOperators = new ArrayList<>();
		Pattern formulaPattern = Pattern.compile(CalculationConstants.NUMBER_PATTERN);
		Matcher matcher = formulaPattern.matcher(formula);
		
		while(matcher.find()) {
			String number = CalculationHelp.removeBrackets(matcher.group());
			numbersOperators.add(Double.parseDouble(number));
		}
		
		return numbersOperators;
	}

	ArrayList<Character> extractOperatorsFromFormula(String formula) {
		String numberPattern = CalculationConstants.NUMBER_PATTERN;
		ArrayList<Character> operators = new ArrayList<>();
		
		for (String operator : formula.split(numberPattern)) {
			if (operator != null && !operator.isEmpty()) {
				operators.add(operator.charAt(0));
			}
		}
		
		return operators;
	}

}
