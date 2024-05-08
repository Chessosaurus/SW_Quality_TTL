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

    private static String createGermanSalutation(String gender, String title) {
        return switch (gender) {
            case "f" -> "Sehr geehrte Frau" + formatTitle(title);
            case "m" -> "Sehr geehrter Herr" + formatTitle(title);
            default -> "Guten Tag" + formatTitle(title);
        };
    }

    private static String createEnglishSalutation(String gender, String title) {
        return switch (gender) {
            case "f" -> "Dear Mrs" + formatTitle(title);
            case "m" -> "Dear Mr" + formatTitle(title);
            case "d" -> "Dear Mx" + formatTitle(title);
            default -> "Dear" + formatTitle(title);
        };
    }

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

