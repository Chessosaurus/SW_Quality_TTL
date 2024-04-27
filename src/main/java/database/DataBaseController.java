package database;

import backend.Model;

import java.util.List;
import java.util.Optional;

public interface DataBaseController {
    Optional<Model> getModelWithId(int id);
    void addModel(Model model);
    void updateModel(Model model);
    List<Model> getAllModels();
}
