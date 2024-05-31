package presentation.views;

import exceptions.*;
import models.kenken.KenKen;
import models.kenken.KenKenSolver;
import presentation.controllers.KenKenPlayController;
import presentation.custom.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KenKenPlayView extends JFrame {
	private final JSetCellValue setCellValue;
//	private final JCustomButton checkButton = new JCustomButton("Check");
	private final KenKenSolver solver;
	private final KenKenPlayController controller = new KenKenPlayController(this);
	protected final KenKen kenKen;
	protected final JKenKenPanel kenKenPanel;
	protected final JLabel elapsedTimeLabel;
	private long startTime;

	protected final  JMainButton solve = new JSmallButton("SOLVE");
	protected final  JMainButton hint = new JSmallButton("HINT");
	private JKenKenCell selected;

	public KenKenPlayView(KenKen kenKen) {
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
		this.solver = new KenKenSolver(kenKen);
		Runnable runnable = () -> {
			System.out.println("Thread is running");
			solver.solve();
			enableAI();
		};
		Thread thread = new Thread(runnable);
		thread.start();

		this.setCellValue = new JSetCellValue(kenKen.getSize(), controller);
		this.addKeyListener(controller);
		setFocusable(true);
		requestFocusInWindow();
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

	private void enableAI() {
		solve.setEnabled(true);
		hint.setEnabled(true);
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

		hint.addActionListenerAndCommand(controller, KenKenPlayController.HINT_KENKEN_AC);
		AIPanel.add(hint);
		AIPanel.add(Box.createVerticalStrut(5));

		solve.addActionListenerAndCommand(controller, KenKenPlayController.SOLVE_KENKEN_AC);
		AIPanel.add(solve);
		solve.setEnabled(false);
		hint.setEnabled(false);



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

		for (int i = 0; i < kenKen.getSize(); i++)
			for (int j = 0; j < kenKen.getSize(); j++)
				if (!kenKen.isFixed(i, j))
					kenKenPanel.addController(i, j, controller, KenKenPlayController.SELECT_CELL_AC + i + "_" + j);
		add(setCellValue, BorderLayout.NORTH);

		add(marginPanel, BorderLayout.CENTER);

	}
	public void selectCell(int row, int col) {
		if (selected != null)
			selected.deselect();
		selected = kenKenPanel.getCell(row, col);
		selected.select();
		setCellValue.enableAllBut(kenKen.getValue(row, col));
	}

	public void setValue(int value) {
		if (selected != null) {
			try {
				if (value == 0)
					kenKen.erasePosition(selected.getRow(), selected.getCol());
				else
					kenKen.setPosition(selected.getRow(), selected.getCol(), value);
				selected.setValue(value, true);
				setCellValue.enableAllBut(value);
			} catch (EraseFixedValueException | ValueOutOfBoundsException ignored) {
			} catch (RewriteFixedPositionException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void check() {
		if (selected != null) {
			selected.deselect();
			selected = null;
			setCellValue.disableAll();
		}
		if (!kenKen.isFull()) {
			JOptionPane.showMessageDialog(this, "You must fill all the cells before checking the KenKen!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			if (kenKen.check())
				JOptionPane.showMessageDialog(this, "Congratulations, you solved the KenKen!", "Success", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog(this, "Oops... continue trying!", "Failure", JOptionPane.ERROR_MESSAGE);
		} catch (OperandsDoNotMatchException | NonIntegerResultException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void solve() {
		startTime = startTime - 2000000;
		for (int row = 0; row < kenKen.getSize(); row++)
			for (int col = 0; col < kenKen.getSize(); col++)
				kenKenPanel.setValue(row, col, kenKen.boardSolved[row][col], false);
	}

	public void hint() throws RewriteFixedPositionException, ValueOutOfBoundsException {
		startTime = startTime - 60000;
		if (!kenKen.isFull()){
			System.out.println("ASDSAD");
			int randomRow = (int) (Math.random() * ((kenKen.getSize() - 1) + 1));
			int randomCol = (int) (Math.random() * ((kenKen.getSize() - 1) + 1));

			while (kenKen.isFixed(randomRow, randomCol) || !kenKen.getCell(randomRow, randomCol).isEmpty())
			{
				++randomRow;
				if (randomRow >= kenKen.getSize())
				{
					randomRow = 0;
					++randomCol;
				}
				if (randomCol >= kenKen.getSize()) {
					randomCol = 0;
				}
			}
			kenKenPanel.setValue(randomRow, randomCol, kenKen.boardSolved[randomRow][randomCol], true);
			kenKen.setPosition(randomRow, randomCol, kenKen.boardSolved[randomRow][randomCol]);
			selectCell(randomRow, randomCol);
		}
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
