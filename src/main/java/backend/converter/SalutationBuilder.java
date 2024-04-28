package backend.converter;

import backend.model.Contact;

/**
 * Class responsible for generating salutations for Contact objects based on their gender,
 * names, language, and titles.
 */
public class SalutationBuilder {

    /**
     * Creates a salutation for the input Contact object based on its gender, language, and titles.
     *
     * @param contact The Contact object for which the salutation is created.
     */
    public void createSalutation(Contact contact) {
        String title = contact.getTitles().isEmpty() ? "" : contact.getTitles().getFirst();
        String baseSalutation = "Guten Tag";

        if (contact.getLanguage().equals("de")) {
            baseSalutation = createGermanSalutation(contact.getGender(), title);
        } else if (contact.getLanguage().equals("en")) {
            baseSalutation = createEnglishSalutation(contact.getGender(), title);
        }

        contact.setLetterSalutation(baseSalutation + " " + contact.getLastName());
    }

    private String createGermanSalutation(String gender, String title) {
        return switch (gender) {
            case "weiblich" -> "Sehr geehrte Frau" + formatTitle(title, "Frau");
            case "männlich" -> "Sehr geehrter Herr" + formatTitle(title, "Herr");
            default -> "Guten Tag";
        };
    }

    private String createEnglishSalutation(String gender, String title) {
        return switch (gender) {
            case "weiblich" -> "Dear Mrs" + formatTitle(title, "");
            case "männlich" -> "Dear Mr" + formatTitle(title, "");
            case "divers" -> "Dear Mx" + formatTitle(title, "");
            default -> "Dear";
        };
    }

    private String formatTitle(String title, String prefix) {
        if (title.isEmpty()) {
            return "";
        }
        if (title.contains("Prof.") || title.contains("Professor")) {
            return " Prof.";
        } else if (title.contains("Dr.") || title.contains("Doctor")) {
            return " Dr.";
        } else {
            return " " + title;
        }
    }
}

