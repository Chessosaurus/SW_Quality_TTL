package backend;

import backend.converter.InputConverter;
import backend.converter.SalutationBuilder;
import backend.model.Contact;
import frontend.View;

import java.util.List;

/**
 * The {@code ContactController} class handles the business logic for managing contacts.
 * This class is responsible for interfacing with the {@code InputConverter} and {@code DatabaseConnection}
 * to convert string inputs into {@code Contact} objects, validate input, and manage contact data persistence.
 */
public class ContactController {

    DatabaseConnection databaseConnection = new DatabaseConnection();
    InputConverter contactConverter = new InputConverter();

    /**
     * Constructs a {@code ContactController} and initializes the user interface by creating a {@code View}.
     */
    public ContactController() {
        View home = new View(this);
    }

    /**
     * Checks if the provided string contains any digits, which are not allowed in this context.
     * Throws {@code WrongInputException} if digits are found.
     *
     * @param in the string input to be validated.
     * @throws WrongInputException if input contains digits.
     */
    private void checkInput(String in) throws WrongInputException {
        //digitCheck with regex
        if (in.matches(".*\\d.*")) {
            throw new WrongInputException();
        }
    }

    /**
     * Converts a given string input to a {@code Contact} object after validating the input.
     * This method first checks for any invalid input and then uses {@code InputConverter} to create a contact.
     *
     * @param input the input string to be converted.
     * @return a {@code Contact} object generated from the input.
     * @throws WrongInputException if input validation fails.
     */
    public Contact convertToContact(String input) throws WrongInputException {
        checkInput(input);
        return contactConverter.convert(input);
    }

    /**
     * Saves a {@code Contact} object to the database using {@code DatabaseConnection}.
     *
     * @param contact the {@code Contact} object to be saved.
     */
    public void saveContact(Contact contact) {
        databaseConnection.addContact(contact);
    }

    /**
     * Retrieves all {@code Contact} objects stored in the database.
     *
     * @return a list of {@code Contact} objects.
     */
    public List<Contact> getAllContacts() {
        return databaseConnection.getAllContacts();
    }

    /**
     * Updates the salutation of a {@code Contact} object using the {@code SalutationBuilder}.
     * This method modifies the contact based on its gender, names, language, and titles.
     *
     * @param contact the {@code Contact} object whose salutation is to be updated.
     */
    public void updateSalutation(Contact contact) {
        SalutationBuilder.createSalutation(contact);
    }

    /**
     * Adds a new title to the list of recognized titles in the {@code InputConverter}.
     * This allows the application to recognize and handle new titles that were not previously configured.
     *
     * @param title the title string to be added.
     */
    public void addNewTitle(String title) {
        contactConverter.addTitle(title);
    }
}
