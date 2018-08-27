package client;

import java.util.ArrayList;

import javax.swing.JButton;

public class GUIHelp {

	private static Character lastPressedButton;
	private static int operatorButtonsCounter;
	private static int digitButtonsCounter;

	static int getOperatorButtonCounter() {
		return operatorButtonsCounter;
	}

	static void setOperatorButtonCounter() {
		operatorButtonsCounter++;
	}

	static int getDigitButtonCounter() {
		return digitButtonsCounter;
	}

	static void setDigitButtonCounter() {
		digitButtonsCounter++;
	}

	static Character getLastPressedButton() {
		return lastPressedButton;
	}

	static void setLastPressedButton(Character pressedButton) {
		lastPressedButton = pressedButton;
	}

	static String appendText(String base, String toAppend) {
		return new StringBuilder(base).append(toAppend).toString();
	}

	static boolean lastButtonIsOperatorOrEquals(Character button) {
		if (button != null) {
			return button.equals(GUIConstants.EQUALS_BUTTON_NAME)
					|| button.equals(GUIConstants.OPERATOR_BUTTON_NAMES[0])
					|| button.equals(GUIConstants.OPERATOR_BUTTON_NAMES[1])
					|| button.equals(GUIConstants.OPERATOR_BUTTON_NAMES[2])
					|| button.equals(GUIConstants.OPERATOR_BUTTON_NAMES[3]);
		}
		return false;
	}

	static String addBracketsIfNegative(String output) {
		if (output.contains("-")) {
			output = "(" + output + ")";
		}
		return output;
	}

	static ArrayList<JButton> createButtons() {
		ArrayList<JButton> buttons = new ArrayList<>();
		for (int i = 0; i < GUIConstants.NUMBER_OF_BUTTONS; i++) {
			if (isIndexOfOperatorButton(i)) {
				buttons.add(new OperatorButton());
				continue;
			} 
			if (i == GUIConstants.GRID_POSITION_EQUALS) {
				buttons.add(new EqualsButton());
				continue;
			} 
			if (isIndexOfDigitButton(i)) {
				buttons.add(new DigitButton());
			}
			if (i == GUIConstants.GRID_POSTIION_CLEAR_ENTRY) {
				buttons.add(new ClearEntryButton());
				continue;
			} 
			if (i == GUIConstants.GRID_POSITION_CLEAR) {
				buttons.add(new ClearButton());
				continue;
			} 
			if (i == GUIConstants.GRID_POSITION_BACK) {
				buttons.add(new BackButton());
				continue;
			} if (i == GUIConstants.GRID_POSITION_PLUSMINUS) {
				buttons.add(new PlusMinusButton());
				continue;
			} 
			if (i == GUIConstants.GRID_POSITION_POINT) {
				buttons.add(new PointButton());
				continue;
			} 
			if (i == GUIConstants.GRID_POSITION_SQUARE_ROOT) {
				buttons.add(new SquareRootButton());
				continue;
			}
			if (i == GUIConstants.GRID_POSITION_SQUARE) {
				buttons.add(new SquareButton());
				continue;
			} 
			if (i == GUIConstants.GRID_POSITION_PERCENTAGE) {
				buttons.add(new PercentageButton());
				continue;
			} 
			if (i == GUIConstants.GRID_POSITION_ONE_DIVIDED_BY_X) {
				buttons.add(new OneDividedByXButton());
				continue;
			} 
			if (i == GUIConstants.GRID_POSITION_CHANGE) {
				buttons.add(new ChangeButton());
				continue;
			} else {
				buttons.add(new JButton());
			}
		}
		return buttons;
	}

	private static boolean isIndexOfDigitButton(int i) {
		return i > GUIConstants.GRID_POSITION_DIGITS_FIRST_LINE_MIN
				&& i < GUIConstants.GRID_POSITION_DIGITS_FIRST_LINE_MAX
				|| i > GUIConstants.GRID_POSITION_DIGITS_SECOND_LINE_MIN
						&& i < GUIConstants.GRID_POSITION_DIGITS_SECOND_LINE_MAX
				|| i > GUIConstants.GRID_POSITION_DIGITS_THIRD_LINE_MIN
						&& i < GUIConstants.GRID_POSITION_DIGITS_THIRD_LINE_MAX
				|| i == GUIConstants.GRID_POSITION_DIGITS_ZERO;
	}

	private static boolean isIndexOfOperatorButton(int i) {
		return (i + 1) % GUIConstants.GRID_POSITION_OPERATORS_DIVIDE == 0
				&& i < GUIConstants.GRID_POSITION_OPERATORS_MAX && i > GUIConstants.GRID_POSITION_OPERATORS_MIN;
	}

	static void clearEntry() {
		GUI.setOutput("0");
	}

	static void clear() {
		clearEntry();
		GUI.setFormula("0");
	}

	static void back() {
		if (GUI.getOutput().length() > 1 && !GUI.getOutput().matches("-\\d"))
			GUI.setOutput(GUI.getOutput().substring(0, GUI.getOutput().length() - 1));
		else
			GUI.setOutput("0");
	}

	static void setNegative() {
		if (!GUI.getOutput().contains("-"))
			GUI.setOutput("-" + GUI.getOutput());
		else {
			GUI.setOutput(GUI.getOutput().replace("-", ""));
		}
		setLastPressedButton(GUIConstants.PLUS_MINUS_BUTTON_NAME);
	}

	static void addDigit(Character pressedButton) {
		if (!lastButtonIsOperatorOrEquals(getLastPressedButton()) && !GUI.getOutput().equals("0")) {
			GUI.setOutput(appendText(GUI.getOutput(), pressedButton.toString()));
		} else {
			GUI.setOutput(pressedButton.toString());
		}

		setLastPressedButton(pressedButton);
	}

	static void addPoint() {
		if (lastButtonIsOperatorOrEquals(getLastPressedButton())) {
			GUI.setOutput("0.");
		} else if (!GUI.getOutput().contains(GUIConstants.POINT_BUTTON_NAME.toString())) {
			GUI.setOutput(appendText(GUI.getOutput(), GUIConstants.POINT_BUTTON_NAME.toString()));
		}
		setLastPressedButton(GUIConstants.POINT_BUTTON_NAME);
	}

	static void addOperatorAndOutputToFormula(Character pressedButton) {
		if (!GUI.getOutput().matches(GUIConstants.NUMBER_WITH_POINT_AT_END_PATTERN) && 
				!GUI.getOutput().matches("-")) {
			if (GUI.getFormula().equals("0") || GUI.getFormula().equals(GUIConstants.ERROR_TEXT)) {
				GUI.setFormula(addBracketsIfNegative(GUI.getOutput()) + pressedButton);
			} else {
				GUI.setFormula(appendText(GUI.getFormula(), addBracketsIfNegative(GUI.getOutput()) + pressedButton));
			}
			setLastPressedButton(pressedButton);
		}
	}

	static void equate() {
		if (!GUI.getOutput().matches(GUIConstants.NUMBER_WITH_POINT_AT_END_PATTERN) && !GUI.getOutput().matches("-")
				&& !GUI.getFormula().equals("0") || GUI.getFormula().equals(GUIConstants.ERROR_TEXT)) {

			GUI.setOutput(Client.sendRequest(GUI.getFormula() + addBracketsIfNegative(GUI.getOutput())));
			GUI.setFormula("0");

			setLastPressedButton(GUIConstants.EQUALS_BUTTON_NAME);
		}
	}
}
