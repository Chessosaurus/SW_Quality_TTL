package frontend;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class InputPanel extends JPanel {
    private enum Buttons {
        _ENTER(InputPanel::validateContact, "Eingeben", new JButton()),
        _LOAD(InputPanel::loadContact, "Laden", new JButton()),
        _RESET(InputPanel::resetContact, "Zurücksetzen",new JButton()),
        ;
        final Runnable buttonPressed;
        final String label;
        final JButton button;

        Buttons(Runnable buttonPressed, String label,JButton button) {
            this.buttonPressed = buttonPressed;
            this.label = label;
            this.button = button;
        }
    }

    private JLabel inputLabel;
    private static JTextField inputField;
    private String validRegex = ".*";
    static MainView mainView;

    public InputPanel(MainView mainView) {
        this.mainView = mainView;
        initComponents();
    }

    private void initComponents() {
        initTextField();
        initButtons();
    }

    private void initTextField() {
        inputLabel = new JLabel("Eingabe:");
        inputField = new JTextField();
        inputField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                inputChange();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                inputChange();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                inputChange();
            }

            private void inputChange() {
                if (mainView.isValidInput(inputField.getText(), validRegex)) {
                    mainView.enableSaveValidate(true);
                } else {
                    mainView.enableSaveValidate(false);
                }
            }
        });
        this.add(inputLabel);
        this.add(inputField);
    }

    private void initButtons() {
        for (Buttons button : Buttons.values()) {
            button.button.setText(button.label);
            button.button.addActionListener(e -> button.buttonPressed.run());
            this.add(button.button);
        }
    }

    private static void validateContact() {
        mainView.setValuesFromString(inputField.getText());
    }

    private static void loadContact() {
        mainView.loadContact();
    }

    private static void resetContact() {
        mainView.resetContact();
    }
    protected void setValidationPossible(boolean validationPossible) {
        Buttons._ENTER.button.setEnabled(validationPossible);
    }
}
