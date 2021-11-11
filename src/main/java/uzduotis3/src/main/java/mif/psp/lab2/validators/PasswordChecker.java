package mif.psp.lab2.validators;

public class PasswordChecker {
    private final int defaultLength = 8;
    private final String defaultSpecialSymbols = "!@#$%^&*()+-";

    public boolean validatePassword(String password) {
        return validatePassword(password, defaultLength, defaultSpecialSymbols);
    }

    public boolean validatePassword(String password, int minLength) {
        return validatePassword(password, minLength, defaultSpecialSymbols);
    }

    public boolean validatePassword(String password, String specialSymbols) {
        return validatePassword(password, defaultLength, specialSymbols);
    }

    public boolean validatePassword(String password, int minLength, String specialSymbols) {
        if(password == null || password.equals("")) return false;

        if(password.length() < minLength) return false;

        char[] cArr = password.toCharArray();
        int counter = 0;

        for(int x = 0; x < password.length(); x++){
            if((int)cArr[x] >= 65 && (int)cArr[x] <= 90){
                counter++;
            }
        }

        if(counter == 0) return false;

        counter = 0;
        char[] symbolsArray = specialSymbols.toCharArray();
        for(int x = 0; x < password.length(); x++){
            for(int y = 0; y < specialSymbols.length(); y++){
                if(cArr[x] == symbolsArray[y]){
                    counter++;
                }
            }
        }

        if(counter == 0) return false;

        return true;
    }
}
