package presentation.views;

import presentation.controllers.ChooseController;
import presentation.custom.JCustomButton;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import static java.util.stream.StreamSupport.stream;

public class ChooseView extends JFrame {

	private final ChooseController controller = new ChooseController(this);

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
//		c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last in row
//		"Arial Unicode MS", Font.PLAIN, 32)
		makebutton("<html><span style='font-family: Arial Unicode MS; font-size: 24;'>\u2B9C </span> BACK </html>", gridbag, c);
		c.gridheight = 2;
		makeSquare("<html><p style='margin-bottom: -7;'>S I</p><p style='margin-top: -7;'>ZE</p></html>", gridbag, c);
		c.gridheight = 1;
		makespinner("NEW", gridbag, c);
		c.gridheight = 2;
		makelabel("", gridbag, c);

		c.gridy = 2;
		c.gridx = 0;
		c.gridheight = 3;
		makelabel("", gridbag, c);;
		c.gridx = 2;
		c.gridheight = 1;		;
		makebutton("PLAY", gridbag, c);

		c.gridy = 3;
		c.gridx = 1;
		c.gridheight = 2;
		makelabel("", gridbag, c);;
		c.gridx = 2;
		makelabel("", gridbag, c);
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
//		Font font = new Font("Spartan", Font.BOLD, 20);
		Font font = loadFont("fonts/Spartan-Bold.ttf").deriveFont(Font.BOLD, 16);
		JButton button = new JButton(name);
		button.setForeground(Color.decode("#375281"));
		button.setBackground(Color.decode("#FAFAFA"));
		button.setOpaque(true);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setFont(font);
		button.setFocusPainted(false);
		button.setPreferredSize(new Dimension(100, 50));
		gridbag.setConstraints(button, c);
		Border border = BorderFactory.createLineBorder(Color.decode("#375281"), 5);
		button.setBorder(border);
		button.addMouseListener(new MouseAdapter() {
			// Change background color when mouse enters
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setBackground(Color.decode("#375281"));
				button.setForeground(Color.decode("#FAFAFA"));

			}

			// Restore original background color when mouse exits
			@Override
			public void mouseExited(MouseEvent e) {
				button.setBackground(Color.decode("#FAFAFA"));
				button.setForeground(Color.decode("#375281"));

			}
		});

		add(button);
	}


	protected JButton button(String name, boolean first, int w, int h)
	{
		Font font = loadFont("fonts/Spartan-Bold.ttf").deriveFont(Font.BOLD, 16);
		JButton button = new JButton(name);
		if (first)
		{
			button.setForeground(Color.decode("#FAFAFA"));
			button.setBackground(Color.decode("#375281"));
			Border border = BorderFactory.createLineBorder(Color.decode("#375281"), 5);
			button.setBorder(border);
		}
		else
		{
			button.setForeground(Color.decode("#375281"));
			button.setBackground(Color.decode("#FAFAFA"));
			Border border = BorderFactory.createLineBorder(Color.decode("#375281"), 5);
			button.setBorder(border);
		}
		button.setOpaque(true);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setFont(font);
		button.setFocusPainted(false);
		button.setPreferredSize(new Dimension(w, h));

		button.addMouseListener(new MouseAdapter() {
			// Change background color when mouse enters
			@Override
			public void mouseEntered(MouseEvent e) {
				if (first)
				{

					button.setBackground(Color.decode("#775AD8"));
					button.setForeground(Color.decode("#FAFAFA"));
					Border border = BorderFactory.createLineBorder(Color.decode("#775AD8"), 5);
					button.setBorder(border);
				}
				else {
					button.setBackground(Color.decode("#375281"));
					button.setForeground(Color.decode("#FAFAFA"));
				}
			}

			// Restore original background color when mouse exits
			@Override
			public void mouseExited(MouseEvent e) {
				if (first)
				{

					button.setBackground(Color.decode("#375281"));
					button.setForeground(Color.decode("#FAFAFA"));
					Border border = BorderFactory.createLineBorder(Color.decode("#375281"), 5);
					button.setBorder(border);
				}
				else
				{
					button.setBackground(Color.decode("#FAFAFA"));
					button.setForeground(Color.decode("#375281"));
				}
			}
		});

		return button;
	}

	protected void makespinner(String name,
							   GridBagLayout gridbag,
							   GridBagConstraints c) {



		JSpinner spinner = new JSpinner(new SpinnerNumberModel());
		spinner.setFont(spinner.getFont().deriveFont(32f));
		JSpinner.NumberEditor jsEditor = (JSpinner.NumberEditor)
				spinner.getEditor();
		jsEditor.getTextField().setBackground(Color.red);
		jsEditor.getTextField().setHorizontalAlignment(SwingConstants.CENTER);

		gridbag.setConstraints(spinner, c);

		add(spinner);
	}

	protected void makelabel(String name,
							  GridBagLayout gridbag,
							  GridBagConstraints c) {
		JLabel label = new JLabel(name);
		label.setPreferredSize(new Dimension(100, 50));
		label.setBackground(Color.decode("#FAFAFA"));

		Border border = BorderFactory.createLineBorder(Color.decode("#D3D8E2"), 9);
		label.setBorder(border);
		gridbag.setConstraints(label, c);
		add(label);
	}

	protected void createPanelWithButtons(String name,
											   GridBagLayout gridbag,
											   GridBagConstraints c) {
		JPanel panel = new JPanel();
		GridLayout grid = new GridLayout(3, 1, 0, 15);
		panel.setLayout(grid);

		panel.add(button("PROPOSE", false, 50, 25));
		panel.add(button("GENERATE", false, 50, 25));
		panel.add(button("IMPORT", false, 50, 23));

		gridbag.setConstraints(panel, c);
		add(panel, c);
	}

	protected void makeSquare(String name,
							 GridBagLayout gridbag,
							 GridBagConstraints c) {
		JLabel label = new JLabel(name);
		label.setPreferredSize(new Dimension(100, 30));
		Font font = loadFont("fonts/Spartan-Bold.ttf").deriveFont(Font.BOLD, 42);
		label.setFont(font);
		label.setBackground(Color.decode("#775AD8"));
		label.setOpaque(true);
		label.setForeground(Color.decode("#FAFAFA"));
		label.setHorizontalAlignment(SwingConstants.CENTER);
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
