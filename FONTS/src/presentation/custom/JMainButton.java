package presentation.custom;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JMainButton extends JButton {
	public JMainButton(String text, ButtonColors colors) {
		super(text);
//		Font font = loadFont("fonts/Spartan-Bold.ttf").deriveFont(Font.BOLD, 16);
//		setFont(font);
		setOpaque(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setFont(new Font("SansSerif", Font.BOLD, 18));
		setFocusPainted(false);
		setPreferredSize(new Dimension(100, 50));
		init(colors);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (isEnabled())
				{
					setBackground(Color.decode(colors.getBackgroundHover()));
					setForeground(Color.decode(colors.getTextHover()));
					Border border = BorderFactory.createLineBorder(Color.decode(colors.getBorderHover()), 6);
					setBorder(border);
				}



			}
			@Override
			public void mouseExited(MouseEvent e) {
				init(colors);
			}
		});
	}

	public JMainButton(String text, ButtonColors colors, int width, int height) {
		this(text, colors);
		setPreferredSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
	}

	public void addActionListenerAndCommand(ActionListener listener, String actionCommand) {
		this.addActionListener(listener);
		this.setActionCommand(actionCommand);
	}

	public void init(ButtonColors colors)
	{

		if (isSelected())
		{
			setBackground(Color.decode(colors.getBackgroundHover()));
			setForeground(Color.decode(colors.getTextHover()));
			Border border = BorderFactory.createLineBorder(Color.decode(colors.getBorderHover()), 6);
			setBorder(border);
		}

		else{
			setBackground(Color.decode(colors.getBackground()));
			setForeground(Color.decode(colors.getText()));
			Border border = BorderFactory.createLineBorder(Color.decode(colors.getBorder()), 6);
			setBorder(border);
		}
	}


}
