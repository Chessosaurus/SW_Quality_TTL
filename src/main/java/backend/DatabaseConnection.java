package backend;

import backend.model.Contact;
import database.DataBaseController;
import database.DatabaseMock;

import java.util.List;


public class DatabaseConnection {
    DataBaseController db;

    public DatabaseConnection() {
        db = new DatabaseMock();
    }

    public void addContact(Contact contact) {
        db.addContact(contact);
    }

    public List<Contact> getAllContacts() {
        return db.getAllContacts();
    }
}
