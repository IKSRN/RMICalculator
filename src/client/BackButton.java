package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class BackButton extends JButton{

	public BackButton() {
		setText(GUIConstants.BACK_BUTTON_NAME.toString());
		setBackground(GUIConstants.DEFAULT_BUTTON_COLOR);
		setForeground(GUIConstants.DEFAULT_BUTTON_TEXTCOLOR);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIHelp.back();
			}
		});
	}
}
