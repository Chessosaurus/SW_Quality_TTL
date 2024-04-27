package frontend;

import backend.model.Contact;
import backend.DatabaseConnection;
import backend.ContactController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SecondView extends JFrame {
    private final DatabaseConnection dbConnection = new DatabaseConnection(); // Erstelle eine Instanz von DatabaseConnection
    private final ContactController contactController = new ContactController();
    private List<Contact> contacts;
    JTable table;
    View view;
    public SecondView(View view) {
        setTitle("Zweite Ansicht");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Schließe nur das zweite Fenster
        setSize(600, 400);
        this.view = view;
        JButton backButton = new JButton("Zurück");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Schließe das zweite Fenster
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        contacts = contactController.getAllContacts();
        table = createTable();
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

        @Override
        public int getColumnCount() {
            // Die Anzahl der Spalten
            return 7;
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
            return contacts.size();
        }

        @Override
        public Object getValueAt(int row, int column) {
            return switch (column) {
                case 0 -> contacts.get(row).getSalutation();
                case 1 -> contacts.get(row).getTitles();
                case 2 -> contacts.get(row).getFirstName();
                case 3 -> contacts.get(row).getLastName();
                case 4 -> contacts.get(row).getGender();
                case 5 -> contacts.get(row).getLanguage();
                case 6 -> "laden";
                default -> 0;
            };
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
                    System.out.println("Button wurde geklickt");
                    int selectedRow = table.getSelectedRow();
                    view.setContact(contacts.get(selectedRow));

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