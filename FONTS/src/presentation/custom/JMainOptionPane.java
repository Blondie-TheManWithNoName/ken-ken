package presentation.custom;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class JMainOptionPane extends JOptionPane {

    public JMainOptionPane() {
    }

    public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType) {
        // Set the background color for JOptionPane
        UIManager.put("OptionPane.background", Color.decode("#FAFAFA"));
        UIManager.put("Panel.background", Color.decode("#FAFAFA"));


        // Set additional customizations
        UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 15));
        UIManager.put("OptionPane.messageForeground", Color.decode("#375281"));
        UIManager.put("Button.background", Color.decode("#FAFAFA"));
        UIManager.put("Button.foreground", Color.decode("#375281"));

        JOptionPane.showMessageDialog(parentComponent, message, title, messageType);
    }
}
