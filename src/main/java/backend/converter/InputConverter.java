package backend.converter;

import backend.model.Contact;

import java.util.*;

/**
 * This class serves as a converter for processing names and titles from string input.
 * It initializes and maintains lists of gender-specific terms, titles, prefixes, and connectors
 * to aid in parsing and converting names from various formats.
 * <p>
 */
public class InputConverter {

    List<String> maleGenders;
    List<String> femaleGenders;
    List<String> diversGenders;
    List<String> titles;
    List<String> german;
    List<String> english;
    List<String> prefixes;
    List<String> connectors;
    List<String> salutation;

    /**
     * Constructor for InputConverter. Initializes lists with predefined values for genders, titles, languages,
     * prefixes, and connectors.
     * <p>
     */
    public InputConverter() {
        //Initializing Prerequisites
        String[] maleInitValues = {"Herr", "Mr.", "Mr", "Mister"};
        String[] femaleInitValues = {"Frau", "Mrs.", "Mrs", "Mme.", "Mme"};
        String[] diversInitValues = {"Mx.", "Mx"};
        String[] titlesInitValues = {"Prof.", "Dr.", "Professor", "Doktor", "Dr.-Ing.", "Dipl.-Ing.", "Dr.-Ing."};
        String[] germanLanguage = {"Frau", "Herr"};
        String[] englishLanguage = {"Mr.", "Mr", "Mister", "Mrs.", "Mrs", "Mme.", "Mme", "Mx.", "Mx"};
        String[] prefixesValueInit = {"van", "von", "der", "de", "y"};
        String[] connectorsValueInit = {"von", "vom"};
        String[] salutationValueInit = {"Herr", "Mr.", "Mr", "Mister", "Frau", "Mrs.", "Mrs", "Mme.", "Mme"};
        maleGenders = Arrays.asList(maleInitValues);
        femaleGenders = Arrays.asList(femaleInitValues);
        diversGenders = Arrays.asList(diversInitValues);
        titles = Arrays.asList(titlesInitValues);
        german = Arrays.asList(germanLanguage);
        english = Arrays.asList(englishLanguage);
        prefixes = Arrays.asList(prefixesValueInit);
        connectors = Arrays.asList(connectorsValueInit);
        salutation = Arrays.asList(salutationValueInit);
    }

    /**
     * Adds a new title to the list of recognized titles if it is not already present.
     * <p>
     *
     * @param newTitle The title to be added.
     */
    public void addTitle(String newTitle) {
        if (!titles.contains(newTitle)) titles.add(newTitle);
    }

    /**
     * Converts a single string input into a Contact object by splitting the input and determining
     * the gender, language, titles, and names of the individual.
     * <p>
     *
     * @param input The string input to be converted.
     * @return A Contact object populated with the extracted details from the input.
     */
    public Contact convert(String input) {

        List<String> splittedInput = new ArrayList<>(Arrays.asList(input.split("\\s+")));

        Contact contact = new Contact();
        if (splittedInput.size() != 1) {
            contact.setSalutation(findSalutation(splittedInput.getFirst()));
            contact.setGender(findGender(splittedInput.getFirst()));
            contact.setLanguage(findLanguage(splittedInput.getFirst()));
            contact.setTitles(findTitles(splittedInput));

            removeNonNameEntries(splittedInput, contact);
            String[] names = parseName(splittedInput);
            contact.setFirstName(names[0]);
            contact.setLastName(names[1]);

        } else {
            contact.setLastName(splittedInput.getFirst());
            contact.setGender("d");
        }
        return contact;
    }


    private String findSalutation(String input) {
        if (salutation.contains(input)) return input;
        else return "";
    }

    /**
     * Determines the gender based on a given input string by checking it against predefined lists.
     * <p>
     *
     * @param input The string part to be analyzed for gender.
     * @return A single character string representing male ('m'), female ('f'), or undetermined ('x').
     */
    public String findGender(String input) {
        if (maleGenders.contains(input)) return "männlich";
        else if (femaleGenders.contains(input)) return "weiblich";
        else if (diversGenders.contains(input)) return "divers";
        else return "keine Angabe";
    }


    /**
     * Determines the language from a given input string based on predefined language identifiers.
     * <p>
     *
     * @param input input The string part to be analyzed for language.
     * @return The ISO code for the detected language, or 'undefined' if not found.
     */
    public String findLanguage(String input) {
        if (german.contains(input)) return "de";
        else if (english.contains(input)) return "en";
        else return "undefined";
    }

