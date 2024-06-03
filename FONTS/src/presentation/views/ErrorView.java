package presentation.views;

import presentation.PresentationController;

import javax.swing.*;
import javax.swing.BorderFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * View for displaying error messages.
 */
public class ErrorView {
    private PresentationController pController;
    private final JFrame frameView = new JFrame("Error");
    private final JPanel contentPanel = new JPanel(new BorderLayout());

    private final JLabel errorLabel = new JLabel("Error: ");
    private final JButton returnButton = new JButton("RETURN");

    /**
     * Constructs an ErrorView with the given PresentationController.
     *
     * @param controller The PresentationController instance.
     */
    public ErrorView(PresentationController controller) {
        pController = controller;
        initializeComponents();
    }

    /**
     * Initializes the components of the error view.
     */
    private void initializeComponents() {
        JPanel errorPanel = new JPanel(new BorderLayout());
        errorPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        errorPanel.add(errorLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(returnButton, BorderLayout.WEST);

        contentPanel.add(errorPanel, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                actionPerformed_returnButton(event);
            }
        });

        frameView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameView.setSize(450, 200);
        frameView.setLocationRelativeTo(null);
        frameView.getContentPane().add(contentPanel);
    }

    /**
     * Updates the error message in the view.
     *
     * @param errorMessage The error message to display.
     */
    public void update(String errorMessage) {
        errorLabel.setText("Error: " + errorMessage);
        JOptionPane.showMessageDialog(frameView, "Error: " + errorMessage);
        System.exit(-1);
    }

    /**
     * Handles the action when the return button is clicked.
     *
     * @param event The action event.
     */
    private void actionPerformed_returnButton(ActionEvent event) {
        System.out.println("Return Button clicked");
    }

    /**
     * Makes the error view visible.
     */
    public void makeVisible() {
        frameView.setVisible(true);
    }
}
