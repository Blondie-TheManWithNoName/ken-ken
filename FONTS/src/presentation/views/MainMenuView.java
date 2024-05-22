package presentation.views;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;

import models.kenken.KenKenProposer;
import presentation.controllers.MainMenuController;
import presentation.custom.JCustomButton;
import presentation.custom.JMainButton;
import presentation.custom.JMainButtonSecond;
import presentation.custom.JMainButtonFirst;
import presentation.custom.JSquareLabel;

public class MainMenuView extends JFrame {
//    private final JCustomButton newGameButton = new JCustomButton("New Game");
//    private final JCustomButton loadGameButton = new JCustomButton("Load Game");
//	private final JCustomButton proposeKenKenButton = new JCustomButton("Propose a KenKen");
//    private final JCustomButton generateKenKenButton = new JCustomButton("Generate a KenKen");
//    private final JCustomButton importKenKenButton = new JCustomButton("Import a KenKen");
//    private final JCustomButton exitButton = new JCustomButton("Exit");

	private final MainMenuController controller = new MainMenuController(this);

	public void start() {
		configureWindow();
		configureLayout();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		getContentPane().setBackground(Color.decode("#FAFAFA"));
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
		setPreferredSize(new Dimension(720,540));
		setResizable(true);
	}

	private void configureLayout() {
		setLayout(new BorderLayout());
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		setFont(new Font("", Font.PLAIN, 14));
		setLayout(gridbag);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.insets = new Insets(3, 3, 3, 3);
		makelabel("", gridbag, c);
		makelabel("", gridbag, c);
		makelabel("", gridbag, c);
		makelabel("", gridbag, c);
		c.gridy = 1;
		makebutton("<html><span style='font-family: Arial Unicode MS; font-size: 24;'>\u2B9C </span> BACK </html>", gridbag, c);
		makebutton2("NEW", gridbag, c);
		c.gridheight = 2;
		makeSquare("<html><p style='margin-bottom: -7;'>GA</p><p style='margin-top: -7;'>ME</p></html>", gridbag, c);
		makelabel("", gridbag, c);

		c.gridy = 2;
		c.gridx = 0;
		c.gridheight = 3;
		makelabel("", gridbag, c);;
		c.gridx = 1;
		c.gridheight = 1;		;
		makebutton("LOAD", gridbag, c);

		c.gridy = 3;
		c.gridx = 1;
		c.gridheight = 2;
		makeSquare("<html><p style='margin-bottom: -7;'>KEN</p><p style='margin-top: -7;'>KEN</p></html>", gridbag, c);
		c.gridx = 2;
//		makelabel("", gridbag, c);
		createPanelWithButtons("", gridbag, c);
		c.gridx = 3;
		c.gridheight = 1;
		makelabel("", gridbag, c);

		c.gridy = 4;
		c.gridx = 3;
		c.gridheight = 1;
		makelabel("", gridbag, c);

		c.gridheight = 1;
		c.gridy = 5;
		c.gridx = 0;
		makelabel("", gridbag, c);
		c.gridx = 1;
		makelabel("", gridbag, c);
		c.gridx = 2;
		makelabel("", gridbag, c);
		c.gridx = 3;
		makelabel("", gridbag, c);


	}

	protected void makebutton(String name, GridBagLayout gridbag, GridBagConstraints c) {
		JMainButton button = new JMainButtonSecond(name);
		gridbag.setConstraints(button, c);
		add(button);
	}

	protected void makebutton2(String name,
							  GridBagLayout gridbag,
							  GridBagConstraints c) {
		JMainButton button = new JMainButtonFirst(name);
		gridbag.setConstraints(button, c);

		add(button);
	}

	protected void makelabel(String name,
							  GridBagLayout gridbag,
							  GridBagConstraints c) {
		JSquareLabel label = new JSquareLabel();
		gridbag.setConstraints(label, c);
		add(label);
	}

	protected void createPanelWithButtons(String name,
											   GridBagLayout gridbag,
											   GridBagConstraints c) {
		JPanel panel = new JPanel();
		GridLayout grid = new GridLayout(3, 1, 0, 15);
		panel.setLayout(grid);

//		panel.add(button("PROPOSE", false, 50, 25));
//		panel.add(button("GENERATE", false, 50, 25));
//		panel.add(button("IMPORT", false, 50, 23));

		gridbag.setConstraints(panel, c);
		add(panel, c);
	}

	protected void makeSquare(String name,
							 GridBagLayout gridbag,
							 GridBagConstraints c) {
		JSquareLabel label = new JSquareLabel(name);
		gridbag.setConstraints(label, c);
		add(label);
	}

	private Font loadFont(String fontFileName) {
		try {
			// Load the font file
			File fontFile = new File(fontFileName);
			return Font.createFont(Font.TRUETYPE_FONT, fontFile);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
			return null;
		}
	}

}
