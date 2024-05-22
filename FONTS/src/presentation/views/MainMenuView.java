package presentation.views;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;

import models.kenken.KenKenProposer;
import presentation.controllers.MainMenuController;
import presentation.custom.*;

public class MainMenuView extends JFrame {
//    private final JCustomButton newGameButton = new JCustomButton("New Game");
//    private final JCustomButton loadGameButton = new JCustomButton("Load Game");
//	private final JCustomButton proposeKenKenButton = new JCustomButton("Propose a KenKen");
//    private final JCustomButton generateKenKenButton = new JCustomButton("Generate a KenKen");
//    private final JCustomButton importKenKenButton = new JCustomButton("Import a KenKen");
//    private final JCustomButton exitButton = new JCustomButton("Exit");

	private final GridBagLayout gridbag = new GridBagLayout();
	private final GridBagConstraints c = new GridBagConstraints();

	private final MainMenuController controller = new MainMenuController(this);

	public void start() {
		configureWindow();
		configureLayout();
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

	private void configureLayout() {
		setLayout(new BorderLayout());
		setFont(new Font("", Font.PLAIN, 14));
		setLayout(gridbag);


		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.insets = new Insets(3, 3, 3, 3);
		makeSquare("");
		makeSquare("");
		makeSquare("");
		makeSquare("");
		c.gridy = 1;
		makeBackButton();
		makebutton2("NEW");
		c.gridheight = 2;
		makeSquare("<html><p style='margin-bottom: -7;'>GA</p><p style='margin-top: -7;'>ME</p></html>");
		makeSquare("");

		c.gridy = 2;
		c.gridx = 0;
		c.gridheight = 3;
		makeSquare("");;
		c.gridx = 1;
		c.gridheight = 1;		;
		makebutton("LOAD");

		c.gridy = 3;
		c.gridx = 1;
		c.gridheight = 2;
		makeSquare("<html><p style='margin-bottom: -7;'>KEN</p><p style='margin-top: -7;'>KEN</p></html>");
		c.gridx = 2;
//		makelabel("", gridbag, c);
		createPanelWithButtons("");
		c.gridx = 3;
		c.gridheight = 1;
		makeSquare("");

		c.gridy = 4;
		c.gridx = 3;
		c.gridheight = 1;
		makeSquare("");

		c.gridheight = 1;
		c.gridy = 5;
		c.gridx = 0;
		makeSquare("");
		c.gridx = 1;
		makeSquare("");
		c.gridx = 2;
		makeSquare("");
		c.gridx = 3;
		makeSquare("");
	}

	protected void makebutton(String name) {
		JMainButton button = new JMainButtonSecond(name);
		gridbag.setConstraints(button, c);
		add(button);
	}

	protected void makebutton2(String name) {
		JMainButton button = new JMainButtonFirst(name);
		gridbag.setConstraints(button, c);

		add(button);
	}

	protected void makeBackButton() {
		JMainButton button = new JBackButton();
		gridbag.setConstraints(button, c);

		add(button);
	}


	protected void createPanelWithButtons(String name) {
		JPanel panel = new J3ButtonPanel("PROPOSE", "GENERATE", "IMPORT");

//		panel.add(button("PROPOSE", false, 50, 25));
//		panel.add(button("GENERATE", false, 50, 25));
//		panel.add(button("IMPORT", false, 50, 23));

		gridbag.setConstraints(panel, c);
		add(panel, c);
	}

	protected void makeSquare(String name) {
		JSquareLabel label;
		if (name.equals("")) {
			label = new JSquareLabel();
		} else {
			label = new JSquareLabel(name);
		}
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
