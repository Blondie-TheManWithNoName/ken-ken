package presentation.custom;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class JMainSpinner extends JSpinner {
	public JMainSpinner(SpinnerNumberModel model) {
		super(model);

		setFont(getFont().deriveFont(32f));
		JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor) getEditor();
		jsEditor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
		jsEditor.getTextField().setEnabled(false);
		jsEditor.getTextField().setDisabledTextColor(Color.decode("#375281"));
		Border border = BorderFactory.createLineBorder(Color.decode("#375281"), 5);
		jsEditor.getTextField().setBorder(border);
	}

}
