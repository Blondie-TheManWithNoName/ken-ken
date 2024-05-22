package presentation.custom;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JMainButton extends JButton {
	public JMainButton(String text, ButtonColors colors) {
		super(text);
//		Font font = loadFont("fonts/Spartan-Bold.ttf").deriveFont(Font.BOLD, 16);
//		setFont(font);
		setForeground(Color.decode(colors.getText()));
		setBackground(Color.decode(colors.getBackground()));
		setOpaque(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setFocusPainted(false);
		setPreferredSize(new Dimension(100, 50));
		Border border = BorderFactory.createLineBorder(Color.decode(colors.getBorder()), 5);
		setBorder(border);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(Color.decode(colors.getBackgroundHover()));
				setForeground(Color.decode(colors.getTextHover()));
				Border border = BorderFactory.createLineBorder(Color.decode(colors.getBorderHover()), 5);
				setBorder(border);


			}
			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(Color.decode(colors.getBackground()));
				setForeground(Color.decode(colors.getText()));
				Border border = BorderFactory.createLineBorder(Color.decode(colors.getBorder()), 5);
				setBorder(border);

			}
		});
	}

	public JMainButton(String text, ButtonColors colors, int width, int height) {
		this(text, colors);
		setPreferredSize(new Dimension(width, height));
	}
}
