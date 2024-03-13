package presentation.views;

import models.KenKen;
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
	}

	protected void configureLayout() {
		setLayout(new BorderLayout());
		add(kenKenPanel, BorderLayout.CENTER);
	}
}
