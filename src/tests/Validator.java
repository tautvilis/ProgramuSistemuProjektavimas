package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import main.EmailChecker;
import main.PasswordChecker;
import main.PhoneChecker;

class Validator {
    PasswordChecker pwd = new PasswordChecker();
    PhoneChecker phone = new PhoneChecker();
    EmailChecker email = new EmailChecker();

    Validator() {
    }

    @Test
    void passwordValidator_isLongerThanX() {
        Assertions.assertTrue(this.pwd.passwordLength("password", 8));
    }

    @Test
    void passwordValidator_hasUppercase() {
        Assertions.assertTrue(this.pwd.hasUpperCase("Password"));
    }

    @Test
    void passwordValidator_hasSpecialCharacters() {
        Assertions.assertTrue(this.pwd.hasSpecialCharacter("Password@"));
    }

    @Test
    void phoneValidator_isNotEmpty() {
        Assertions.assertTrue(this.phone.isNotEmpty("+37061234567"));
    }

    @Test
    void phoneValidator_hasOnlyNumbers() {
        Assertions.assertTrue(this.phone.onlyNumbers("861234567"));
    }

    @Test
    void phoneValidator_isNotLongerThanX() {
        Assertions.assertTrue(this.phone.numberLength("+37061234567", "Lithuania"));
    }

    @Test
    void phoneValidator_validCountryCode() {
        Assertions.assertTrue(this.phone.checkCountryCode("+37061234567", "Lithuania"));
    }

    @Test
    void emailValidator_isNotEmpty() {
        Assertions.assertTrue(this.email.notEmpty("email@email.com"));
    }

    @Test
    void emailValidator_hasAtSign() {
        Assertions.assertTrue(this.email.hasAtSign("email@email.com"));
    }

    @Test
    void emailValidator_hasNoBadSymbols() {
        Assertions.assertTrue(this.email.checkBadSymbols("email@email.com"));
    }

    @Test
    void emailValidator_hasCorrectTLD() {
        Assertions.assertTrue(this.email.correctTLDCheck("email@email.com"));
    }

    @Test
    void emailValidator_hasCorrectDomain() {
        Assertions.assertTrue(this.email.correctDomainCheck("email@email.com"));
    }
}
