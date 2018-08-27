  package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class DigitButton extends JButton {

	public DigitButton() {
		Character name = GUIConstants.DIGIT_BUTTON_NAMES[GUIHelp.getDigitButtonCounter()];
		setText(GUIConstants.DIGIT_BUTTON_NAMES[GUIHelp.getDigitButtonCounter()].toString());
		setBackground(GUIConstants.DIGIT_BUTTON_COLOR);
		setForeground(GUIConstants.DIGIT_BUTTON_TEXTCOLOR);
		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				GUIHelp.addDigit(name);
			}
		});
		GUIHelp.setDigitButtonCounter();
	}
}
