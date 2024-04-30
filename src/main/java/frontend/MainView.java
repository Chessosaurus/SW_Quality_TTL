package frontend;

import backend.model.Contact;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private InputPanel inputPanel;
    private ValuePanel valuePanel;

    public MainView() {
        setResizable(false);
        setSize(600, 400);
        setLayout(new BorderLayout());
        inputPanel = new InputPanel(this);
        add(inputPanel, BorderLayout.NORTH);
        valuePanel = new ValuePanel(this);
        this.add(valuePanel, BorderLayout.EAST);
        this.setVisible(true);
    }

    public void getValuesFromString(String input) {

    }

    public void setContact(Contact c) {
        valuePanel.setContact(c);
    }

    protected void enableValidating() {

    }

    protected void disableValidating() {

    }

    protected void enableSaving() {

    }

    protected void disableSaving() {

    }

    protected boolean isValidInput(String input, String validRegex) {
        return input.matches(validRegex);
    }
}
