package presentation.custom;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

public class JIntegerField extends JFormattedTextField {
	public JIntegerField() {
		NumberFormatter formatter = new NumberFormatter();
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(1);
		formatter.setAllowsInvalid(false);
		formatter.setCommitsOnValidEdit(true);
		setFormatter(formatter);
	}

	@Override
	public Integer getValue() {
		try {
			return Integer.parseInt(getText());
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}
