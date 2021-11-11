package uzduotis1.main;

public class PasswordChecker {
    public PasswordChecker() {
    }

    public boolean passwordLength(String password, int length) {
        return password.length() >= length;
    }

    public boolean hasUpperCase(String password) {
        return !password.equals(password.toLowerCase());
    }

    public boolean hasSpecialCharacter(String password) {
        return true;
    }
}

