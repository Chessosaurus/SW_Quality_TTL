package backend;

import backend.converter.InputConverter;
import backend.converter.SalutationBuilder;
import backend.model.Contact;
import frontend.View;

import java.util.List;

/**
 *
 */
public class ContactController {

    DatabaseConnection databaseConnection = new DatabaseConnection();
    InputConverter contactConverter = new InputConverter();

    public ContactController() {
        View home = new View(this);
    }

    /**
     * @param in
     * @throws WrongInputException
     */
    private void checkInput(String in) throws WrongInputException {
        //digitCheck with regex
        if (in.matches(".*\\d.*")) {
            throw new WrongInputException();
        }
    }

    /**
     * @param input
     * @return
     * @throws WrongInputException
     */
    public Contact convertToContact(String input) throws WrongInputException {
        checkInput(input);
        return contactConverter.convert(input);
    }

    /**
     * @param contact
     */
    public void saveContact(Contact contact) {
        databaseConnection.addContact(contact);
    }

    /**
     * @return
     */
    public List<Contact> getAllContacts() {
        return databaseConnection.getAllContacts();
    }

    /**
     * @param contact
     */
    public void updateSalutation(Contact contact) {
        SalutationBuilder.createSalutation(contact);
    }

    public void addNewTitle(String title) {
        contactConverter.addTitle(title);
    }
}
