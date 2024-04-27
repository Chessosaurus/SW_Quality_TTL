package backend;

import backend.converter.InputConverter;
import backend.converter.SalutationBuilder;
import backend.model.Contact;
import frontend.View;

import java.util.List;

public class ContactController {

    DatabaseConnection databaseConnection = new DatabaseConnection();
    InputConverter contactConverter = new InputConverter();
    SalutationBuilder salutationBuilder = new SalutationBuilder();

    private void checkInput(String in) throws WrongInputException{
        //digitCheck with regex
        if(in.matches(".*\\d.*")){
            throw new WrongInputException();
        }
    }

    public Contact convertToContact(String input) throws WrongInputException{
        checkInput(input);
        Contact contact = contactConverter.convert(input);
        salutationBuilder.createSalutation(contact);
        return contact;
    }

     public void saveContact(Contact contact){
        databaseConnection.addContact(contact);
     }

    public List<Contact> getAllContacts() {
        return databaseConnection.getAllContacts();
    }

    public void updateSalutation(Contact contact){
        salutationBuilder.createSalutation(contact);
    }

    public static void main(String[] args) {
        View home = new View();
    }
}
