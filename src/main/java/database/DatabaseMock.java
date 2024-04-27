package database;

import backend.Model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseMock implements DataBaseController{
    private String csvFilePath;

    public DatabaseMock() {
        this.csvFilePath = "database/data.csv";
    }

    @Override
    public Optional<Model> getModelWithId(int id) {
        List<Model> models = getAllModels();
        for (Model model : models) {
            if (model.getId() == id) {
                return Optional.of(model);
            }
        }
        return Optional.empty();
    }

    @Override
    public void addModel(Model model) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
            writer.write(toCSV());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateModel(Model model) {
        // For simplicity, assuming updating in CSV means adding again
        addModel(model);
    }

    @Override
    public List<Model> getAllModels() {
        List<Model> models = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Model model = fromCSV(line);
                if (model != null) {
                    models.add(model);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return models;
    }
    public String toCSV(Model m) {
        String titles = String.join("|", m.getTitles()); // Assuming titles are separated by "|"
        return m.getId() + "," + m.getfirstName() + "," + m.getlastName() + "," + titles + "," + m.getgender() + "," + m.getlanguage() + "," + m.getsalutation();
    }

    // Method to create Model from CSV format
    public static Model fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length != 7) {
            return null; // Invalid CSV format
        }
        int id = Integer.parseInt(parts[0]);
        String firstName = parts[1];
        String lastName = parts[2];
        List<String> titles = List.of(parts[3].split("\\|")); // Assuming titles are separated by "|"
        String gender = parts[4];
        String language = parts[5];
        String salutation = parts[6];
        return new Model(id, firstName, lastName, titles, gender, language, salutation);
    }
}
