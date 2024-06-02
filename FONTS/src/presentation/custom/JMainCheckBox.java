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

		ImageIcon unselectedIcon = new ImageIcon(unselected);
		ImageIcon selectedIcon = new ImageIcon(selected);

		setIcon(new ImageIcon(unselectedIcon.getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT)));
		setSelectedIcon(new ImageIcon(selectedIcon.getImage().getScaledInstance(55, 55, Image.SCALE_DEFAULT)));
		setPreferredSize(new Dimension(15, 10));


	}

	public void addActionListenerAndCommand(ActionListener listener, String actionCommand) {
		this.addActionListener(listener);
		this.setActionCommand(actionCommand);
	}
}
