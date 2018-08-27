package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class PlusMinusButton extends JButton{
	
	public PlusMinusButton() {
		setText(GUIConstants.PLUS_MINUS_BUTTON_NAME.toString());
		setBackground(GUIConstants.DEFAULT_BUTTON_COLOR);
		setForeground(GUIConstants.DEFAULT_BUTTON_TEXTCOLOR);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIHelp.setNegative();
			}
		});
	}
}
