package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class SquareRootButton extends JButton{

	public SquareRootButton() {
		setText(GUIConstants.SQUARE_ROOT_BUTTON_NAME.toString());
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
