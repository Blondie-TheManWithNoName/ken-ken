package presentation.views;

import exceptions.*;
import models.Group;
import models.KenKen;
import models.operations.Operation;
import models.operations.OperationFactory;
import presentation.ProposeKenKenTool;
import presentation.controllers.ProposeKenKenController;
import presentation.custom.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.IntStream;

public class ProposeKenKenView extends JFrame {
	protected final static int MAX_SIZE = 15;

	private final MainMenuView mainMenuView;
	private final int size;
	private final KenKen kenKen;
	private final Map<Group, Color> groups = new HashMap<>();
	private final Map<JKenKenCell, Group> cells = new HashMap<>();

	private final JProposeKenKenToolBar toolBar;

	private final JKenKenPanel kenKenPanel;
	private final JCustomButton cancelButton = new JCustomButton("Cancel");
	private final JCustomButton continueButton = new JCustomButton("Continue");

	private final ProposeKenKenController controller = new ProposeKenKenController(this);

	private ProposeKenKenTool activeTool;
	private Group selectedGroup;

	public ProposeKenKenView(MainMenuView mainMenuView, int size) {
		this.mainMenuView = mainMenuView;
		this.size = size;
		this.kenKen = new KenKen(size);
		this.toolBar = new JProposeKenKenToolBar(controller);
		this.kenKenPanel = new JKenKenPanel(kenKen);
	}

