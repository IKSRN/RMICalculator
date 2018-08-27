package client;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class GUI extends JFrame {

	private static JLabel outputLabel;
	private static JLabel formulaLabel;

	public GUI() {
		initializeGUI();
	}

	private void initializeGUI() {
		setIconImage(GUIConstants.CALCULATOR_ICON.getImage());
		setSize(GUIConstants.GUI_SIZE);
		setResizable(false);
		setTitle(GUIConstants.GUI_TITLE);
		setLocationRelativeTo(null);

		add(initializeOutputPanel(), BorderLayout.NORTH);
		add(initializeInputPanel(), BorderLayout.CENTER);
		setVisible(true);
	}

	private JPanel initializeOutputPanel() {
		JPanel outputPanel = new JPanel();
		GridLayout inputOutputLayout = new GridLayout(2, 1);
		outputPanel.setLayout(inputOutputLayout);
		outputPanel.setBackground(GUIConstants.GUI_BACKGROUND_COLOR);

		formulaLabel = new JLabel("0", SwingConstants.RIGHT);
		formulaLabel.setPreferredSize(GUIConstants.FORMULA_SIZE);
		formulaLabel.setForeground(GUIConstants.OPERATOR_BUTTON_TEXTCOLOR);
		formulaLabel.setFont(GUIConstants.FORMULA_FONT);

		outputLabel = new JLabel("0", SwingConstants.CENTER);
		outputLabel.setPreferredSize(GUIConstants.OUTPUT_SIZE);
		outputLabel.setForeground(GUIConstants.OPERATOR_BUTTON_TEXTCOLOR);
		outputLabel.setFont(GUIConstants.OUTPUT_FONT);

		outputPanel.add(formulaLabel);
		outputPanel.add(outputLabel);

		return outputPanel;
	}

	private JPanel initializeInputPanel() {
		JPanel inputPanel = new JPanel();
		inputPanel.setBackground(GUIConstants.GUI_BACKGROUND_COLOR);

		GridLayout buttonLayout = new GridLayout(GUIConstants.GRID_BUTTON_ROWS, GUIConstants.GRID_BUTTON_COLUMNS);
		inputPanel.setLayout(buttonLayout);
		buttonLayout.setHgap(GUIConstants.HGAP_BUTTONS);
		buttonLayout.setVgap(GUIConstants.VGAP_BUTTONS);
		
		for (JButton button : GUIHelp.createButtons()) {
			inputPanel.add(button);
		}

		return inputPanel;
	}

	public static String getOutput() {
		return outputLabel.getText();
	}

	public static void setOutput(String response) {
		if (!response.equals(GUIConstants.ERROR_TEXT)) {
			outputLabel.setText(response);
		} else {
			outputLabel.setText("0");
			setFormula(GUIConstants.ERROR_TEXT);
		}
	}

	public static String getFormula() {
		return formulaLabel.getText();
	}

	public static void setFormula(String text) {
		formulaLabel.setText(text);
	}

}
