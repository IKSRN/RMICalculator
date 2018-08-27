package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class OneDividedByXButton extends JButton{

	public OneDividedByXButton() {
		setText(GUIConstants.ONE_DIVIDED_BY_X_BUTTON_NAME);
		setBackground(GUIConstants.OPERATOR_BUTTON_COLOR);
		setForeground(GUIConstants.OPERATOR_BUTTON_TEXTCOLOR);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
