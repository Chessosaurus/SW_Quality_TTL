package frontend;

import backend.ContactController;
import backend.WrongInputException;
import backend.model.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class View extends JFrame {
    // Instanzvariablen für Eingabefelder
    private JTextField salutationField;
    private JTextField titleField;
    private JTextField firstnameField;
    private JTextField lastnameField;
    private JComboBox<String> genderComboBox;
    private JComboBox<String> languageComboBox;
    private ContactController contactController;
    private Contact contact;
    JButton previewButton;
    JButton confirmButton;
    public View() {
        setTitle("Kontaktsplitter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());
        setResizable(false);

        // Top Input Field with Buttons
        JPanel inputPanel = new JPanel();
        JTextField inputField = new JTextField(20);
        JButton inputButton = new JButton("Eingabe");
        JButton loadDataButton = new JButton("Laden");
        JButton clearButton = new JButton("Zurücksetzten");
        inputPanel.add(inputField);
        inputPanel.add(inputButton);
        inputPanel.add(loadDataButton);
        inputPanel.add(clearButton);
        add(inputPanel, BorderLayout.NORTH);

        contactController = new ContactController();

        // Box with Input-Fields for output and edit of the splitted Contact
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new GridLayout(0, 2));
        Insets insets = new Insets(10, 10, 10, 10);

        String[] labels = {"Anrede:", "Titel:", "Vorname:", "Nachname:", "Geschlecht:", "Anredesprache"};

        for (int i = 0; i < 6; i++) {
            if (i == 4) { // If the index is 4 then a ComboBox for gender is created
                genderComboBox = new JComboBox<>(new String[]{"keine Angabe", "männlich", "weiblich", "divers"}); // Creates Combo Box
                JLabel label = new JLabel(labels[i], SwingConstants.RIGHT);
                label.setBorder(BorderFactory.createEmptyBorder(insets.top, insets.left, insets.bottom, insets.right));
                boxPanel.add(label);
                boxPanel.add(genderComboBox);
            } else if (i == 5) { // If the index is 5 then a ComboBox for language is created
                languageComboBox = new JComboBox<>(new String[]{"Deutsch", "Englisch"}); // Creates Combo Box
                JLabel label = new JLabel(labels[i], SwingConstants.RIGHT);
                label.setBorder(BorderFactory.createEmptyBorder(insets.top, insets.left, insets.bottom, insets.right));
                boxPanel.add(label);
                boxPanel.add(languageComboBox);
            } else {
                JTextField textField = new JTextField(10); // Else a TextField is created
                JLabel label = new JLabel(labels[i], SwingConstants.RIGHT);
                label.setBorder(BorderFactory.createEmptyBorder(insets.top, insets.left, insets.bottom, insets.right));
                boxPanel.add(label);
                boxPanel.add(textField);

                // Depending on the index, the text fields are created
                switch (i) {
                    case 0:
                        salutationField = textField;
                        break;
                    case 1:
                        titleField = textField;
                        break;
                    case 2:
                        firstnameField = textField;
                        break;
                    case 3:
                        lastnameField = textField;
                        break;
                }
            }
        }
        previewButton = new JButton("Preview");
        confirmButton = new JButton("Bestätigen");
        boxPanel.add(previewButton);
        boxPanel.add(confirmButton);
        add(boxPanel, BorderLayout.CENTER);

        previewButton.setEnabled(false);
        confirmButton.setEnabled(false);

        // Bottom OutputField
        JTextArea outputArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.SOUTH);

        // ActionListener for Loading-Button
        loadDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SecondView(View.this); // New Instance of SecondView
            }
        });
        inputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    contact = contactController.convertToContact(inputField.getText());
                    setContact(contact);
                } catch (WrongInputException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contact.setSalutation(salutationField.getText());
                contact.setTitles(Arrays.asList(titleField.getText().split(", ")));
                contact.setFirstName(firstnameField.getText());
                contact.setLastName(lastnameField.getText());
                contact.setGender(genderComboBox.getSelectedItem().toString());
                contact.setLanguage(languageComboBox.getSelectedItem().toString());
                System.out.println(contact);
                contactController.saveContact(contact);
                clearFields();
            }
        });
        previewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                contact.setSalutation(salutationField.getText());
                contact.setTitles(Arrays.asList(titleField.getText().split(", ")));
                contact.setFirstName(firstnameField.getText());
                contact.setLastName(lastnameField.getText());

                if(genderComboBox.getSelectedItem().toString().equals("männlich")) {
                    contact.setGender("m");
                } else if (genderComboBox.getSelectedItem().toString().equals("weiblich")) {
                    contact.setGender("f");
                } else if (genderComboBox.getSelectedItem().toString().equals("divers")) {
                    contact.setGender("d");
                } else {
                    contact.setGender(genderComboBox.getSelectedItem().toString());
                }

                if(languageComboBox.getSelectedItem().toString().equals("Englisch")){
                    contact.setLanguage("en");
                } else if (languageComboBox.getSelectedItem().toString().equals("Deutsch")){
                    contact.setLanguage("de");
                } else {
                    contact.setLanguage(languageComboBox.getSelectedItem().toString());
                }


                contactController.updateSalutation(contact);
                outputArea.setText(contact.getLetterSalutation() + ",");
            }
        });

        setVisible(true);
    }

    public void setContact(Contact contact){
        setAnredeField(contact.getSalutation());
        setTitelField(String.join(", ", contact.getTitles()));
        setVornameField(contact.getFirstName());
        setNachnameField(contact.getLastName());
        setGeschlechtComboBox(contact.getGender());
        setSpracheComboBox(contact.getLanguage());

        this.contact = contact;
        previewButton.setEnabled(true);
        confirmButton.setEnabled(true);
    }

    // Setter for each InputField, accepts String as parameter
    public void setAnredeField(String text) {
        salutationField.setText(text);
    }

    public void setTitelField(String text) {
        titleField.setText(text);
    }

    public void setVornameField(String text) {
        firstnameField.setText(text);
    }

    public void setNachnameField(String text) {
        lastnameField.setText(text);
    }

    public void setGeschlechtComboBox(String value) {
        genderComboBox.setSelectedItem(value);
    }

    public void setSpracheComboBox(String value) {
        languageComboBox.setSelectedItem(value);
    }
    public void clearFields(){
        setAnredeField("");
        setTitelField("");
        setVornameField("");
        setNachnameField("");
        setGeschlechtComboBox("keine Angabe");
        setSpracheComboBox("Deutsch");

        previewButton.setEnabled(false);
        confirmButton.setEnabled(false);

        contact = null;
    }
}
