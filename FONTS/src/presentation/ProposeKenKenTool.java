package presentation;

import javax.swing.*;
import java.awt.*;

public enum ProposeKenKenTool {
	SET_FIXED_VALUE("Set/Clean given values", "fixed_value"),
	CREATE_GROUP("Create a new group", "create_group"),
	DELETE_GROUP("Delete an existing group", "delete_group"),
	ADD_TO_GROUP("Add cells to a group", "add_to_group"),
	REMOVE_FROM_GROUP("Remove cells from their group", "remove_from_group");

	private final String tooltip;
	private final ImageIcon icon;
	private final Cursor cursor;

	ProposeKenKenTool(String tooltip, String iconName) {
		this.tooltip = tooltip;
		this.icon = new ImageIcon(String.format("sprites/%s.png", iconName));
		Cursor cursor = Cursor.getDefaultCursor();
		try {
			cursor = Toolkit.getDefaultToolkit().createCustomCursor(
					new ImageIcon(String.format("sprites/%s_cursor.png", iconName)).getImage(),
					new Point(0,0),
					iconName
			);
		} catch (HeadlessException ignored) {}
		this.cursor = cursor;
	}

	public String getTooltip() {
		return tooltip;
	}

	public ImageIcon getIcon() {
		return icon;
	}

	public Cursor getCursor() {
		return cursor;
	}
}
