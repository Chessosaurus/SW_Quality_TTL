package database;

import backend.Contact;

import java.util.List;
import java.util.Optional;

public interface DataBaseController {
    Optional<Contact> getContactWithId(int id);
    void addContact(Contact contact);
    void updateContact(Contact contact);
    List<Contact> getAllContacts();
}
