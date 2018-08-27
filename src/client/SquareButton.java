package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class SquareButton extends JButton{

	public SquareButton() {
		setText(GUIConstants.SQUARE_BUTTON_NAME);
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
