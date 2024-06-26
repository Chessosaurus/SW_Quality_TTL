package backend.converter;

import backend.model.Contact;

/**
 * Class responsible for generating salutations for Contact objects based on their gender,
 * names, language, and titles.
 */
public abstract class SalutationBuilder {

    /**
     * Creates a salutation for the input Contact object based on its gender, language, and titles.
     *
     * @param contact The Contact object for which the salutation is created.
     */
    public static void createSalutation(Contact contact) {
        String title = contact.getTitles().isEmpty() ? "" : contact.getTitles().getFirst();
        String baseSalutation = "Guten Tag";

        if (contact.getLanguage().equals("de")) {
            baseSalutation = createGermanSalutation(contact.getGender(), title);
        } else if (contact.getLanguage().equals("en")) {
            baseSalutation = createEnglishSalutation(contact.getGender(), title);
        } else if (!title.isEmpty()) {
            baseSalutation = "Guten Tag" + formatTitle(title) + contact.getFirstName();
        }
        contact.setLetterSalutation(baseSalutation + " " + contact.getLastName());
    }

    /**
     * Generates a German salutation based on the gender and title of the contact.
     *
     * @param gender The gender of the contact.
     * @param title  The title of the contact, if any.
     * @return A string containing the formal German salutation.
     */
    private static String createGermanSalutation(String gender, String title) {
        return switch (gender) {
            case "f" -> "Sehr geehrte Frau" + formatTitle(title);
            case "m" -> "Sehr geehrter Herr" + formatTitle(title);
            default -> "Guten Tag" + formatTitle(title);
        };
    }

    /**
     * Generates an English salutation based on the gender and title of the contact.
     *
     * @param gender The gender of the contact.
     * @param title  The title of the contact, if any.
     * @return A string containing the formal English salutation.
     */
    private static String createEnglishSalutation(String gender, String title) {
        return switch (gender) {
            case "f" -> "Dear Mrs" + formatTitle(title);
            case "m" -> "Dear Mr" + formatTitle(title);
            case "d" -> "Dear Mx" + formatTitle(title);
            default -> "Dear" + formatTitle(title);
        };
    }

    /**
     * Formats a title to include a proper salutation prefix. If a specific title like "Prof."
     * or "Dr." is found, it adds the correct abbreviation. Otherwise, it appends the title
     * as provided.
     *
     * @param title The title to be formatted.
     * @return A formatted title string suitable for salutations.
     */
    private static String formatTitle(String title) {
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