    /**
     * Parses an array of string inputs to extract and separate potential firstnames and lastnames.
     * Handles names with prefixes and connectors appropriately.
     * <p>
     *
     * @param input A list of strings representing different parts of a full name.
     * @return A string array where index 0 contains the firstname and index 1 contains the lastname.
     */
    public String[] parseName(List<String> input) {
        String[] nameTuple = {"", ""};
        // Handle last name with potential commas
        for (int i = input.size() - 1; i >= 0; i--) {
            String currentWord = input.get(i);
            // Check if word has a comma
            if (currentWord.contains(",")) {
                // remove comma and set lastname
                currentWord = currentWord.replace(",", "");
                if (nameTuple[1].isEmpty()) {
                    nameTuple[1] = currentWord;
                    input.remove(i);
                } else {
                    nameTuple[1] = currentWord + " " + nameTuple[1];
                    input.remove(i);
                }
            }
        }
        // Handle last name with potential and connectors
        for (int i = input.size() - 1; i >= 0; i--) {
            if (prefixes.contains(input.get(i).toLowerCase())) {
                if (nameTuple[1].isEmpty()) {
                    nameTuple[1] = input.get(i);
                    input.remove(i);
                } else {
                    nameTuple[1] = input.get(i) + " " + nameTuple[1];
                    input.remove(i);
                }
            } else if (connectors.contains(input.get(i).toLowerCase())) {
                if (nameTuple[1].isEmpty()) {
                    nameTuple[1] = input.get(i);
                    input.remove(i);
                } else {
                    nameTuple[1] = input.get(i - 1) + " " + input.get(i) + " " + nameTuple[1];
                    input.removeLast();
                    input.remove(i - 1);
                    i--;
                }
            } else if (nameTuple[1].isEmpty()) {
                nameTuple[1] = input.get(i);
                input.remove(i);
            } else {
                break;
            }
        }
        // Assign firstname
        if (!input.isEmpty()) {
            int size = input.size();
            for (int j = 0; j < size; j++) {
                if (nameTuple[0].isEmpty()) {
                    nameTuple[0] = input.getFirst();
                    input.removeFirst();
                } else {
                    nameTuple[0] += "-" + input.getFirst();
                    input.removeFirst();
                }
            }
        }
        return nameTuple;
    }

    /**
     * Checks if a given part of the input is recognized as a gender term or a title.
     * <p>
     *
     * @param part The string to check against known genders and titles.
     * @return true if the string matches a known gender or title, false otherwise.
     */
    private boolean isGenderOrTitle(String part) {
        return maleGenders.contains(part)
                || femaleGenders.contains(part)
                || diversGenders.contains(part)
                || titles.contains(part);

    }

    /**
     * Extracts titles from an array of input strings, combining adjacent titles into a single title string where applicable.
     * <p>
     *
     * @param input An array of strings, each potentially a part of a title.
     * @return A list of extracted and combined titles.
     */
    public List<String> findTitles(List<String> input) {
        List<String> generatedTitles = new ArrayList<String>();
        for (int i = 0; i < input.size(); i++) { // iterate over all parts of the Input Array
            if (input.get(i).equals("Prof.")) {
                if (input.get(i + 1).equals("Dr.")) {
                    i++;
                    String tempTitle = "Prof. Dr.";
                    //determine a more complex title if existent
                    i = findComplexTitle(input, generatedTitles, i, tempTitle);
                } else {
                    generatedTitles.add(input.get(i));
                }
            } else if (input.get(i).equals("Dr.")) {
                String tempTitle = "Dr.";
                //determine a more complex title if existent
                i = findComplexTitle(input, generatedTitles, i, tempTitle);
            } else if ((input.get(i).matches("[a-zA-Z.]+\\.")
                    && !maleGenders.contains(input.get(i))
                    && !diversGenders.contains(input.get(i))
                    && !femaleGenders.contains(input.get(i))
                    || titles.contains(input.get(i)))) {
                //check if the current potential title is not a salutation and the title ends with a '.'
                generatedTitles.add(input.get(i));
            }
        }

        return generatedTitles;
    }


    /**
     * Supports the findTitles method by combining consecutive title parts into complex titles.
     * <p>
     *
     * @param inputParts   An array of title parts.
     * @param titles       A list to store combined titles.
     * @param currentIndex The current index in the input parts array being processed.
     * @param currentTitle The current title part being combined.
     * @return The updated index after processing possible continuations of the current title.
     */
    private int findComplexTitle(List<String> inputParts, List<String> titles, int currentIndex, String currentTitle) {
        for (int j = currentIndex + 1; j < inputParts.size(); j++) {
            if (inputParts.get(j).matches("[a-z.]+\\.")) {
                //combine titles
                currentTitle += " ";
                currentTitle += inputParts.get(j);
            } else {
                //determine iteration variable to continue where it stopped
                currentIndex = j - 1;
                break;
            }
        }
        titles.add(currentTitle);
        return currentIndex;
    }

    /**
     * @param inputList
     * @param contact
     */
    private void removeNonNameEntries(List<String> inputList, Contact contact) {
        Set<String> nonNameEntries = new HashSet<>();
        nonNameEntries.add(contact.getSalutation());
        nonNameEntries.addAll(contact.getTitles());
        nonNameEntries.add(contact.getGender());
        nonNameEntries.add(contact.getLanguage());
        Set<String> nonNameEntries2 = new HashSet<>();
        for (String nonNameEntry : nonNameEntries) {
            nonNameEntries2.addAll(Arrays.asList(nonNameEntry.split(" ")));
        }
        inputList.removeIf(nonNameEntries2::contains);
    }
}


