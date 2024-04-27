package database;

import backend.Contact;

import java.util.List;
import java.util.Optional;

public interface DataBaseController {
    Optional<Contact> getModelWithId(int id);
    void addModel(Contact contact);
    void updateModel(Contact contact);
    List<Contact> getAllModels();
}
