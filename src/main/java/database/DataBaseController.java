package database;

import backend.model.Contact;

import java.util.List;
import java.util.Optional;

public interface DataBaseController {

    /**
     * Adds a contact to the Database
     * <p>
     * If the Database contains a contact with its id, update it instead
     *
     * @param contact The Contact to be added
     */
    void addContact(Contact contact);

    /**
     * Updates a Contact in the Database
     * <p>
     * If no contact with that id exists, do nothing
     *
     * @param contact contact to update its values for
     * @return Optional.of(Contact) or Optional.empty()
     */
    void updateContact(Contact contact);

    /**
     * Get all Contacts from the Database
     * <p>
     * This Method always returns a List
     *
     * @return List<Contact>
     */
    List<Contact> getAllContacts();
}
