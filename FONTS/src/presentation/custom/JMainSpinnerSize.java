package presentation.custom;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class JMainSpinnerSize extends JMainSpinner {
	public JMainSpinnerSize() {
		super(new SpinnerNumberModel(3, 3, 9, 1));
	}

}
