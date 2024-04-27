package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class View extends JFrame {
    public View(){

        setTitle("Meine UI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());
        setResizable(false);

        // String[] labels = {"Input", "Anrede", "Titel", "Vorname", "Nachname", "Geschlecht"};

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
                JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"keine Angabe", "männlich", "weiblich", "divers"}); // Erstelle eine Dropdown-Liste mit den Geschlechts-Optionen
                JLabel label = new JLabel(labels[i], SwingConstants.RIGHT); // Rechtsbündig ausgerichtetes Label
                label.setBorder(BorderFactory.createEmptyBorder(insets.top, insets.left, insets.bottom, insets.right)); // Setze das Padding für das Label
                boxPanel.add(label); // Füge das Label hinzu
                boxPanel.add(genderComboBox); // Füge die Dropdown-Liste hinzu
            } else if (i == 5) {
                JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Deutsch", "Englisch"}); // Erstelle eine Dropdown-Liste mit den Geschlechts-Optionen
                JLabel label = new JLabel(labels[i], SwingConstants.RIGHT); // Rechtsbündig ausgerichtetes Label
                label.setBorder(BorderFactory.createEmptyBorder(insets.top, insets.left, insets.bottom, insets.right)); // Setze das Padding für das Label
                boxPanel.add(label); // Füge das Label hinzu
                boxPanel.add(genderComboBox); // Füge die Dropdown-Liste hinzu
            } else {
                JTextField textField = new JTextField(10); // Erstelle das Textfeld
                JLabel label = new JLabel(labels[i], SwingConstants.RIGHT); // Rechtsbündig ausgerichtetes Label
                label.setBorder(BorderFactory.createEmptyBorder(insets.top, insets.left, insets.bottom, insets.right)); // Setze das Padding für das Label
                boxPanel.add(label); // Füge das Label hinzu
                boxPanel.add(textField); // Füge das Textfeld hinzu
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
                new SecondView(); // Instanziere und zeige die neue Ansicht
            }
        });

        setVisible(true);
    }

    private class SecondView extends JFrame {
        public SecondView() {
            setTitle("Zweite Ansicht");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Schließe nur das zweite Fenster
            setSize(600, 400);

            JButton backButton = new JButton("Zurück");
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose(); // Schließe das zweite Fenster
                }
            });

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());

            JTable table = createTable();
            JScrollPane scrollPane = new JScrollPane(table);
            panel.add(scrollPane, BorderLayout.CENTER);

            panel.add(backButton, BorderLayout.SOUTH);

            add(panel);

            setLocationRelativeTo(null); // Zentriere das Fenster
            setVisible(true);
        }

        private JTable createTable() {
            MyTableModel tableModel = new MyTableModel();
            JTable table = new JTable(tableModel);

            // Button-Renderer und -Editor für die letzte Spalte der Tabelle hinzufügen
            TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(6).setCellRenderer(new ButtonRenderer());
            columnModel.getColumn(6).setCellEditor(new ButtonEditor(new JTextField()));

            return table;
        }

        class MyTableModel extends DefaultTableModel {
            // Die Anzahl der Spalten
            private final int columnCount = 7;

            @Override
            public int getColumnCount() {
                return columnCount;
            }

            @Override
            public String getColumnName(int column) {
                return switch (column) {
                    case 0 -> "Anrede";
                    case 1 -> "Titel";
                    case 2 -> "Vorname";
                    case 3 -> "Nachname";
                    case 4 -> "Geschlecht";
                    case 5 -> "Sprache";
                    case 6 -> "Auswahl";
                    default -> "Leer";
                };
            }

            @Override
            public int getRowCount() {
                // Hier könntest du die Anzahl der Zeilen dynamisch anpassen
                return 10;
            }

            @Override
            public Object getValueAt(int row, int column) {
                return "laden"; // Alle Buttons in der Tabelle sollen mit "laden" beschriftet sein
            }
        }

        class ButtonRenderer extends JButton implements TableCellRenderer {
            public ButtonRenderer() {
                setOpaque(true);
            }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                setText((value == null) ? "" : value.toString());
                return this;
            }
        }

        class ButtonEditor extends DefaultCellEditor {
            private JButton button;
            private String label;
            private boolean isPushed;

            public ButtonEditor(JTextField textField) {
                super(textField);
                button = new JButton();
                button.setOpaque(true);
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Zurück zur Hauptseite navigieren, wenn ein Button in der Tabelle geklickt wird
                        dispose();
                    }
                });
            }

            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                if (isSelected) {
                    button.setForeground(table.getSelectionForeground());
                    button.setBackground(table.getSelectionBackground());
                } else {
                    button.setForeground(table.getForeground());
                    button.setBackground(table.getBackground());
                }

                label = (value == null) ? "" : value.toString();
                button.setText(label);
                isPushed = true;
                return button;
            }

            @Override
            public Object getCellEditorValue() {
                if (isPushed) {
                    // Aktion ausführen, wenn der Button gedrückt wird
                    // Hier kannst du die gewünschte Aktion hinzufügen
                    System.out.println(label + " Button clicked.");
                }
                isPushed = false;
                return label;
            }

            @Override
            public boolean stopCellEditing() {
                isPushed = false;
                return super.stopCellEditing();
            }

            @Override
            protected void fireEditingStopped() {
                super.fireEditingStopped();
            }
        }
    }
}
