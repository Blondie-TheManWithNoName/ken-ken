package presentation.views;

import exceptions.*;
import models.kenken.Group;
import models.kenken.KenKenProposer;
import models.operations.OperationFactory;
import presentation.ProposeKenKenTool;
import presentation.controllers.ProposeKenKenController;
import presentation.custom.*;

import javax.swing.*;
import java.awt.*;

public class ProposeKenKenView extends JFrame {
	private final MainMenuView mainMenuView;
	private final KenKenProposer kenKenProposer;
	private final JProposeKenKenToolBar toolBar;
	private final JKenKenPanel kenKenPanel;

	private final JCustomButton cancelButton = new JCustomButton("Cancel");
	private final JCustomButton continueButton = new JCustomButton("Continue");

	private final ProposeKenKenController controller = new ProposeKenKenController(this);

	private ProposeKenKenTool activeTool;
	private Group selectedGroup;

	public ProposeKenKenView(MainMenuView mainMenuView, int size) {
		this.kenKenProposer = new KenKenProposer(size);
		this.mainMenuView = mainMenuView;
		this.toolBar = new JProposeKenKenToolBar(controller);
		this.kenKenPanel = new JKenKenPanel(kenKenProposer.getKenKen());
	}

	public void start() {
		configureWindow();
		configureLayout();
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void setTool(JToolBarItem toolBarItem) {
		if (activeTool == toolBarItem.getTool() && activeTool != ProposeKenKenTool.ADD_TO_GROUP) {
			selectNoTools();
			return;
		}

		if (toolBarItem.getTool() == ProposeKenKenTool.ADD_TO_GROUP) {
			selectedGroup = selectGroup();
			if (selectedGroup == null)
				return;
		}

		if (toolBarItem.getTool() == ProposeKenKenTool.CREATE_GROUP) {
			selectedGroup = askNewGroup();
		} else if (toolBarItem.getTool() == ProposeKenKenTool.DELETE_GROUP) {
			Group group = selectGroup();
			if (group == null)
				return;
			if (JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this group? This action cannot be undone.", "Delete group", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
				deleteGroup(group);
		} else {
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
					kenKenProposer.clearFixedPosition(row, col);
					kenKenPanel.clearValue(row, col);
					return;
				}
				try {
					kenKenProposer.setFixedPosition(row, col, Integer.parseInt(value));
					kenKenPanel.setFixedValue(row, col, Integer.parseInt(value));
					break;
				} catch (NumberFormatException | ValueOutOfBoundsException e) {
					JOptionPane.showMessageDialog(this, "Please enter a valid number", "Invalid number", JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (activeTool == ProposeKenKenTool.ADD_TO_GROUP) {
			try {
				kenKenProposer.addCellToGroup(row, col, selectedGroup);
			} catch (GroupDoesNotExistException e) {
				// TODO: handle this
			}
			kenKenPanel.getCell(row, col).addToGroup(selectedGroup, kenKenProposer.getGroupColor(selectedGroup));
		} else if (activeTool == ProposeKenKenTool.REMOVE_FROM_GROUP) {
			kenKenProposer.removeCellGroup(row, col);
			kenKenPanel.getCell(row, col).removeFromGroup();
		}
	}

	public void generateGroups() {
		try {
			kenKenProposer.generateGroups();
		} catch (TooManyOperandsException | CellAlreadyInGroupException | CellHasNoGroupException | GroupCellsNotContiguousException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error generating the KenKen", JOptionPane.ERROR_MESSAGE);
			return;
		}

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

		for (int i = 0; i < kenKenProposer.getSize(); i++)
			for (int j = 0; j < kenKenProposer.getSize(); j++)
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

	private Group askNewGroup() {
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
				return kenKenProposer.createGroup(OperationFactory.createOperation(selectedOption, intValue));
			} catch (CannotCreateOperationException ignored) {
				JOptionPane.showMessageDialog(this, "An error occurred while creating the operation", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		return null;
	}

	private Group selectGroup() {
		if (!kenKenProposer.anyGroup()) {
			JOptionPane.showMessageDialog(this, "No groups have been created", "No groups", JOptionPane.ERROR_MESSAGE);
			return null;
		}

		JPanel askGroupPane = new JPanel(new GridLayout(1, 2));
		JComboBox<String> comboBox = new JComboBox<>(kenKenProposer.getGroupNotationsEnum());

		askGroupPane.add(new JLabel("Select the group:"));
		askGroupPane.add(comboBox);

		if (JOptionPane.showConfirmDialog(this, askGroupPane, "Select a group",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
			return kenKenProposer.getGroup(comboBox.getSelectedIndex());
		}
		return null;
	}

	private void deleteGroup(Group group) {
		for (int i = 0; i < kenKenProposer.getSize(); i++)
			for (int j = 0; j < kenKenProposer.getSize(); j++)
				if (kenKenProposer.getCellGroup(i, j) == group)
					kenKenPanel.getCell(i, j).removeFromGroup();
		kenKenProposer.deleteGroup(group);
		if (selectedGroup == group)
			selectedGroup = null;
		if (activeTool == ProposeKenKenTool.ADD_TO_GROUP)
			selectNoTools();
	}

	private void selectNoTools() {
		activeTool = null;
		toolBar.unsetActiveAll();
		setCursor(Cursor.getDefaultCursor());
	}
}
