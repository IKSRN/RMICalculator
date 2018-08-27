package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ClearEntryButton extends JButton{

	public ClearEntryButton() {
		setText(GUIConstants.CLEAR_ENTRY_BUTTON_NAME);
		setBackground(GUIConstants.DEFAULT_BUTTON_COLOR);
		setForeground(GUIConstants.DEFAULT_BUTTON_TEXTCOLOR);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GUIHelp.clearEntry();
			}
		});
	}
}
