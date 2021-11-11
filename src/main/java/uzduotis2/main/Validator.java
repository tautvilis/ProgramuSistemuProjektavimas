package uzduotis2.main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static java.util.Objects.isNull;
import static sun.util.locale.LocaleUtils.isEmpty;

public class Validator {

    public Validator() {
    }

    private String phoneNumber;

    public boolean passwordChecker(String password) {

        if(isNull(password)){
            return false;
        }

        if(password.length() <= 10){
            return false;
        };

        if(!checkerUppercase(password)){
            return true;
        }

        if(!checkerSpecialSymbols(password)){
            return false;
        }

        if(!checkerNoAllowedSymbols(password)){
            return false;
        }

        return true;
    }

    private boolean checkerNoAllowedSymbols(String password) {
        char[] specialChars = "|'~".toCharArray();
        return checkSpecialSymbols(password, specialChars);
    }

    private boolean checkerSpecialSymbols(String password) {
        char[] specialChars = "!@#$%^&?-".toCharArray();
        return checkSpecialSymbols(password, specialChars);
    }

    private boolean checkSpecialSymbols(String text, char[] specialChars) {
        for(int i = 0; i < text.length(); i++) {
            char currChar = text.charAt(i);
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
        if(isNull(phoneNumber)){
            return false;
        }
        if(isEmpty(phoneNumber)){
            return false;
        }
        setPhoneNumber(phoneNumber);
        if(!checkIfStartsWithPlus(phoneNumber)){
            if(!checkPhoneOnlyNumbers(phoneNumber)) {
                return false;
            }
        }else{
            if(phoneNumber.length() == 12){
                return true;
            }else if(phoneNumber.length() > 12){
                return false;
            }
            if(checkIfNotLTPrefix(phoneNumber)){
                return true;
            }
        }


        return true;
    }

    private boolean checkIfNotLTPrefix(String phoneNumber) {
        String prefix = phoneNumber.substring(0,4);
        if(prefix == "+370"){
            return false;
        }
        return true;
    }

    private boolean checkIfStartsWithPlus(String phoneNumber) {
        if(phoneNumber.charAt(0) == '+'){
            return true;
        }
        return false;
    }

    private boolean checkPhoneOnlyNumbers(String phoneNumber) {

        for(int i = 0; i < phoneNumber.length(); i++){
            if((Character.isDefined(phoneNumber.charAt(i)) == false)){

                return true;
            }
        }
        return false;
    }

    public boolean emailChecker(String email) {

        if(email == null){
            return false;
        }
        if(isEmpty(email)){
            return false;
        }
        if(!email.contains("@")) {
            return false;
        }
        if(!checkIfValidDomain(email)){
            return false;
        }
        if(!checkIfValidTLD(email)){
            return false;

        }
        return true;
    }

    private boolean checkIfValidTLD(String email) {
        String domain = email.substring(email.indexOf('@') + 1);
        try (BufferedReader br = new BufferedReader(new FileReader("src/uzduotis/uzduotis1.tests/tlds-alpha-by-domain.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(domain.endsWith(line.toLowerCase())){
                    return true;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean checkIfValidDomain(String email) {
        String invalidChars = "?,;|[]=#$%^&*~_/` ";
        String domain = email.substring(email.indexOf('@') + 1);
        if(!Character.isLetter(domain.charAt(0)) && !Character.isDigit(domain.charAt(0))){
            return false;
        }
        return true;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        String phone = phoneNumber.substring(1);
        String start = "+370";
        this.phoneNumber = start + phone;

    }


}
