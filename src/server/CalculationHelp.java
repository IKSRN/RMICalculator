package server;

public class CalculationHelp {

	static String removeBrackets(String number) {
		if (number.contains("(")) {
			number = number.replace("(", "");
			number = number.replace(")", "");
		}
		return number;
	}
}
