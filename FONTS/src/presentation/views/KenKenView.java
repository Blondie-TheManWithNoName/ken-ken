package presentation.views;

import models.kenken.KenKen;
import presentation.custom.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KenKenView extends JFrame {
	protected final KenKen kenKen;
	protected final JKenKenPanel kenKenPanel;
	protected final JLabel elapsedTimeLabel;
	private long startTime;


	public KenKenView(KenKen kenKen) {
		this.kenKen = kenKen;
		this.kenKenPanel = new JKenKenPanel(kenKen);
		this.elapsedTimeLabel = new JLabel("<html><div style='padding: 10px;'>00:00</div></html>");
		this.elapsedTimeLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		this.elapsedTimeLabel.setForeground(Color.decode("#FAFAFA"));
		this.elapsedTimeLabel.setBackground(Color.decode("#775AD8"));
		this.elapsedTimeLabel.setOpaque(true);
		this.elapsedTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		startTimer();
		startTime = System.currentTimeMillis();
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
		marginPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20)); // Margin

		JPanel marginPanelKenKen = new JPanel();
		marginPanelKenKen.setLayout(new BorderLayout());
		marginPanelKenKen.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20)); // Margin
		marginPanelKenKen.setBackground(Color.decode("#FAFAFA"));

		JPanel AIPanel = new JPanel();
		AIPanel.setBackground(Color.decode("#FAFAFA"));
		AIPanel.setLayout(new BoxLayout(AIPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for stacking buttons vertically
		AIPanel.add((Box.createHorizontalStrut(0)));
		AIPanel.add(new JSmallButton("HINT"));
		AIPanel.add(Box.createVerticalStrut(5));
		AIPanel.add(new JSmallButton("SOLVE"));

		JPanel PausedPanel = new JPanel();
		PausedPanel.setLayout(new BoxLayout(PausedPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for stacking buttons vertically
		PausedPanel.add(elapsedTimeLabel);
//		timeLabel.setText();
		PausedPanel.add(Box.createVerticalStrut(5));
		PausedPanel.add(new JSmallButton("PAUSE"));
		PausedPanel.setBackground(Color.decode("#FAFAFA"));

		marginPanelKenKen.add(kenKenPanel, BorderLayout.CENTER);
		marginPanel.add(marginPanelKenKen, BorderLayout.CENTER);
		marginPanel.setBackground(Color.decode("#FAFAFA"));
		marginPanel.add(AIPanel, BorderLayout.WEST); // Add the button panel to the south of marginPanel
		marginPanel.add(PausedPanel, BorderLayout.EAST); // Add the button panel to the south of marginPanel
		add(marginPanel, BorderLayout.CENTER);

	}

	private void startTimer() {
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				long currentTime = System.currentTimeMillis();
				long elapsedTime = currentTime - startTime;
				long elapsedSeconds = elapsedTime / 1000;
				long minutes = elapsedSeconds / 60;
				long seconds = elapsedSeconds % 60;
				elapsedTimeLabel.setText(String.format("<html><div style='padding: 10px;'>%02d:%02d</div></html>", minutes, seconds));
			}
		});
		timer.start();
	}
}
