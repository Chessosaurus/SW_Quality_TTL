package database;

import backend.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseMock implements DataBaseController {
    private String csvFilePath;
    private int nextId;

    public DatabaseMock() {
        this.csvFilePath = "src/main/java/database/data.csv";
        nextId = getLastEntryId() + 1;
    }

    @Override
    public Optional<Contact> getContactWithId(int id) {
        List<Contact> contacts = getAllContacts();
        for (Contact contact : contacts) {
            if (contact.getId() == id) {
                return Optional.of(contact);
            }
        }
        return Optional.empty();
    }

    @Override
    public void addContact(Contact contact) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
            contact.setId(nextId++);
            writer.write(toCSV(contact));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateContact(Contact updatedModel) {
        List<Contact> models = getAllContacts();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            for (Contact model : models) {
                if (model.getId() == updatedModel.getId()) {
                    writer.write(toCSV(updatedModel));
                } else {
                    writer.write(toCSV(model));
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Contact> getAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Contact contact = fromCSV(line);
                if (contact != null) {
                    contacts.add(contact);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    // Method to create String from Contact
    private String toCSV(Contact contact) {
        String titles = String.join("|", contact.getTitle()); // Assuming titles are separated by "|"
        return contact.getId() + "," + contact.getFirstName() + "," + contact.getLastName() + "," + titles + "," + contact.getGender() + "," + contact.getLanguage() + "," + contact.getSalutation();
    }

    // Method to create Contact from CSV format
    private static Contact fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length != 7) {
            return null; // Invalid CSV format
        }
        int id = Integer.parseInt(parts[0]);
        String firstName = parts[1];
        String lastName = parts[2];
        List<String> titles = List.of(parts[3].split("\\|")); // Assuming titles are separated by "|"
        String gender = parts[4];
        String language = parts[5];
        String salutation = parts[6];
        return new Contact(id,firstName, lastName, titles, gender, language, salutation);
    }

    private int getLastEntryId() {
        int lastId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Contact model = fromCSV(line);
                if (model != null && model.getId() > lastId) {
                    lastId = model.getId();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastId;
    }
}
