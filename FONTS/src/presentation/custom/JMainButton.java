package presentation.custom;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JMainButton extends JButton {
	public JMainButton(String text, String mainColor, String secondaryColor, String hoverColor) {
		super(text);
//		Font font = loadFont("fonts/Spartan-Bold.ttf").deriveFont(Font.BOLD, 16);
//		setFont(font);
		setForeground(Color.decode(mainColor));
		setBackground(Color.decode(secondaryColor));
		setOpaque(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setFocusPainted(false);
		setPreferredSize(new Dimension(100, 50));
		Border border = BorderFactory.createLineBorder(Color.decode(mainColor), 5);
		setBorder(border);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(Color.decode(hoverColor));
				setForeground(Color.decode(secondaryColor));

			}
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(Color.decode(secondaryColor));
				setForeground(Color.decode(mainColor)); // cambia

			}
		});
	}
	public JMainButton() {
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFocusable(false);
	}
}
