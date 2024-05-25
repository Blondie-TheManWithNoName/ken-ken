package presentation.views;

import presentation.custom.*;

import javax.swing.*;
import javax.swing.border.Border;
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

	public void createPanelWithButtons(String text1, String text2, String text3) {
		JPanel panel = new J3ButtonPanel(text1, text2, text3);

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

	public void makeTitle() {
		JLabel title = new JLabel("<html><p style='margin-bottom: -20; color: #375281'>KEN</p><p style='margin-top: -20;  color: #775AD8'>KEN</p></html>");
		title.setPreferredSize(new Dimension(100, 100));
		Font font =  getFont().deriveFont(Font.BOLD, 80);
		title.setFont(font);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		gridbag.setConstraints(title, c);
		add(title);
	}

	public void makeNumber(String number) {
		JLabel label = new JLabel(number);
		label.setPreferredSize(new Dimension(100, 100));
		Font font =  getFont().deriveFont(Font.BOLD, label.getPreferredSize().height);
		label.setFont(font);
		Border border = BorderFactory.createLineBorder(Color.decode("#375281"), 5);
		label.setBorder(border);
		label.setForeground(Color.decode("#775AD8"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
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
