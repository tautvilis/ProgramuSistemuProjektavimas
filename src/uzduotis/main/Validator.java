package uzduotis.main;

import java.util.Locale;

public class Validator {

    public Validator() {
    }

    private String phoneNumber;

    public boolean passwordChecker(String password) {


        if(password.length() <= 10){
            return false;
        };

        if(!checkerUppercase(password)){
            return true;
        }

        if(!checkerSpecialSymbols(password)){
            return false;
        }

        return true;
    }

    private boolean checkerSpecialSymbols(String password) {
        char[] specialChars = "!@#$%^&?".toCharArray();
        for(int i = 0; i < password.length(); i++) {
            char currChar = password.charAt(i);
            for(int j = 0; j < specialChars.length; j++) {
                if(currChar == specialChars[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkerUppercase(String password) {
        if(password == password.toLowerCase()){
            return true;
        }
        return false;
    }

    public boolean phoneNumberChecker(String phoneNumber) {
        setPhoneNumber(phoneNumber);
        return true;
    }

    public boolean emailChecker(String email) {
        return true;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
