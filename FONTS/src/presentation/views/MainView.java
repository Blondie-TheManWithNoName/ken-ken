package presentation.views;

import presentation.custom.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

public class MainView extends JFrame {
	protected final GridBagLayout gridbag = new GridBagLayout();
	protected final GridBagConstraints c = new GridBagConstraints();

	//private final MainMenuController controller = new MainMenuController(this);

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
		JMainSpinner spinner = new JMainSpinner(new SpinnerNumberModel(3, 3, 9, 1));
		gridbag.setConstraints(spinner, c);
		add(spinner);
	}

	public void makeSpinnerFixed() {
		JMainSpinner spinner = new JMainSpinner(new SpinnerNumberModel(0, 0, 10, 1));
		gridbag.setConstraints(spinner, c);
		add(spinner);
	}

	public void Generate3(JKenKenPanel k) {
		

		JPanel KenkenPanel = new JPanel();
		JPanel ButtonsAndKenken = new JPanel();	
		ButtonsAndKenken.setLayout(new BoxLayout(ButtonsAndKenken, BoxLayout.Y_AXIS));	
		KenkenPanel.setLayout(new BorderLayout());
		KenkenPanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 10, 20));

		KenkenPanel.add(k, BorderLayout.CENTER);
		KenkenPanel.setBackground(Color.decode("#FAFAFA"));

		JPanel Buttons = new JPanel();
		Buttons.setLayout(new BoxLayout(Buttons, BoxLayout.X_AXIS));
		
		JMainButton button = new JMainButtonSecond("REGENERATE");
		JMainButton button2 = new JMainButtonFirst("PLAY");

		Dimension buttonSize = new Dimension(150, 50); 
		button.setPreferredSize(buttonSize);
		button.setMinimumSize(buttonSize);
		button.setMaximumSize(buttonSize);

		button2.setPreferredSize(buttonSize);
		button2.setMinimumSize(buttonSize);
		button2.setMaximumSize(buttonSize);

		Buttons.add(button);
		Buttons.add(button2);

		ButtonsAndKenken.add(k);
		ButtonsAndKenken.add(Buttons);

		gridbag.setConstraints(ButtonsAndKenken, c);
		add(ButtonsAndKenken);



	}

	public void makeCheckBoxContainer(String[] sprites) {
		JPanel panel = new JPanel(new GridLayout(3, 3));
		for (int i = 0; i < 9*2; i+=2) {
			panel.add(new JMainCheckBox(sprites[i], sprites[i + 1]));
		}
		gridbag.setConstraints(panel, c);
		add(panel);
	}

	public void makeVisible() {
        setVisible(true);
    }
}
