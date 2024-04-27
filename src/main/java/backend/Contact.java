package backend;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    int id;
    String firstName;
    String lastName;
    List<String> title;
    String gender;
    String language;
    String salutation;

    public Contact(){
        this.firstName = "";
        this.lastName = "";
        this.title = new ArrayList<>();
        this.gender = "";
        this.language = "";
        this.salutation = "";
    }

    public Contact(String firstName, String lastName, List<String> title, String gender, String language, String salutation) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.gender = gender;
        this.language = language;
        this.salutation = salutation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }
}
