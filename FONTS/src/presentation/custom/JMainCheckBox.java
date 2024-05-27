package presentation.custom;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JMainCheckBox extends JCheckBox {
	public JMainCheckBox(String unselected, String selected) {
//		super(text);
		setFocusPainted(false);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		setIcon(new ImageIcon(unselected));
		setSelectedIcon(new ImageIcon(selected));
		setPreferredSize(new Dimension(5, 5));

	}

	public void addActionListenerAndCommand(ActionListener listener, String actionCommand) {
		this.addActionListener(listener);
		this.setActionCommand(actionCommand);
	}
}
