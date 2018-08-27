package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class OperatorButton extends JButton{

	public OperatorButton() {
		Character name = GUIConstants.OPERATOR_BUTTON_NAMES[GUIHelp.getOperatorButtonCounter()];
		setText(GUIConstants.OPERATOR_BUTTON_NAMES[GUIHelp.getOperatorButtonCounter()].toString());
		setBackground(GUIConstants.OPERATOR_BUTTON_COLOR);
		setForeground(GUIConstants.OPERATOR_BUTTON_TEXTCOLOR);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				GUIHelp.addOperatorAndOutputToFormula(name);
			}
		});
		GUIHelp.setOperatorButtonCounter();
	}
}
