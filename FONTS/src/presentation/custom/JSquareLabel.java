package presentation.custom;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class JSquareLabel extends JLabel {
	public JSquareLabel() {
		setPreferredSize(new Dimension(100, 50));
		setBackground(Color.decode("#FAFAFA"));
		Border border = BorderFactory.createLineBorder(Color.decode("#D3D8E2"), 9);
		setBorder(border);
	}

		public JSquareLabel(String text) {
		super(text);
		setPreferredSize(new Dimension(100, 30));
		Font font =  getFont().deriveFont(Font.BOLD, 42);
		setFont(font);
		setBackground(Color.decode("#775AD8"));
		setOpaque(true);
		setForeground(Color.decode("#FAFAFA"));
		setHorizontalAlignment(SwingConstants.CENTER);
	}


}
