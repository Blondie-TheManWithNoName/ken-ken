package presentation.views;

import models.kenken.KenKen;
import presentation.custom.*;

import javax.swing.*;
import java.awt.*;

public class KenKenView extends JFrame {
	protected final KenKen kenKen;
	protected final JKenKenPanel kenKenPanel;


	public KenKenView(KenKen kenKen) {
		this.kenKen = kenKen;
		this.kenKenPanel = new JKenKenPanel(kenKen);
	}

	public void start() {
		configureWindow();
		configureLayout();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void configureWindow() {
		setTitle("KenKen");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);
//		getContentPane().setBackground(Color.decode("#FAFAFA"));
		((JPanel) getContentPane()).setOpaque(true);
	}

	protected void configureLayout() {
		setLayout(new BorderLayout());

		JPanel marginPanel = new JPanel();
		marginPanel.setLayout(new BorderLayout());
		marginPanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 10, 20)); // Margin

		JPanel AIPanel = new JPanel();
		AIPanel.setLayout(new BoxLayout(AIPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for stacking buttons vertically
		AIPanel.add(new JSmallButton("HINT"));
		AIPanel.add(new JSmallButton("SOLVE"));

		JPanel PausedPanel = new JPanel();
		PausedPanel.setLayout(new BoxLayout(PausedPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for stacking buttons vertically
		PausedPanel.add(new JSmallButton("1:23"));
		PausedPanel.add(new JSmallButton("PAUSE"));

		marginPanel.add(kenKenPanel, BorderLayout.CENTER);
		marginPanel.setBackground(Color.decode("#FAFAFA"));
		marginPanel.add(AIPanel, BorderLayout.WEST); // Add the button panel to the south of marginPanel
		marginPanel.add(PausedPanel, BorderLayout.EAST); // Add the button panel to the south of marginPanel

		add(marginPanel, BorderLayout.CENTER);
	}
}
