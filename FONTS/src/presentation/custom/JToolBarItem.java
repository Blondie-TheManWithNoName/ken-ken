package presentation.custom;

import presentation.ProposeKenKenTool;

import javax.swing.*;
import java.awt.*;

public class JToolBarItem extends JButton {
	private final ProposeKenKenTool tool;

	public JToolBarItem(ProposeKenKenTool tool) {
		super(tool.getIcon());
		this.tool = tool;

		setToolTipText(tool.getTooltip());
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFocusable(false);
		setSquareSize();
		setNonActive();
		setBackground(Color.YELLOW);
	}

	public ProposeKenKenTool getTool() {
		return tool;
	}

	public void setActive() {
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		setOpaque(true);
	}

	public void setNonActive() {
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		setOpaque(false);
	}

	public Cursor getToolCursor() {
		return tool.getCursor();
	}

	private void setSquareSize() {
		Dimension preferredSize = getPreferredSize();
		int max = Math.min(preferredSize.width, preferredSize.height);
		setPreferredSize(new Dimension(max, max));
		setMinimumSize(new Dimension(max, max));
		setMaximumSize(new Dimension(max, max));
	}
}
