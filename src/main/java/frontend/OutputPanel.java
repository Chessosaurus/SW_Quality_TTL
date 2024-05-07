package frontend;

import backend.model.Contact;

import javax.swing.*;

public class OutputPanel extends JPanel {


    private enum Buttons {
        _PREVIEW(OutputPanel::preview, "Vorschau", new JButton()),
        _SAVE(OutputPanel::save, "Speichern", new JButton()),
        ;
        final Runnable buttonPressed;
        final String label;
        final JButton button;

        Buttons(Runnable buttonPressed, String label, JButton button) {
            this.buttonPressed = buttonPressed;
            this.label = label;
            this.button = button;
        }
    }

    static MainView mainView;
    JTextField letterSalutation;

    OutputPanel(MainView mainView) {
        this.mainView = mainView;
        initComponents();
    }

    void initComponents() {
        letterSalutation = new JTextField();
        this.add(letterSalutation);
        for (Buttons button : OutputPanel.Buttons.values()) {
            button.button.setText(button.label);
            button.button.addActionListener(e -> {
                button.buttonPressed.run();
            });
            this.add(button.button);
        }
    }

    public void updateSalutation() {
    }

    void setContact(Contact contact) {
        this.letterSalutation.setText(contact.getLetterSalutation());
    }

    public void enableSaveValidate(boolean enabled) {
        for (Buttons button : OutputPanel.Buttons.values()) {
            button.button.setEnabled(enabled);
        }
    }

    private static void preview() {
        mainView.previewContact();
    }

    private static void save() {
        mainView.saveContact();
    }
}
