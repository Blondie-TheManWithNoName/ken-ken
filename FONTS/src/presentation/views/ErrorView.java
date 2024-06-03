package presentation.views;

import presentation.*;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;

public class ErrorView {
    private PresentationController pController;
    private final JFrame frameView = new JFrame("Error");
    private final JPanel contentPanel = new JPanel(new BorderLayout());

    private final JLabel errorLabel = new JLabel("Error: ");

    private final JButton returnButton = new JButton("RETURN");

    public ErrorView(PresentationController controller) {
        pController = controller;
        initializeComponents();
    }

    private void initializeComponents() {
        JPanel errorPanel = new JPanel(new BorderLayout());
        errorPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        //errorPanel.setBorder(new EmptyBorder(0, 0, 0, 10));
        errorPanel.add(errorLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //buttonPanel.setBorder(new EmptyBorder(10, 0, 0, 0));
        buttonPanel.add(returnButton,BorderLayout.WEST);

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

    public void update(String errorMessage) {
        errorLabel.setText("Error: " + errorMessage);
        JOptionPane.showMessageDialog(frameView, "Error: " + errorMessage);
        System.exit(-1);
    }

    private void actionPerformed_returnButton(ActionEvent event) {
        System.out.println("Return Button clicked");
    }


    public void makeVisible() {
        //frameView.setVisible(true);
    }
}
