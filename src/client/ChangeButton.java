package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ChangeButton extends JButton{

	public ChangeButton() {
		setText(GUIConstants.CHANGE_BUTTON_NAME.toString());
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
