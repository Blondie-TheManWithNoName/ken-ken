package presentation.views;

import exceptions.*;
import models.kenken.Group;
import models.kenken.KenKenProposer;
import models.operations.OperationFactory;
import presentation.PresentationController;
import presentation.ProposeKenKenTool;
import presentation.controllers.ProposeKenKenController;
import presentation.custom.*;

import javax.swing.*;
import java.awt.*;

/**
 * ProposeKenKenView represents the GUI window for proposing a KenKen puzzle.
 */
public class ProposeKenKenView extends MainView {
//	private final MainMenuView mainMenuView;
	private final KenKenProposer kenKenProposer;
	private final JProposeKenKenToolBar toolBar;
	private final JKenKenPanel kenKenPanel;

	private final JCustomButton cancelButton = new JCustomButton("Cancel");
	private final JCustomButton continueButton = new JCustomButton("Continue");

	private final ProposeKenKenController controller;
	private final PresentationController pController;
	private ProposeKenKenTool activeTool;
	private Group selectedGroup;

	/**
	 * Constructor for ProposeKenKenView.
	 * @param controller Presentation controller
	 * @param size Size of the KenKen puzzle
	 */
	public ProposeKenKenView(PresentationController controller, int size) {
		this.kenKenProposer = new KenKenProposer(size);
//		this.mainMenuView = mainMenuView;
		this.pController = controller;
		this.controller = new ProposeKenKenController(pController, this, kenKenProposer);
		this.toolBar = new JProposeKenKenToolBar(this.controller);
		this.kenKenPanel = new JKenKenPanel(kenKenProposer.getKenKen());
		start();
	}

//	public void start() {
//		configureWindow();
//		configureLayout();
//		pack();
//		setLocationRelativeTo(null);
//		setVisible(true);
//	}

	/**
	 * Sets the active tool for the user.
	 * @param toolBarItem The tool to be set as active
	 */
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

	/**
	 * Performs an action with the selected tool on a given cell.
	 * @param row Row of the cell
	 * @param col Column of the cell
	 */
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

	/**
	 * Generates groups in the proposed KenKen puzzle.
	 */
	public void generateGroups() {
		try {
			kenKenProposer.generateGroups();
		} catch (TooManyOperandsException | CellAlreadyInGroupException | CellHasNoGroupException | GroupCellsNotContiguousException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error generating the KenKen", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (pController.checkKenKen(kenKenProposer.getKenKen()))
		{


		}
		else {
			JOptionPane.showMessageDialog(this, "KenKen cannot be solved.", "Fail", JOptionPane.ERROR_MESSAGE);
		}

		// TODO: do something with the KenKen
	}

	/**
	 * Exits the proposal process.
	 */
	public void exit() {
		if (JOptionPane.showConfirmDialog(this, "Are you sure you want to exit? All progress will be lost", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
			dispose();
	}

	/**
	 * Exits the proposal process.
	 */
	@Override
	public void dispose() {
//		mainMenuView.setVisible(true);
		super.dispose();
	}

	/**
	 * Configures the window settings.
	 */
	private void configureWindow() {
		setTitle("Propose a KenKen");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
	}

	/**
	 * Configures the layout of the ProposeKenKenView.
	 */
	private void configureLayout() {
		setLayout(new BorderLayout());

		for (int i = 0; i < kenKenProposer.getSize(); i++)
			for (int j = 0; j < kenKenProposer.getSize(); j++)
				kenKenPanel.addController(i, j, controller, ProposeKenKenController.CELL_CLICKED_AC + i + "_" + j);

		JPanel buttonsPanel = new JPanel(new GridLayout(2, 1));
		continueButton.addActionListener(controller);
		continueButton.setActionCommand(ProposeKenKenController.PLAY_AC);
		buttonsPanel.add(continueButton);
		cancelButton.addActionListener(controller);
//		cancelButton.setActionCommand(ProposeKenKenController.CANCEL_AC);
		buttonsPanel.add(cancelButton);

		add(toolBar, BorderLayout.NORTH);
		add(kenKenPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
	}

	/**
	 * Initializes the ProposeKenKenView.
	 */
	public void start() {
		for (int i = 0; i < kenKenProposer.getSize(); i++)
			for (int j = 0; j < kenKenProposer.getSize(); j++)
				kenKenPanel.addController(i, j, controller, ProposeKenKenController.CELL_CLICKED_AC + i + "_" + j);

		c.gridy = 0;
		c.gridx = 0;
		makeSquare("");
		c.gridwidth = 2;
		c.gridx = 1;
		makeSquare("PROPOSE");
		c.gridwidth = 1;
		c.gridx = 3;
		makeSquare("");

		c.gridy = 1;
		c.gridx = 0;
		makeBackButton(controller, ProposeKenKenController.BACK_AC);
		c.gridx = 1;
		c.gridwidth = 2;
		c.gridheight = 3;
		makeKenKenPanel(kenKenPanel);
		c.gridx = 3;
		c.gridwidth = 1;
		c.gridheight = 2;
		makeSquare("");

		c.gridheight = 1;
		c.gridy = 2;
		c.gridx = 0;
		makeSquare("");

		c.gridy = 3;
		c.gridx = 0;
		makeSquare("");
		c.gridx = 3;
		c.gridheight = 2;
		makeSquare("");

		c.gridheight = 1;
		c.gridy = 4;
		c.gridx = 0;
		makeSquare("");
		c.gridx = 1;
		gridbag.setConstraints(toolBar, c);
		add(toolBar);
		c.gridx = 2;
		makeButtonFirst("PLAY", controller, ProposeKenKenController.PLAY_AC);

		c.gridy = 5;
		c.gridx = 0;
		makeSquare("");
		c.gridx = 1;
		makeSquare("");
		c.gridx = 2;
		makeSquare("");
		c.gridx = 3;
		makeSquare("");

		this.revalidate();
		this.repaint();

	}

	/**
	 * Prompts the user to create a new group.
	 * @return The newly created group
	 */
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

	/**
	 * Prompts the user to select a group.
	 * @return The selected group
	 */
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

	/**
	 * Deletes a group from the KenKen puzzle.
	 * @param group The group to be deleted
	 */
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

	/**
	 * Deselects all tools.
	 */
	private void selectNoTools() {
		activeTool = null;
		toolBar.unsetActiveAll();
		setCursor(Cursor.getDefaultCursor());
	}
}
