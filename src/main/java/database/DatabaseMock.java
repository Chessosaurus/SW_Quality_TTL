package database;

import backend.model.Contact;

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
    /**
     * Searches the CSV File for a Contact
     * <p>
     * This Method always returns a Value and never null
     * @param id id of the contact to search for
     * @return Optional.of(Contact) or Optional.empty()
     */
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
    /**
     * Adds or updates a Contact to the CSV File
     * <p>
     * If the Contact exists already it instead updates the contact
     * @param contact The contact to add to the Database
     */
    @Override
    public void addContact(Contact contact) {
        if (containsId(contact.getId())) {
            updateContact(contact);
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
                contact.setId(nextId++);
                writer.write(toCSV(contact));
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Updates an entry in the CSV File
     * <p>
     * If there is no user with the given id, nothing happens
     * @param  updateContact  the contact that needs its values updates
     */
    @Override
    public void updateContact(Contact updateContact) {
        List<Contact> contacts = getAllContacts();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath))) {
            for (Contact contact : contacts) {
                if (contact.getId() == updateContact.getId()) {
                    writer.write(toCSV(updateContact));
                } else {
                    writer.write(toCSV(contact));
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Searches the CSV for all Contacts
     * <p>
     * This Method always returns a List
     * @return List<Contact> Contains all values of the CSV File
     */
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
        String titles = String.join("|", contact.getTitles()); // Assuming titles are separated by "|"
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
        return new Contact(id, firstName, lastName, titles, gender, language, salutation);
    }

    private int getLastEntryId() {
        int lastId = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Contact contact = fromCSV(line);
                if (contact != null && contact.getId() > lastId) {
                    lastId = contact.getId();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastId;
    }

    private boolean containsId(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Contact contact = fromCSV(line);
                assert contact != null;
                if (contact.getId() == id) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
