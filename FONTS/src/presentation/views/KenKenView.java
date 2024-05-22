package presentation.views;

import models.kenken.KenKen;
import presentation.custom.JKenKenPanel;

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
		setResizable(false);
//		getContentPane().setBackground(Color.decode("#FAFAFA"));
		((JPanel) getContentPane()).setOpaque(true);
	}

	protected void configureLayout() {
		setLayout(new BorderLayout());

		JPanel marginPanel = new JPanel();
		marginPanel.setLayout(new BorderLayout());
		marginPanel.setBorder(BorderFactory.createEmptyBorder(50, 20, 10, 20)); // Margin
		marginPanel.add(kenKenPanel, BorderLayout.CENTER);
		marginPanel.setBackground(Color.decode("#FAFAFA"));

		add(marginPanel, BorderLayout.CENTER);
	}
}
