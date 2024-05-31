package presentation.custom;

import javax.swing.*;
import java.awt.*;

public class JCustomButton extends JButton {
	public JCustomButton(String text) {
		super(text);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFocusable(false);

	}

	public JCustomButton() {
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFocusable(false);
	}
}
