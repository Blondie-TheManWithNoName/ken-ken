package presentation.views;

import presentation.custom.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class MainView extends JFrame {
	protected final GridBagLayout gridbag = new GridBagLayout();
	protected GridBagConstraints c = new GridBagConstraints();

	final JPanel KenkenPanel = new JPanel();
	//private final MainMenuController controller = new MainMenuController(this);

	public MainView() {
		configureWindow();
		setLayout(new BorderLayout());
		setFont(new Font("", Font.BOLD, 16));
		setLayout(gridbag);
		this.c.fill = GridBagConstraints.BOTH;
		this.c.weightx = 1.0;
		this.c.weighty = 1.0;
		c.insets = new Insets(3, 3, 3, 3);
		pack();
		setLocationRelativeTo(null);
		//setVisible(true);
		getContentPane().setBackground(Color.decode("#FAFAFA"));
	}

	private void configureWindow() {
		setTitle("KenKen");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(800,600));
		setResizable(true);
	}

	public void makeButtonSecond(String name, ActionListener listener, String actionCommand) {
		JMainButton button = new JMainButtonSecond(name);
		button.addActionListenerAndCommand(listener, actionCommand);
		gridbag.setConstraints(button, c);
		add(button);
	}

	public void makeButtonFirst(String name, ActionListener listener, String actionCommand) {
		JMainButton button = new JMainButtonFirst(name);
		button.addActionListenerAndCommand(listener, actionCommand);
		gridbag.setConstraints(button, c);

		add(button);
	}

	public void makeBackButton(ActionListener listener, String actionCommand) {
		JMainButton button = new JBackButton();
		button.addActionListenerAndCommand(listener, actionCommand);
		gridbag.setConstraints(button, c);

		add(button);
	}

	public void makeNextButton(ActionListener listener, String actionCommand) {
		JMainButton button = new JNextButton();
		button.addActionListenerAndCommand(listener, actionCommand);
		gridbag.setConstraints(button, c);

		add(button);
	}

	public void createPanelWithButtons(String name1, boolean first, String name2, String name3, ActionListener listener, String actionCommand1, String actionCommand2, String actionCommand3) {
		JPanel panel = new J3ButtonPanel(name1, first, name2, name3);
		Component[] components = panel.getComponents();
		JMainButton button = (JMainButton) components[0];
		button.addActionListenerAndCommand(listener, actionCommand1);
		button = (JMainButton) components[1];
		button.addActionListenerAndCommand(listener, actionCommand2);
		button = (JMainButton) components[2];
		button.addActionListenerAndCommand(listener, actionCommand3);
		gridbag.setConstraints(panel, c);
		add(panel, c);
	}

	public void createPanel2WithButtons(String name1, String name2, ActionListener listener, String actionCommand1, String actionCommand2) {
		JPanel panel = new J2ButtonPanel(name1, name2);
		Component[] components = panel.getComponents();
		JMainButton button = (JMainButton) components[0];
		button.addActionListenerAndCommand(listener, actionCommand1);
		button = (JMainButton) components[1];
		button.addActionListenerAndCommand(listener, actionCommand2);
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
		Font font =  getFont().deriveFont(Font.BOLD, 90);
		title.setFont(font);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		gridbag.setConstraints(title, c);
		add(title);
	}

	public void makeNumber(String number) {
		JLabel label = new JLabel(number);
		label.setPreferredSize(new Dimension(100, 100));
		Font font =  getFont().deriveFont(Font.BOLD, 150);
		label.setFont(font);
		Border border = BorderFactory.createLineBorder(Color.decode("#375281"), 9);
		label.setBorder(border);
		label.setForeground(Color.decode("#775AD8"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		gridbag.setConstraints(label, c);
		add(label);
	}


	public void makeSpinnerSize(ChangeListener listener) {
		JMainSpinner spinner = new JMainSpinner(new SpinnerNumberModel(3, 3, 9, 1));
		spinner.setName("sizeSpinner");
		spinner.addChangeListener(listener);
		gridbag.setConstraints(spinner, c);
		add(spinner);
	}

	public void makeSpinnerFixed(ChangeListener listener) {
		JMainSpinner spinner = new JMainSpinner(new SpinnerNumberModel(0, 0, 10, 1));
		spinner.setName("fixedSpinner");
		spinner.addChangeListener(listener);
		gridbag.setConstraints(spinner, c);
		add(spinner);
	}

	public void swapKenKen(JKenKenPanel kenken)
	{
		KenkenPanel.removeAll();
		KenkenPanel.add(kenken, BorderLayout.CENTER);
	}

	public void makeKenKenPanel(JKenKenPanel kenken) {

		KenkenPanel.setLayout(new BorderLayout());
		KenkenPanel.setBorder(BorderFactory.createEmptyBorder(10, 70, 10, 70));
		KenkenPanel.add(kenken, BorderLayout.CENTER);
		KenkenPanel.setBackground(Color.decode("#FAFAFA"));
		gridbag.setConstraints(KenkenPanel, c);
		add(KenkenPanel);
	}



	public void makeCheckBoxContainer(String[] sprites, ActionListener listener, String actionCommand) {
		JPanel panel = new JPanel(new GridLayout(3, 3));
		panel.setBackground(Color.decode("#FAFAFA"));
		for (int i = 0; i < sprites.length; i+=2) {
			JMainCheckBox checkBox = new JMainCheckBox(sprites[i], sprites[i + 1]);
			checkBox.addActionListenerAndCommand(listener, actionCommand + i/2);
			checkBox.setBackground(Color.decode("#FAFAFA"));
			panel.add(checkBox);
		}
		gridbag.setConstraints(panel, c);
		add(panel);
	}

	public void makeVisible() {
        setVisible(true);
    }
}
