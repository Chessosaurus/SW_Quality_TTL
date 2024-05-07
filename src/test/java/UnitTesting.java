import backend.ContactController;
import backend.WrongInputException;
import backend.converter.InputConverter;
import backend.model.Contact;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class UnitTesting {

    ContactController cc = new ContactController();

    @Test
    public void testName_one() throws WrongInputException {
        String name = "Frau Sandra Berger";
        Contact actual = cc.convertToContact(name);
        assertTrue(new ContactMatcher()
                .withFirstName("Sandra")
                .withLastName("Berger")
                .withGender("weiblich")
                .withLanguage("de")
                .withTitles(new ArrayList<String>())
                .withSalutation("Frau")
                .withLetterSalutation("Sehr geehrte Frau Berger")
                .matches(actual)
        );
    }
    @Test
    public void testName_two() throws WrongInputException {
        String name = "Herr Dr. Sandro Gutmensch";
        Contact actual = cc.convertToContact(name);
        assertTrue(new ContactMatcher()
                .withFirstName("Sandro")
                .withLastName("Gutmensch")
                .withGender("männlich")
                .withLanguage("de")
                .withTitles(List.of("Dr."))
                .withSalutation("Dr.")
                .withLetterSalutation("Sehr geehrter Herr Dr. Gutmensch")
                .matches(actual)
        );
    }

    class ContactMatcher {
        protected WithFirstName withFirstName(String firstName) {
            Contact c = new Contact();
            c.setFirstName(firstName);
            return new WithFirstName(c);
        }

        private class WithFirstName {
            Contact c;

            WithFirstName(Contact c) {
                this.c = c;
            }

            private WithLastName withLastName(String lastName) {
                c.setLastName(lastName);
                return new WithLastName(c);
            }
        }

        private class WithLastName {
            Contact c;

            WithLastName(Contact c) {
                this.c = c;
            }

            private WithGender withGender(String gender) {
                c.setGender(gender);
                return new WithGender(c);
            }
        }

        private class WithGender {
            Contact c;

            WithGender(Contact c) {
                this.c = c;
            }

            private WithLanguage withLanguage(String language) {
                c.setLanguage(language);
                return new WithLanguage(c);
            }
        }

        private class WithLanguage {
            Contact c;

            WithLanguage(Contact c) {
                this.c = c;
            }

            private WithTitles withTitles(List<String> titles) {
                c.setTitles(titles);
                return new WithTitles(c);
            }
        }

        private class WithTitles {
            Contact c;

            WithTitles(Contact c) {
                this.c = c;
            }

            private WithSalutation withSalutation(String salutation) {
                c.setSalutation(salutation);
                return new WithSalutation(c);
            }
        }

        private class WithSalutation {
            Contact c;

            WithSalutation(Contact c) {
                this.c = c;
            }

            private ContactMatching withLetterSalutation(String letterSalutation) {
                c.setLetterSalutation(letterSalutation);
                return new ContactMatching(c);
            }
        }

        private class ContactMatching {
            Contact expected;

            ContactMatching(Contact c) {
                this.expected = c;
            }

            private boolean matches(Contact actual) {
                if (!expected.getFirstName().equals(actual.getFirstName())) return false;
                if (!expected.getLastName().equals(actual.getLastName())) return false;
                if (!expected.getGender().equals(actual.getGender())) return false;
                if (!expected.getLetterSalutation().equals(actual.getLetterSalutation())) return false;
                if (!expected.getLanguage().equals(actual.getLanguage())) return false;
                if (!expected.getSalutation().equals(actual.getSalutation())) return false;
                if (!expected.getTitles().equals(actual.getTitles())) return false;
                return true;
            }
        }
    }
}
