package database;

import backend.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseMock implements DataBaseController{
    private String csvFilePath;

    public DatabaseMock() {
        this.csvFilePath = "database/data.csv";
    }

    @Override
    public Optional<Contact> getModelWithId(int id) {
        List<Contact> contacts = getAllModels();
        for (Contact contact : contacts) {
            if (contact.getId() == id) {
                return Optional.of(contact);
            }
        }
        return Optional.empty();
    }

    @Override
    public void addModel(Contact contact) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
            writer.write(toCSV());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateModel(Contact contact) {
        // For simplicity, assuming updating in CSV means adding again
        addModel(contact);
    }

    @Override
    public List<Contact> getAllModels() {
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
    public String toCSV(Contact m) {
        String titles = String.join("|", m.getTitles()); // Assuming titles are separated by "|"
        return m.getId() + "," + m.getfirstName() + "," + m.getlastName() + "," + titles + "," + m.getgender() + "," + m.getlanguage() + "," + m.getsalutation();
    }

    // Method to create Model from CSV format
    public static Contact fromCSV(String csvLine) {
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
}
