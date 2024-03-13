package presentation.views;

import models.kenken.KenKenProposer;
import presentation.controllers.MainMenuController;
import presentation.custom.JCustomButton;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JFrame {
	private final JCustomButton proposeKenKenButton = new JCustomButton("Propose a KenKen");
	private final JCustomButton generateKenKenButton = new JCustomButton("Generate a KenKen");
	private final JCustomButton loadKenKenButton = new JCustomButton("Load a KenKen");
	private final JCustomButton loadSavedGameButton = new JCustomButton("Load a saved game");
	private final JCustomButton seeRankingButton = new JCustomButton("See ranking");
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
		setResizable(false);
	}

	private void configureLayout() {
		setLayout(new GridLayout(7, 1, 0, 0));

		proposeKenKenButton.addActionListener(controller);
		proposeKenKenButton.setActionCommand(MainMenuController.PROPOSE_KENKEN_AC);
		generateKenKenButton.addActionListener(controller);
		generateKenKenButton.setActionCommand(MainMenuController.GENERATE_KENKEN_AC);
		loadKenKenButton.addActionListener(controller);
		loadKenKenButton.setActionCommand(MainMenuController.LOAD_KENKEN_AC);
		loadSavedGameButton.addActionListener(controller);
		loadSavedGameButton.setActionCommand(MainMenuController.LOAD_SAVED_GAME_AC);
		seeRankingButton.addActionListener(controller);
		seeRankingButton.setActionCommand(MainMenuController.SEE_RANKING_AC);
		exitButton.addActionListener(controller);
		exitButton.setActionCommand(MainMenuController.EXIT_AC);

		add(proposeKenKenButton);
		add(generateKenKenButton);
		add(loadKenKenButton);
		add(loadSavedGameButton);
		add(seeRankingButton);
		add(Box.createVerticalStrut(0));
		add(exitButton);
	}
}
