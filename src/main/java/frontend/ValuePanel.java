package frontend;

import backend.model.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ValuePanel extends JPanel {
    private enum TextInputFields {
        _SALUTATION("Anrede:", ".*", new JTextField(),
                contact::setSalutation, contact::getSalutation),
        _TITLE("Titel:", ".*", new JTextField(),
                contact::setTitlesFromString, contact::getTitlesAsString),
        _FIRSTNAME("Vorname:", ".*", new JTextField(),
                contact::setFirstName, contact::getFirstName),
        _LASTNAME("Nachname:", ".*", new JTextField(),
                contact::setLastName, contact::getLastName),
        ;

        TextInputFields(String lblText, String validRexex,
                        JTextField textField, Consumer<String> setter, Supplier<String> getter) {
            this.lblText = lblText;
            this.validRexex = validRexex;
            this.textField = textField;
            this.setter = setter;
            this.getter = getter;
        }

        private final String lblText;
        private final String validRexex;
        private final JTextField textField;
        private final Consumer<String> setter;
        private final Supplier<String> getter;
    }

    /*private JTextField salutationField;
    private JTextField titleField;
    private JTextField firstnameField;
    private JTextField lastnameField;*/
    private JComboBox<String> genderComboBox;

    private final String[] genders = {"keine Angabe", "männlich", "weiblich", "divers"};
    private JComboBox<String> languageComboBox;

    private final String[] languages = {"Deutsch", "Englisch"};

    private static Contact contact;
    private MainView mainView;

    protected ValuePanel(MainView mainView) {
        this.mainView = mainView;
        this.setLayout(new GridLayout(0, 2));
        addFields();
    }

    public void setContactValues(Contact contact) {
        this.contact = contact;
    }

    public Contact getContactValues() {
        return this.contact;
    }

    private void addFields() {
        for (TextInputFields inputField : TextInputFields.values()) {
            this.add(new JLabel(inputField.lblText, SwingConstants.RIGHT));
            inputField.textField.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (isValidInput(inputField.textField.getText(), inputField.validRexex)) {
                        inputField.setter.accept(inputField.textField.getText());
                        mainView.enableSaving();
                    }
                    {
                        mainView.disableSaving();
                    }
                }
            });
            this.add(inputField.textField);
        }
        initFields();
        /*this.add(new JLabel("Anrede:", SwingConstants.RIGHT));
        this.add(salutationField);
        this.add(new JLabel("Titel:", SwingConstants.RIGHT));
        this.add(titleField);
        this.add(new JLabel("Vorname:", SwingConstants.RIGHT));
        this.add(firstnameField);
        this.add(new JLabel("Nachname", SwingConstants.RIGHT));
        this.add(lastnameField);*/
        this.add(new JLabel("Geschlecht:", SwingConstants.RIGHT));
        this.add(genderComboBox);
        this.add(new JLabel("Anredesprache:", SwingConstants.RIGHT));
        this.add(languageComboBox);
    }

    private boolean isValidInput(String input, String validRegex) {
        return input.matches(validRegex);
    }

    private void initFields() {
        /*initSalutation();
        initTitle();
        initFirstName();
        initLastName();*/
        initGenderBox();
        initLanguageBox();
    }

    /*private void initSalutation() {
        salutationField = new JTextField();
        salutationField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSalutationTo(salutationField.getText());
            }
        });
    }

    private void changeSalutationTo(String salutation) {
        if (salutation.matches(".*\\d.*")) {
            //Invalid Input
            mainView.disableSaving();
        } else {
            //Valid Input
            contact.setSalutation(salutation);
            mainView.enableSaving();
        }
    }

    private void initTitle() {
        titleField = new JTextField();
        titleField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeTitleTo(titleField.getText());
            }
        });
    }

    private void changeTitleTo(String title) {
        if (title.matches(".*\\d.*")) {
            //Invalid Input
            mainView.disableSaving();
        } else {
            //Valid Input
            contact.setFirstName(title);
            mainView.enableSaving();
        }
    }

    private void initFirstName() {
        firstnameField = new JTextField();
        firstnameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeFirstNameTo(firstnameField.getText());
            }
        });
    }

    private void changeFirstNameTo(String firstName) {
        if (firstName.matches(".*\\d.*")) {
            //Invalid Input
            mainView.disableSaving();
        } else {
            //Valid Input
            contact.setFirstName(firstName);
            mainView.enableSaving();
        }
    }

    private void initLastName() {
        lastnameField = new JTextField();
        lastnameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeLastNameTo(lastnameField.getText());
            }
        });
    }

    private void changeLastNameTo(String lastName) {
        if (lastName.matches(".*\\d.*")) {
            //Invalid Input
            mainView.disableSaving();
        } else {
            //Valid Input
            contact.setLastName(lastName);
            mainView.enableSaving();
        }
    }

     */
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
