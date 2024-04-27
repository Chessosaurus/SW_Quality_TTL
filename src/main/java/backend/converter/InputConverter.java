package backend.converter;

import backend.model.Contact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        String[] salutationValueInit = {"Herr", "Mr.", "Mr", "Mister","Frau", "Mrs.", "Mrs", "Mme.", "Mme"};
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
     * @param newTitle The title to be added.
     */
    public void addTitle(String newTitle) {
        if (!titles.contains(newTitle)) titles.add(newTitle);
    }

    /**
     * Converts a single string input into a Contact object by splitting the input and determining
     * the gender, language, titles, and names of the individual.
     * <p>
     * @param input The string input to be converted.
     * @return A Contact object populated with the extracted details from the input.
     */
    public Contact convert(String input) {

        String[] splittedInput = input.split(" ");

        Contact contact = new Contact();
        if (splittedInput.length != 1) {
            contact.setSalutation(findSalutation(splittedInput[0]));
            contact.setGender(findGender(splittedInput[0]));
            contact.setLanguage(findLanguage(splittedInput[0]));
            contact.setTitles(findTitles(splittedInput));
            String[] names = findNames(splittedInput);
            contact.setFirstName(names[0]);
            contact.setLastName(names[1]);

        } else {
            contact.setLastName(splittedInput[0]);
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
     * @param input The string part to be analyzed for gender.
     * @return A single character string representing male ('m'), female ('f'), or undetermined ('x').
     */
    public String findGender(String input) {
        if (maleGenders.contains(input)) return "männlich";
        else if (femaleGenders.contains(input)) return "weiblich";
        else if(diversGenders.contains(input)) return "divers";
        else return "keine Angabe";
    }





    /**
     * Determines the language from a given input string based on predefined language identifiers.
     * <p>
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
     * @param input An array of strings representing different parts of a full name.
     * @return A string array where index 0 contains the firstname and index 1 contains the lastname.
     */
    public String[] findNames(String[] input) {
        String[] nameTuple = {"", ""};
        List<String> potentialNames = new ArrayList<>();
        List<String> connectorsAndPrefixes = new ArrayList<>();

        for (String part : input) {
            if (part.matches("[A-ZÄÖÜ][a-zäöü]*[-]*[A-ZÄÖÜ]?[a-zäöü]*") && !isGenderOrTitle(part)) {
                potentialNames.add(part);
            } else if (part.matches("[a-z]{1,3}")) {
                connectorsAndPrefixes.add(part);
            } else if (part.endsWith(",")) {
                nameTuple[1] = part.substring(0, part.length() - 1);
            }
        }

        processNames(potentialNames, connectorsAndPrefixes, nameTuple);
        return nameTuple;
    }

    /**
     * Processes a list of potential names and connectors to determine the correct firstname and lastname.
     * <p>
     * @param names A list of potential names.
     * @param connectors A list of recognized connectors.
     * @param nameTuple A string array where the firstname and lastname will be stored.
     */
    private void processNames(List<String> names, List<String> connectors, String[] nameTuple) {
        if (names.size() == 2) {
            nameTuple[0] = names.get(0);
            nameTuple[1] = names.get(1);
        } else if (names.size() == 1 && nameTuple[1].isEmpty()) {
            nameTuple[1] = names.getFirst(); // No Firstname given
        } else {
            buildNameFromParts(names, connectors, nameTuple);
        }
    }

    /**
     * Constructs a full name from parts, combining recognized names with connectors when applicable.
     * <p>
     * @param names A list of names that might include parts to be combined.
     * @param connectors A list of strings that signify connectors between name parts.
     * @param nameTuple A string array to hold the resulting firstname and lastname.
     */
    private void buildNameFromParts(List<String> names, List<String> connectors, String[] nameTuple) {
        List<String> combinedNames = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).matches("[A-ZÄÖÜ][a-zäöü]*[-]*[A-ZÄÖÜ]?[a-zäöü]*")) {
                if (i + 1 < names.size() && connectors.contains(names.get(i + 1))) {
                    combinedNames.add(names.get(i) + " " + names.get(i + 1));
                    i++;
                } else {
                    combinedNames.add(names.get(i));
                }
            }
        }
        if (nameTuple[1].isEmpty() && !combinedNames.isEmpty()) {
            nameTuple[1] = combinedNames.removeLast();
        }
        nameTuple[0] = String.join(" ", combinedNames);
    }

    /**
     * Checks if a given part of the input is recognized as a gender term or a title.
     * <p>
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
     * @param input An array of strings, each potentially a part of a title.
     * @return A list of extracted and combined titles.
     */
    public List<String> findTitles(String[] input) {
        List<String> generatedTitles = new ArrayList<String>();
        for (int i = 0; i < input.length; i++) { // iterate over all parts of the Input Array
            if (input[i].equals("Prof.")) {
                if (input[i + 1].equals("Dr.")) {
                    i++;
                    String tempTitle = "Prof. Dr.";
                    //determine a more complex title if existent
                    i = findComplexTitle(input, generatedTitles, i, tempTitle);
                } else {
                    generatedTitles.add(input[i]);
                }
            } else if (input[i].equals("Dr.")) {
                String tempTitle = "Dr.";
                //determine a more complex title if existent
                i = findComplexTitle(input, generatedTitles, i, tempTitle);
            } else if ((input[i].matches("[a-zA-Z.]+\\.")
                    && !maleGenders.contains(input[i])
                    && !diversGenders.contains(input[i])
                    && !femaleGenders.contains(input[i])
                    || titles.contains(input[i]))) {
                //check if the current potential title is not a salutation and the title ends with a '.'
                generatedTitles.add(input[i]);
            }
        }
        return generatedTitles;
    }


    /**
     * Supports the findTitles method by combining consecutive title parts into complex titles.
     * <p>
     * @param inputParts An array of title parts.
     * @param titles A list to store combined titles.
     * @param currentIndex The current index in the input parts array being processed.
     * @param currentTitle The current title part being combined.
     * @return The updated index after processing possible continuations of the current title.
     */
    private int findComplexTitle(String[] inputParts, List<String> titles, int currentIndex, String currentTitle) {
        for (int j = currentIndex + 1; j < inputParts.length; j++) {
            if (inputParts[j].matches("[a-z.]+\\.")) {
                //combine titles
                currentTitle += " ";
                currentTitle += inputParts[j];
            } else {
                //determine oiteration variable to continue where it stopped
                currentIndex = j - 1;
                break;
            }
        }
        titles.add(currentTitle);
        return currentIndex;
    }
}


