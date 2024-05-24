package presentation.views;

import presentation.custom.*;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
	protected final GridBagLayout gridbag = new GridBagLayout();
	protected final GridBagConstraints c = new GridBagConstraints();

//	private final MainMenuController controller = new MainMenuController(this);

	public MainView() {
		configureWindow();
		setLayout(new BorderLayout());
		setFont(new Font("", Font.PLAIN, 14));
		setLayout(gridbag);
		this.c.fill = GridBagConstraints.BOTH;
		this.c.weightx = 1.0;
		this.c.weighty = 1.0;
		c.insets = new Insets(3, 3, 3, 3);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		getContentPane().setBackground(Color.decode("#FAFAFA"));
	}

	private void configureWindow() {
		setTitle("KenKen");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(720,540));
		setResizable(true);
	}

	public void makeButtonSecond(String name) {
		JMainButton button = new JMainButtonSecond(name);
		gridbag.setConstraints(button, c);
		add(button);
	}

	public void makeButtonFirst(String name) {
		JMainButton button = new JMainButtonFirst(name);
		gridbag.setConstraints(button, c);

		add(button);
	}

	public void makeBackButton() {
		JMainButton button = new JBackButton();
		gridbag.setConstraints(button, c);
		add(button);
	}

	public void makeNextButton() {
		JMainButton button = new JNextButton();
		gridbag.setConstraints(button, c);

		add(button);
	}

	public void createPanelWithButtons(String name) {
		JPanel panel = new J3ButtonPanel("PROPOSE", "GENERATE", "IMPORT");

		gridbag.setConstraints(panel, c);
		add(panel, c);
	}

	public void makeSquare(String name) {
		JSquareLabel label;
		if (name.equals("")) {
			label = new JSquareLabel();
		} else {
			label = new JSquareLabel(name);
		}
		gridbag.setConstraints(label, c);
		add(label);
	}

	public void makeSpinnerSize() {
		JMainSpinnerSize spinner = new JMainSpinnerSize();
		gridbag.setConstraints(spinner, c);
		add(spinner);
	}

	public void makeSpinnerFixed() {
		JMainSpinnerFixed spinner = new JMainSpinnerFixed();
		gridbag.setConstraints(spinner, c);
		add(spinner);
	}
}
