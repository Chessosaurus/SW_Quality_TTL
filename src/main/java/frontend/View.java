package frontend;

import backend.model.Contact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class View extends JFrame {
    // Instanzvariablen für Eingabefelder
    private JTextField anredeField;
    private JTextField titelField;
    private JTextField vornameField;
    private JTextField nachnameField;
    private JComboBox<String> geschlechtComboBox;
    private JComboBox<String> spracheComboBox;

    private Contact contact;
    public View() {
        setTitle("Meine UI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());
        setResizable(false);

        // Input Feld oben
        JPanel inputPanel = new JPanel();
        JTextField inputField = new JTextField(20);
        JButton inputButton = new JButton("Eingabe");
        JButton loadDataButton = new JButton("Laden");
        inputPanel.add(inputField);
        inputPanel.add(inputButton);
        inputPanel.add(loadDataButton);
        add(inputPanel, BorderLayout.NORTH);

        // Kasten mit 5 Input Feldern nebeneinander
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new GridLayout(0, 2)); // Änderung hier - GridLayout für Beschriftungen und Textfelder
        Insets insets = new Insets(10, 10, 10, 10); // Insets für Padding

        String[] labels = {"Anrede:", "Titel:", "Vorname:", "Nachname:", "Geschlecht:", "Anredesprache"};
        for (int i = 0; i < 6; i++) {
            if (i == 4) { // Falls es das letzte Feld ist (Index 4)
                geschlechtComboBox = new JComboBox<>(new String[]{"keine Angabe", "männlich", "weiblich", "divers"}); // Erstelle eine Dropdown-Liste mit den Geschlechts-Optionen
                JLabel label = new JLabel(labels[i], SwingConstants.RIGHT); // Rechtsbündig ausgerichtetes Label
                label.setBorder(BorderFactory.createEmptyBorder(insets.top, insets.left, insets.bottom, insets.right)); // Setze das Padding für das Label
                boxPanel.add(label); // Füge das Label hinzu
                boxPanel.add(geschlechtComboBox); // Füge die Dropdown-Liste hinzu
            } else if (i == 5) {
                spracheComboBox = new JComboBox<>(new String[]{"Deutsch", "Englisch"}); // Erstelle eine Dropdown-Liste mit den Geschlechts-Optionen
                JLabel label = new JLabel(labels[i], SwingConstants.RIGHT); // Rechtsbündig ausgerichtetes Label
                label.setBorder(BorderFactory.createEmptyBorder(insets.top, insets.left, insets.bottom, insets.right)); // Setze das Padding für das Label
                boxPanel.add(label); // Füge das Label hinzu
                boxPanel.add(spracheComboBox); // Füge die Dropdown-Liste hinzu
            } else {
                JTextField textField = new JTextField(10); // Erstelle das Textfeld
                JLabel label = new JLabel(labels[i], SwingConstants.RIGHT); // Rechtsbündig ausgerichtetes Label
                label.setBorder(BorderFactory.createEmptyBorder(insets.top, insets.left, insets.bottom, insets.right)); // Setze das Padding für das Label
                boxPanel.add(label); // Füge das Label hinzu
                boxPanel.add(textField); // Füge das Textfeld hinzu

                // Setter für jedes Textfeld
                switch (i) {
                    case 0:
                        anredeField = textField;
                        break;
                    case 1:
                        titelField = textField;
                        break;
                    case 2:
                        vornameField = textField;
                        break;
                    case 3:
                        nachnameField = textField;
                        break;
                }
            }
        }
        JButton cancelButton = new JButton("Abbrechen");
        JButton confirmButton = new JButton("Bestätigen");
        boxPanel.add(cancelButton);
        boxPanel.add(confirmButton);
        add(boxPanel, BorderLayout.CENTER);

        // Outputfeld unten
        JTextArea outputArea = new JTextArea(5, 20);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.SOUTH);

        // ActionListener für den Laden-Button hinzufügen
        loadDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hier Code einfügen, um die neue Ansicht anzuzeigen
                new SecondView(View.this); // Instanziere und zeige die neue Ansicht
            }
        });

        setVisible(true);
    }

    public void setContact(Contact contact){
        setAnredeField(contact.getSalutation());
        setTitelField(contact.getTitles().getFirst() + ", " + contact.getTitles().getLast());
        setVornameField(contact.getFirstName());
        setNachnameField(contact.getLastName());
        setGeschlechtComboBox(contact.getGender());
        setSpracheComboBox(contact.getLanguage());

        this.contact = contact;
    }

    // Setter für jedes Eingabefeld, akzeptiert Strings als Parameter
    public void setAnredeField(String text) {
        anredeField.setText(text);
    }

    public void setTitelField(String text) {
        titelField.setText(text);
    }

    public void setVornameField(String text) {
        vornameField.setText(text);
    }

    public void setNachnameField(String text) {
        nachnameField.setText(text);
    }

    public void setGeschlechtComboBox(String value) {
        geschlechtComboBox.setSelectedItem(value);
    }

    public void setSpracheComboBox(String value) {
        spracheComboBox.setSelectedItem(value);
    }
}
