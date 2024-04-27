package backend;

import backend.model.Contact;
import database.DataBaseController;
import database.DatabaseMock;

import java.util.List;
import java.util.Optional;

public class DatabaseConnection {
    DataBaseController db;
    public DatabaseConnection(){
        db = new DatabaseMock();
    }
    Optional<Contact> getContactWithId(int id) {
        return db.getContactWithId(id);
    }

    public void addContact(Contact contact) {
        db.addContact(contact);
    }

    public void updateContact(Contact updateContact) {
        db.updateContact(updateContact);
    }

    public List<Contact> getAllContacts() {
        return db.getAllContacts();
    }
}
