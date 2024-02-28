package presentation.custom;

import javax.swing.*;

public class CustomJButton extends JButton {
	public CustomJButton(String text) {
		super(text);
		setFocusable(false);
	}
}
