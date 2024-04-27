package backend;

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

        contact.setSalutation(baseSalutation + " " + contact.getFirstName() + ",");
    }

    private String createGermanSalutation(String gender, String title) {
        switch (gender) {
            case "f":
                return "Sehr geehrte Frau" + formatTitle(title, "Frau");
            case "m":
                return "Sehr geehrter Herr" + formatTitle(title, "Herr");
            default:
                return "Guten Tag";
        }
    }

    private String createEnglishSalutation(String gender, String title) {
        switch (gender) {
            case "f":
                return "Dear Mrs" + formatTitle(title, "");
            case "m":
                return "Dear Mr" + formatTitle(title, "");
            case "x":
                return "Dear Mx" + formatTitle(title, "");
            default:
                return "Dear";
        }
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

