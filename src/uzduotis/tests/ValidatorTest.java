package uzduotis.tests;

import org.junit.Before;
import org.junit.Test;
import uzduotis.main.Validator;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ValidatorTest {

    Validator validator;

    @Before
    public void init() {
        validator = new Validator();
    }

    // password validator tests
    @Test
    public void passwordCheckerShorterThan10Test() {
        String password = "AbC1-25";
        assertFalse(validator.passwordChecker(password));
    }

    @Test
    public void passwordCheckerNoUppercaseLettersTest() {
        String password = "aaaa1-25bb";
        assertFalse(validator.passwordChecker(password));
    }

    @Test
    public void passwordCheckerNoSpecialSymbolsTest() {
        String password = "aaaA1a25bb";
        assertFalse(validator.passwordChecker(password));
    }

    @Test
    public void passwordCheckerNotAllowedSymbolTest() {
        String password = "|asd`";
        assertFalse(validator.passwordChecker(password));
    }

    @Test
    public void passwordCheckerValidPasswordTest() {
        String password = "ABC-123-abc";
        assertTrue(validator.passwordChecker(password));
    }

    @Test
    public void passwordCheckerEmptyTest() {
        String password = "   ";
        assertFalse(validator.passwordChecker(password));
    }

    @Test
    public void passwordCheckerNullTest() {
        assertFalse(validator.passwordChecker(null));
    }

    // phone number validator tests
    @Test
    public void phoneNumberCheckerContainsLettersTest() {
        String phoneNumber = "8655836A2";
        assertFalse(validator.phoneNumberChecker(phoneNumber));
    }

    @Test
    public void phoneNumberCheckerContainsNotAllowedSymbolsTest() {
        String phoneNumber = "$8655836A2";
        assertFalse(validator.phoneNumberChecker(phoneNumber));
    }

    @Test
    public void phoneNumberEmptyTest() {
        String phoneNumber = "  ";
        assertFalse(validator.phoneNumberChecker(phoneNumber));
    }

    @Test
    public void phoneNumberNullTest() {
        assertFalse(validator.phoneNumberChecker(null));
    }

    @Test
    public void phoneNumberCheckerConvertsPhoneNumberTest() {
        String phoneNumber = "865583652";
        validator.phoneNumberChecker(phoneNumber);
        assertEquals(validator.getPhoneNumber(), "+37065583652");
    }

    @Test
    public void phoneNumberCheckerLTPrefixValidatorTest() {
        String phoneNumber = "+37065583652";
        assertTrue(validator.phoneNumberChecker(phoneNumber));
    }

    @Test
    public void phoneNumberCheckerLTNumberLengthValidatorTest() {
        String phoneNumber = "+37065583652";
        assertTrue(validator.phoneNumberChecker(phoneNumber));
    }

    @Test
    public void phoneNumberCheckerLTNumberTooLongTest() {
        String phoneNumber = "+37065583652423423";
        assertFalse(validator.phoneNumberChecker(phoneNumber));
    }

    @Test
    public void phoneNumberCheckerOtherPrefixValidatorTest() {
        String phoneNumber = "+5365583652";
        assertTrue(validator.phoneNumberChecker(phoneNumber));
    }

    // email checker tests
    @Test
    public void emailCheckerNoSpecialSymbolTest() {
        String email = "asd.yahoo.com";
        assertFalse(validator.emailChecker(email));
    }

    @Test
    public void emailCheckerWrongDomainTest() {
        String email = "asd@.yahoo.yahoo.com";
        assertFalse(validator.emailChecker(email));
    }

    @Test
    public void emailCheckerWrongTLDTest() {
        String email = "asd@yahoo.aa";
        assertFalse(validator.emailChecker(email));
    }

    @Test
    public void emailCheckerNotAllowedSymbolsTest() {
        String email = "asd@!@#$Šyahoo.com";
        assertFalse(validator.emailChecker(email));
    }

    @Test
    public void emailCheckerValidEmailAddressTest() {
        String email = "veronika.sumilova@mif.stud.vu.lt";
        assertTrue(validator.emailChecker(email));
    }

    @Test
    public void emailCheckerEmptyTest() {
        String email = "     ";
        assertFalse(validator.emailChecker(email));
    }

    @Test
    public void emailCheckerNullTest() {
        assertFalse(validator.emailChecker(null));
    }
}