package presentation.views;

import java.awt.*;

import javax.swing.*;

import models.kenken.KenKenProposer;
import presentation.controllers.MainMenuController;
import presentation.custom.JCustomButton;

public class MainMenuView extends JFrame {
    private final JCustomButton newGameButton = new JCustomButton("New Game");
    private final JCustomButton loadGameButton = new JCustomButton("Load Game");
	private final JCustomButton proposeKenKenButton = new JCustomButton("Propose a KenKen");
    private final JCustomButton generateKenKenButton = new JCustomButton("Generate a KenKen");
    private final JCustomButton importKenKenButton = new JCustomButton("Import a KenKen");
    private final JCustomButton exitButton = new JCustomButton("Exit");

	private final MainMenuController controller = new MainMenuController(this);

	public void start() {
		configureWindow();
		configureLayout();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void proposeKenKen() {
		int size;
		while (true) {
			String input = JOptionPane.showInputDialog("Enter the size of the KenKen:");
			if (input == null)
				return;
			try {
				size = Integer.parseInt(input);
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "The size must be a number.", "Invalid size", JOptionPane.ERROR_MESSAGE);
				continue;
			}
			if (size >= 2 && size <= KenKenProposer.MAX_SIZE)
				break;
			JOptionPane.showMessageDialog(this, "The size must be between 2 and " + KenKenProposer.MAX_SIZE + " (inclusive).", "Invalid size", JOptionPane.ERROR_MESSAGE);
		}

		new ProposeKenKenView(this, size).start();
		setVisible(false);
	}

	private void configureWindow() {
		setTitle("KenKen");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 300);
		setResizable(true);
	}

	private void configureLayout() {
		setLayout(new BorderLayout());

		JPanel backPanel = new JPanel(new GridLayout(3, 3, 2, 2));
		JPanel mainPanel = new JPanel(new GridLayout(2, 2, 2, 2));
		JPanel buttonPanel1 = new JPanel(new GridLayout(2, 1, 2, 2));
		JPanel buttonPanel2 = new JPanel(new GridLayout(3, 1, 2, 2));
		JLabel game = new JLabel("GAME");
		JLabel kenKen = new JLabel("KENKEN");


		mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		Dimension default1 = new Dimension(120,25);
		Dimension default2 = new Dimension(120,25);

		proposeKenKenButton.setPreferredSize(default1);
        proposeKenKenButton.setMinimumSize(default1);
		proposeKenKenButton.addActionListener(controller);
		proposeKenKenButton.setActionCommand(MainMenuController.PROPOSE_KENKEN_AC);
		generateKenKenButton.setPreferredSize(default1);
        generateKenKenButton.setMinimumSize(default1);
		generateKenKenButton.addActionListener(controller);
		generateKenKenButton.setActionCommand(MainMenuController.GENERATE_KENKEN_AC);
		importKenKenButton.setPreferredSize(default1);
        importKenKenButton.setMinimumSize(default1);
		importKenKenButton.addActionListener(controller);
		importKenKenButton.setActionCommand(MainMenuController.LOAD_KENKEN_AC);
		newGameButton.setPreferredSize(default1);
        newGameButton.setMinimumSize(default1);
		newGameButton.addActionListener(controller);
		newGameButton.setActionCommand(MainMenuController.LOAD_SAVED_GAME_AC);
		loadGameButton.setPreferredSize(default1);
        loadGameButton.setMinimumSize(default1);
		loadGameButton.addActionListener(controller);
		loadGameButton.setActionCommand(MainMenuController.SEE_RANKING_AC);
		exitButton.setPreferredSize(default1);
        exitButton.setMinimumSize(default1);
		exitButton.addActionListener(controller);
		exitButton.setActionCommand(MainMenuController.EXIT_AC);

		buttonPanel1.add(newGameButton);
		buttonPanel1.add(loadGameButton);
		buttonPanel2.add(proposeKenKenButton);
		buttonPanel2.add(generateKenKenButton);
		buttonPanel2.add(importKenKenButton);

	
		mainPanel.add(buttonPanel1);	
		mainPanel.add(game);
		mainPanel.add(kenKen);
		mainPanel.add(buttonPanel2);

		backPanel.add(exitButton);
		backPanel.add(new JLabel());
		backPanel.add(new JLabel());
		backPanel.add(new JLabel());
		backPanel.add(mainPanel);
		backPanel.add(new JLabel());
		backPanel.add(new JLabel());
		backPanel.add(new JLabel());
		backPanel.add(new JLabel());

		add(backPanel, BorderLayout.CENTER);
	}
}
