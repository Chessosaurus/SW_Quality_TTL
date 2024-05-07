package frontend;

import backend.model.Contact;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ValuePanel extends JPanel {


    private enum TextInputField {
        _SALUTATION("Anrede:", ".*", new JTextField(),
                contact::setSalutation, contact::getSalutation),
        _TITLE("Titel:", ".*", new JTextField(),
                contact::setTitlesFromString, contact::getTitlesAsString),
        _FIRSTNAME("Vorname:", ".*", new JTextField(),
                contact::setFirstName, contact::getFirstName),
        _LASTNAME("Nachname:", ".*", new JTextField(),
                contact::setLastName, contact::getLastName),
        ;

        TextInputField(String lblText, String validRegex,
                       JTextField textField, Consumer<String> setter, Supplier<String> getter) {
            this.lblText = lblText;
            this.validRegex = validRegex;
            this.textField = textField;
            this.setter = setter;
            this.getter = getter;
        }

        private final String lblText;
        private final String validRegex;
        private final JTextField textField;
        private final Consumer<String> setter;
        private final Supplier<String> getter;
    }

    private JComboBox<String> genderComboBox;

    private final String[] genders = {"keine Angabe", "männlich", "weiblich", "divers"};
    private JComboBox<String> languageComboBox;

    private final String[] languages = {"Deutsch", "Englisch"};

    private static Contact contact;
    private MainView mainView;

    protected ValuePanel(MainView mainView) {
        this.contact = new Contact();
        this.mainView = mainView;
        this.setLayout(new GridLayout(0, 2));
        addFields();
    }

    public void setContact(Contact contact) {
        this.contact = contact;
        for (TextInputField inputField : TextInputField.values()) {
            inputField.textField.setText(inputField.getter.get());
        }
    }

    public Contact getContact() {
        return this.contact;
    }
    public void updateSalutation() {
        TextInputField._SALUTATION.textField.setText(contact.getSalutation());
    }

    private void addFields() {
        for (TextInputField inputField : TextInputField.values()) {
            this.add(new JLabel(inputField.lblText, SwingConstants.RIGHT));
            inputField.textField.getDocument().addDocumentListener(new DocumentListener() {
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
                    if (mainView.isValidInput(inputField.textField.getText(), inputField.validRegex)) {
                        inputField.setter.accept(inputField.textField.getText());
                        inputField.textField.setBorder(new LineBorder(Color.BLACK, 2));
                        mainView.enableSaveValidate(true);
                    } else {
                        inputField.textField.setBorder(new LineBorder(Color.RED, 2));
                        mainView.enableSaveValidate(false);
                    }
                }
            });
            this.add(inputField.textField);
        }
        initComboBoxes();
        this.add(new JLabel("Geschlecht:", SwingConstants.RIGHT));
        this.add(genderComboBox);
        this.add(new JLabel("Anredesprache:", SwingConstants.RIGHT));
        this.add(languageComboBox);
    }


    private void initComboBoxes() {
        initGenderBox();
        initLanguageBox();
    }

    private void initGenderBox() {
        genderComboBox = new JComboBox<>(genders);
        genderComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contact.setGender((String) genderComboBox.getSelectedItem());
            }
        });
    }

    private void initLanguageBox() {
        languageComboBox = new JComboBox<>(languages);
        languageComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contact.setLanguage((String) languageComboBox.getSelectedItem());
            }
        });
    }
}
