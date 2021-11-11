package mif.psp.lab2.validators;

import java.util.Arrays;
import java.util.List;

public class EmailValidator {

    public boolean validateEmail(String email) {
        if(email == null || email.equals("")) return false;

        int atIndex = checkAtSymbols(email);

        if(atIndex <= 0)
            return false;

        String recipientsName = email.substring(0, atIndex);
        String domainName_TLD = email.substring(atIndex + 1);

        if(!validateRecipientsName(recipientsName)) return false;
        if(!validateDomainName_TLD(domainName_TLD)) return false;

        return true;
    }
    private int checkAtSymbols(String email){
        char[] cArr = email.toCharArray();

        int counter = 0;
        int atIndex = 0;

        for(int x = 0; x < email.length(); x++){
            if(cArr[x] == '@'){
                counter++;
                atIndex = x;
            }
        }

        if(counter != 1)
            return -1;
        else
            return atIndex;
    }

    private boolean validateRecipientsName(String recipientsName){
        if(recipientsName.length() > 64) return false;

        char[] cArr = recipientsName.toCharArray();
        String specialSymbols = "\"(),:;<>@[\\]";
        char[] specialSymbolsArr = specialSymbols.toCharArray();

        for(int x = 0; x < recipientsName.length(); x++){
            for(int y = 0; y < specialSymbols.length(); y++){
                if(cArr[x] == specialSymbolsArr[y]) return false;
            }
            if((int)cArr[x] >= 48 && (int)cArr[x] <= 57)
                continue;
            else if((int)cArr[x] >= 65 && (int)cArr[x] <= 90)
                continue;
            else if((int)cArr[x] >= 97 && (int)cArr[x] <= 122)
                continue;
            else return false;
        }

        return true;
    }

    private boolean validateDomainName_TLD(String domain_TLD){
        if(domain_TLD.length() > 253) return false;

        List<String> tldList = Arrays.asList(".lt", ".net", ".com", ".uk", ".org");

        String ending = "";

        for(String tld : tldList){
            if(domain_TLD.endsWith(tld)){
                ending = tld;
                break;
            }
        }

        if(ending.equals("")) return false;

        String domainName = domain_TLD.substring(0, (domain_TLD.length() - ending.length()));

        if(domainName.equals("")) return false;

        if(domainName.startsWith("-")) return false;
        
        char[] cArr = domainName.toCharArray();

        for(int x = 0; x < domainName.length(); x++){
            if((int)cArr[x] >= 48 && (int)cArr[x] <= 57) continue;
            else if((int)cArr[x] >= 65 && (int)cArr[x] <= 90) continue;
            else if((int)cArr[x] >= 97 && (int)cArr[x] <= 122) continue;
            else if(cArr[x] == '-' || cArr[x] == '.') continue;
            else return false;
        }


        return true;
    }
}
