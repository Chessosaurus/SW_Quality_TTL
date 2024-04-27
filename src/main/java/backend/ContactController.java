package backend;

import database.DataBaseController;
import database.DatabaseMock;
import frontend.View;

import java.security.cert.CertPath;
import java.util.List;

public class ContactController {

    DatabaseConnection databaseConnection;
    InputConverter contactConverter;

    public ContactController() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        InputConverter contactConverter = new InputConverter();
    }

    private void checkInput(String in) throws WrongInputException{
        //digitCheck with regex
        if(in.matches(".*\\d.*")){
            throw new WrongInputException();
        }
    }

    public Contact converToContact(String input) throws WrongInputException{
        checkInput(input);
        return contactConverter.convert(input);
    }

     public void saveContact(Contact contact){
        databaseConnection.addContact(contact);
     }

    public List<Contact> getAllContacts() {
        return databaseConnection.getAllContacts();
    }


    public static void main(String[] args) {
        View home = new View();
    }
}
