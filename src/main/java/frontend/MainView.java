package frontend;

import backend.model.Contact;

import javax.swing.*;

public class MainView extends JFrame{
    private Contact contact;
    private ValuePanel valuePanel;
    public MainView(){
        valuePanel = new ValuePanel(this);
        this.add(valuePanel);
        this.setVisible(true);
    }
    public void setContact(Contact c){

    }

    protected void disableSaving(){

    }
    protected void enableSaving(){

    }
}
