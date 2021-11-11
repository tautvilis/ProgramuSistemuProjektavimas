package mif.psp.lab2.validators;

public class PhoneValidator {
    public boolean validateLithuanianPhoneNumber(String phoneNumber) {
        if(phoneNumber == null || phoneNumber.equals("")) return false;

        if(!phoneNumber.startsWith("+370") && !phoneNumber.startsWith("8")) return false;

        char[] cArr = phoneNumber.toCharArray();

        int counter = 0;

        for(int x = 0; x < phoneNumber.length(); x++){
            if((int)cArr[x] >= 48 && (int)cArr[x] <= 57){
                counter++;
            }
        }

        if(phoneNumber.startsWith("+370") && counter != (phoneNumber.length() - 1)){
            return false;
        }

        if(phoneNumber.startsWith("8") && counter != phoneNumber.length()){
            return false;
        }

        return true;
    }

    public String changeLocalLithuanianNumberToInternational(String phoneNumber) {
        if(!validateLithuanianPhoneNumber(phoneNumber)) return phoneNumber;

        if(phoneNumber.startsWith("+370")) return phoneNumber;

        String newNumber = "+370";
        return newNumber.concat(phoneNumber.substring(1));
    }

    public boolean validateInternationalPhoneNumber(String phoneNumber, String prefix, int length) {
        if(phoneNumber == null || phoneNumber.equals("")) return false;
        if(phoneNumber.length() != length) return false;
        if(prefix == null || prefix.equals("")) return false;
        if(length < 1) return false;

        if(!phoneNumber.startsWith(prefix)) return false;

        return true;
    }
}
