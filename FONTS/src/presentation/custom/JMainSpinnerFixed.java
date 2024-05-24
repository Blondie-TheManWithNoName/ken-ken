package presentation.custom;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class JMainSpinnerFixed extends JMainSpinner {
	public JMainSpinnerFixed() {
		super(new SpinnerNumberModel(0, 0, 10, 1));
	}

}
