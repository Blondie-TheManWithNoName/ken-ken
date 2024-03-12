package presentation.custom;

import presentation.ProposeKenKenTool;
import presentation.controllers.ProposeKenKenController;

import javax.swing.*;

public class JProposeKenKenToolBar extends JToolBar {
	private final static int GAP = 2;

	private final ProposeKenKenController controller;

	private final JToolBarItem[] toolBarItems = new JToolBarItem[]{
			new JToolBarItem(ProposeKenKenTool.SET_FIXED_VALUE),
			new JToolBarItem(ProposeKenKenTool.CREATE_GROUP),
			new JToolBarItem(ProposeKenKenTool.DELETE_GROUP),
			new JToolBarItem(ProposeKenKenTool.ADD_TO_GROUP),
			new JToolBarItem(ProposeKenKenTool.REMOVE_FROM_GROUP)
	};

	public JProposeKenKenToolBar(ProposeKenKenController controller) {
		this.controller = controller;
		configureLayout();
	}

	public void unsetActiveAll() {
		for (JToolBarItem toolBarItem : toolBarItems)
			toolBarItem.setNonActive();
	}

	private void configureLayout() {
		setFloatable(false);
		for (int i = 0; i < toolBarItems.length; i++) {
			if (i != 0)
				add(Box.createHorizontalStrut(GAP));
			toolBarItems[i].addActionListener(controller);
			add(toolBarItems[i]);
		}
	}
}