	public void start() {
		configureWindow();
		configureLayout();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setTool(JToolBarItem toolBarItem) {
		if (activeTool == toolBarItem.getTool()) {
			activeTool = null;
			toolBar.unsetActiveAll();
			setCursor(Cursor.getDefaultCursor());
			return;
		}
		if (toolBarItem.getTool() == ProposeKenKenTool.ADD_TO_GROUP) {
			if (groups.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please create a group first", "No groups", JOptionPane.ERROR_MESSAGE);
				return;
			}
			selectedGroup = askGroup();
			if (selectedGroup == null)
				return;
		}
		if (toolBarItem.getTool() == ProposeKenKenTool.CREATE_GROUP) {
			askOperation();
		} else if (toolBarItem.getTool() == ProposeKenKenTool.DELETE_GROUP) {
			if (groups.isEmpty()) {
				JOptionPane.showMessageDialog(this, "There are no groups to delete", "No groups", JOptionPane.ERROR_MESSAGE);
				return;
			}
			Group group = askGroup();
			if (group == null)
				return;
			if (JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this group? This action cannot be undone.", "Delete group", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
				deleteGroup(group);
		} else {
			if (toolBarItem.getTool() == ProposeKenKenTool.REMOVE_FROM_GROUP) {
				if (cells.isEmpty()) {
					JOptionPane.showMessageDialog(this, "There are no cells to remove from any group", "No cells", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			toolBar.unsetActiveAll();
			activeTool = toolBarItem.getTool();
			toolBarItem.setActive();
			setCursor(toolBarItem.getToolCursor());
		}
	}

	public void useToolWithCell(int row, int col) {
		if (activeTool == null) {
			JOptionPane.showMessageDialog(this, "Please select a tool first", "No tool selected", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (activeTool == ProposeKenKenTool.SET_FIXED_VALUE) {
			while (true) {
				String value = JOptionPane.showInputDialog(this, "Enter the value for the cell (leave blank to clear)", "Set fixed value", JOptionPane.QUESTION_MESSAGE);
				if (value == null)
					return;
				if (value.isEmpty()) {
					clearValue(row, col);
					return;
				}
				try {
					setFixedValue(row, col, Integer.parseInt(value));
					break;
				} catch (NumberFormatException | ValueOutOfBoundsException e) {
					JOptionPane.showMessageDialog(this, "Please enter a valid number", "Invalid number", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (activeTool == ProposeKenKenTool.ADD_TO_GROUP) {
			if (selectedGroup == null) {
				JOptionPane.showMessageDialog(this, "Please select a group first", "No group selected", JOptionPane.ERROR_MESSAGE);
				return;
			}
			cells.put(kenKenPanel.getCell(row, col), selectedGroup);
			kenKenPanel.getCell(row, col).addToGroup(selectedGroup, groups.get(selectedGroup));
		} else if (activeTool == ProposeKenKenTool.REMOVE_FROM_GROUP) {
			cells.remove(kenKenPanel.getCell(row, col));
			kenKenPanel.getCell(row, col).removeFromGroup();
		}
	}

	public void generateGroups() {
		if (cells.size() < size * size) {
			JOptionPane.showMessageDialog(this, "There are cells without a group", "Incomplete groups", JOptionPane.ERROR_MESSAGE);
			return;
		}
		for (Group group : groups.keySet()) {
			boolean hasCells = false;
			for (JKenKenCell cell : cells.keySet()) {
				if (cells.get(cell) == group) {
					hasCells = true;
					break;
				}
			}
			if (!hasCells)
				deleteGroup(group);
		}
		for (Group group : groups.keySet()) {
			kenKen.addGroup(group.getOperation());
			for (JKenKenCell cell : cells.keySet()) {
				if (cells.get(cell) == group) {
					try {
						kenKen.addCellToLastGrop(cell.getRow(), cell.getCol());
					} catch (GroupCellsNotContiguousException | CellAlreadyInGroupException ignored) {
					} catch (TooManyOperandsException e) {
						kenKen.clearGroups();
						JOptionPane.showMessageDialog(this, "A group has too many cells", "Too many cells", JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		}
		// TODO: checkAllCellsHaveGroup();
		// TODO: do something with the KenKen
		JOptionPane.showMessageDialog(this, "KenKen generated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
	}

	public void exit() {
		if (JOptionPane.showConfirmDialog(this, "Are you sure you want to exit? All progress will be lost", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
			dispose();
	}

	@Override
	public void dispose() {
		mainMenuView.setVisible(true);
		super.dispose();
	}

	private void configureWindow() {
		setTitle("Propose a KenKen");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
	}

	private void configureLayout() {
		setLayout(new BorderLayout());

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				kenKenPanel.addController(i, j, controller, ProposeKenKenController.CELL_CLICKED_AC + i + "_" + j);

		JPanel buttonsPanel = new JPanel(new GridLayout(2, 1));
		continueButton.addActionListener(controller);
		continueButton.setActionCommand(ProposeKenKenController.CONTINUE_AC);
		buttonsPanel.add(continueButton);
		cancelButton.addActionListener(controller);
		cancelButton.setActionCommand(ProposeKenKenController.CANCEL_AC);
		buttonsPanel.add(cancelButton);

		add(toolBar, BorderLayout.NORTH);
		add(kenKenPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
	}

	private void setFixedValue(int row, int col, int value) throws ValueOutOfBoundsException {
		kenKen.setFixedPosition(row, col, value);
		kenKenPanel.setFixedValue(row, col, value);
	}

	private void clearValue(int row, int col) {
		kenKen.clearValue(row, col);
		kenKenPanel.clearValue(row, col);
	}

	private void askOperation() {
		JPanel askOperationPane = new JPanel(new GridLayout(2, 2));
		JComboBox<String> comboBox = new JComboBox<>(new String[]{"+", "-", "*", "/", "gcd", "lcm", "^", "="});
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));

		askOperationPane.add(new JLabel("Select the operation:"));
		askOperationPane.add(comboBox);
		askOperationPane.add(new JLabel("Enter its target:"));
		askOperationPane.add(spinner);

		if (JOptionPane.showConfirmDialog(this, askOperationPane, "Custom JOptionPane",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
			String selectedOption = (String) comboBox.getSelectedItem();
			int intValue = (int) spinner.getValue();
			try {
				createGroup(OperationFactory.createOperation(selectedOption, intValue));
			} catch (CannotCreateOperationException ignored) {
				JOptionPane.showMessageDialog(this, "An error occurred while creating the operation", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void createGroup(Operation operation) {
		groups.put(new Group(operation), createRandomColor());
	}

	private Color createRandomColor() {
		// FIXME: create different colors (always same colors generated)
		List<Color> colors = new ArrayList<>(groups.values());
		return new Color((int) (Math.random() * 0x1000000));
	}

	private Group askGroup() {
		JPanel askGroupPane = new JPanel(new GridLayout(1, 2));
		JComboBox<String> comboBox = new JComboBox<>(IntStream.range(0, groups.size()).mapToObj(i -> String.format("%d) %s", i+1, ((Group) groups.keySet().toArray()[i]).getNotation())).toArray(String[]::new));

		askGroupPane.add(new JLabel("Select the group:"));
		askGroupPane.add(comboBox);

		if (JOptionPane.showConfirmDialog(this, askGroupPane, "Select a group",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
			int selectedIndex = comboBox.getSelectedIndex();
			return (Group) groups.keySet().toArray()[selectedIndex];
		}
		return null;
	}

	private void deleteGroup(Group group) {
		groups.remove(group);
		List<JKenKenCell> toRemove = new ArrayList<>();
		for (JKenKenCell cell : cells.keySet()) {
			if (cells.get(cell) == group) {
				toRemove.add(cell);
				cell.removeFromGroup();
			}
		}
		toRemove.forEach(cells::remove);
		if (selectedGroup == group)
			selectedGroup = null;
	}
}
