package unittest;

import backend.ContactController;
import backend.WrongInputException;
import backend.model.Contact;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UnitTesting {
    ContactController cc = new ContactController();

    @Test
    public void testName_one() throws WrongInputException {
        String name = "Frau Sandra Berger";
        Contact actual = cc.convertToContact(name);
        new ContactMatcher()
                .withFirstName("Sandra")
                .withLastName("Berger")
                .withGender("f")
                .withLanguage("de")
                .withTitles(new ArrayList<String>())
                .withSalutation("Frau")
                .withLetterSalutation("Sehr geehrte Frau Berger")
                .matches(actual);
    }

    @Test
    public void testName_two() throws WrongInputException {
        String name = "Herr Dr. Sandro Gutmensch";
        Contact actual = cc.convertToContact(name);
        new ContactMatcher()
                .withFirstName("Sandro")
                .withLastName("Gutmensch")
                .withGender("m")
                .withLanguage("de")
                .withTitles(List.of("Dr."))
                .withSalutation("Herr")
                .withLetterSalutation("Sehr geehrter Herr Dr. Gutmensch")
                .matches(actual);
    }

    @Test
    public void testName_three() throws WrongInputException {
        String name = "Professor Heinrich Freiherr vom Wald";
        Contact actual = cc.convertToContact(name);
        new ContactMatcher()
                .withFirstName("Heinrich")
                .withLastName("Freiherr vom Wald")
                .withGender("keine Angabe")
                .withLanguage("de")
                .withTitles(List.of("Professor"))
                .withSalutation("")
                .withLetterSalutation("Guten Tag Prof. Freiherr vom Wald")
                .matches(actual);

    }
    @Test
    public void testName_four() throws WrongInputException {
        String name = "Mrs. Doreen Faber";
        Contact actual = cc.convertToContact(name);
        new ContactMatcher()
                .withFirstName("Doreen")
                .withLastName("Faber")
                .withGender("f")
                .withLanguage("en")
                .withTitles(new ArrayList<>())
                .withSalutation("Mrs.")
                .withLetterSalutation("Dear Mrs Faber")
                .matches(actual);

    }
    @Test
    public void testName_five() throws WrongInputException {
        String name = "Mme. Charlotte Noir";
        Contact actual = cc.convertToContact(name);
        new ContactMatcher()
                .withFirstName("Charlotte")
                .withLastName("Noir")
                .withGender("f")
                .withLanguage("en")
                .withTitles(new ArrayList<>())
                .withSalutation("Mme.")
                .withLetterSalutation("Dear Mrs Noir")
                .matches(actual);

    }
    @Test
    public void testName_six() throws WrongInputException {
        String name = "Frau Prof. Dr. rer. nat. Maria von Leuthäuser-Schnarrenberger";
        Contact actual = cc.convertToContact(name);
        new ContactMatcher()
                .withFirstName("Maria")
                .withLastName("von Leuthäuser-Schnarrenberger")
                .withGender("f")
                .withLanguage("de")
                .withTitles(List.of("Prof. Dr. rer. nat."))
                .withSalutation("Frau")
                .withLetterSalutation("Sehr geehrte Frau Prof. von Leuthäuser-Schnarrenberger")
                .matches(actual);
    }
    @Test
    public void testName_seven() throws WrongInputException {
        String name = "Herr Dipl.-Ing. Max von Müller";
        Contact actual = cc.convertToContact(name);
        new ContactMatcher()
                .withFirstName("Max")
                .withLastName("von Müller")
                .withGender("m")
                .withLanguage("de")
                .withTitles(List.of("Dipl.-Ing."))
                .withSalutation("Herr")
                .withLetterSalutation("Sehr geehrter Herr Dipl.-Ing. von Müller")
                .matches(actual);
    }
    @Test
    public void testName_eight() throws WrongInputException {
        String name = "Dr. Russwurm, Winfried";
        Contact actual = cc.convertToContact(name);
        new ContactMatcher()
                .withFirstName("Winfried")
                .withLastName("Russwurm")
                .withGender("keine Angabe")
                .withLanguage("de")
                .withTitles(List.of("Dr."))
                .withSalutation("")
                .withLetterSalutation("Guten Tag Dr. Russwurm")
                .matches(actual);
    }
    @Test
    public void testName_nine() throws WrongInputException {
        String name = "Herr Dr.-Ing. Dr. rer. nat. Dr. h.c. mult. Paul Steffens";
        Contact actual = cc.convertToContact(name);
        new ContactMatcher()
                .withFirstName("Paul")
                .withLastName("Steffens")
                .withGender("m")
                .withLanguage("de")
                .withTitles(List.of("Dr.-Ing.","Dr. rer. nat.","Dr. h.c. mult."))
                .withSalutation("Herr")
                .withLetterSalutation("Sehr geehrter Herr Dr. Steffens")
                .matches(actual);
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

            private void matches(Contact actual) {
                assertEquals(expected.getFirstName(), actual.getFirstName());
                assertEquals(expected.getLastName(), actual.getLastName());
                assertEquals(expected.getGender(), actual.getGender());
                assertEquals(expected.getLanguage(), actual.getLanguage());
                assertEquals(expected.getSalutation(), actual.getSalutation());
                assertEquals(expected.getLetterSalutation(), actual.getLetterSalutation());
                assertEquals(expected.getTitles(), actual.getTitles());
            }
        }
    }
}
