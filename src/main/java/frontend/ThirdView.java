package frontend;

import backend.ContactController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ThirdView extends JFrame {
    ContactController contactController;
    View view;

    public ThirdView(View view, ContactController contactController) {
        // Set up the frame
        this.contactController = contactController;
        setTitle("Titel Hinzufügen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 150);
        setLocationRelativeTo(null);
        setVisible(true);
        this.view = view;
        JPanel panel = new JPanel();
        getContentPane().setLayout(new BorderLayout(10, 10));

        // Create components
        JLabel label = new JLabel("Geben Sie einen Titel ein, um ihn hinzuzufügen");
        JTextField titleField = new JTextField(20);
        JButton confirmButton = new JButton("Bestätigen");
        JButton cancelButton = new JButton("Abbrechen");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Closes third View
            }
        });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contactController.addNewTitle(titleField.getText());
                dispose();
            }
        });

        // Customize components
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Raum über dem Label

        // Add components to panels
        textPanel.add(titleField);
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        // Add components to frame using BorderLayout
        add(label, BorderLayout.NORTH);
        add(titleField, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
