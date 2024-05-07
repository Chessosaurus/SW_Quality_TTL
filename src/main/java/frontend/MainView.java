package frontend;

import backend.ContactController;
import backend.model.Contact;
import logging.CustomLogger;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {
    private InputPanel inputPanel;
    private ValuePanel valuePanel;
    private OutputPanel outputPanel;
    private SelectionView selectionView;
    private ContactController contactController;

    public MainView() {
        this.contactController = new ContactController();
        this.selectionView = new SelectionView();
        setResizable(false);
        setSize(600, 400);
        setLayout(new BorderLayout());
        inputPanel = new InputPanel(this);
        add(inputPanel, BorderLayout.NORTH);
        valuePanel = new ValuePanel(this);
        this.add(valuePanel, BorderLayout.CENTER);
        outputPanel = new OutputPanel(this);
        this.add(outputPanel, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    public void setValuesFromString(String input) {
        Contact contact;
        try{
            contact = contactController.convertToContact(input);
            valuePanel.setContact(contact);
        }catch (Exception e){
            CustomLogger.error(e.getMessage());
        }
    }

    public void setContact(Contact c) {
        valuePanel.setContact(c);
        outputPanel.setContact(c);
    }

    protected void enableSaveValidate(boolean enabled) {
        outputPanel.enableSaveValidate(enabled);
    }


    protected boolean isValidInput(String input, String validRegex) {
        return input.matches(validRegex);
    }
    void loadContact() {
        try{
            valuePanel.setContact(selectionView.selectContact());
        }catch (Exception e){
            CustomLogger.error(e.getMessage());
        }
    }

    public void previewContact() {
        contactController.updateSalutation(valuePanel.getContact());
        valuePanel.updateSalutation();
        outputPanel.updateSalutation();
    }

    public void saveContact() {
        contactController.saveContact(valuePanel.getContact());
        valuePanel.setContact(null);
    }
}
