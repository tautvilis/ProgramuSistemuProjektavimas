package mif.psp.lab2.controllers;

import mif.psp.lab2.model.Account;
import mif.psp.lab2.repository.AccountRepository;
import mif.psp.lab2.validators.EmailValidator;
import mif.psp.lab2.validators.PasswordChecker;
import mif.psp.lab2.validators.PhoneValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountRestController {

    private AccountRepository accountRepository;

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private PasswordChecker passwordChecker;

    @Autowired
    private PhoneValidator phoneValidator;

    AccountRestController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/accounts")
    List<Account> allAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping("/account/{id}")
    Account getAccount(@PathVariable Long id) throws Exception {
        return accountRepository.findById(id).orElseThrow(() -> new Exception(("ID not found: ") + id));
    }

    @PostMapping("/account")
    Account addAccount(@RequestBody Account newAccount) throws Exception {
        if (!validateAccount(newAccount)) {
            throw new Exception("Account not found");
        }

        return accountRepository.save(newAccount);
    }

    @DeleteMapping("/account/{id}")
    void deleteAccount(@PathVariable Long id) throws Exception {
        accountRepository.deleteById(id);

    }


    @PutMapping("/account/{id}")
    Account updateAccount(@PathVariable Long id, @RequestBody Account newAccount) throws Exception {
        if(!validateAccount(newAccount)) {
            throw new Exception("Account info not valid");
        }

        return accountRepository.findById(id).map(account -> {
            account.setName(newAccount.getName());
            account.setSurname(newAccount.getSurname());
            account.setAddress(newAccount.getAddress());
            account.setEmail(newAccount.getEmail());
            account.setPassword(newAccount.getPassword());
            account.setPhonenumber(newAccount.getPhonenumber());
            return accountRepository.save(account);
        }).orElseGet(()->{
            newAccount.setId(id);
            return accountRepository.save(newAccount);
        });
    }


    private boolean validateAccount(Account account) throws Exception {
        String email = account.getEmail();
        String phoneNumber = account.getPhonenumber();
        String password = account.getPassword();

        if(!emailValidator.validateEmail(email)) {
            throw new Exception("Invalid email address: "+email);
        }

        if(!passwordChecker.validatePassword(password)) {
            throw new Exception("Invalid password");
        }

        if(phoneNumber.startsWith("+370") || phoneNumber.startsWith("86")){
            if(!phoneValidator.validateLithuanianPhoneNumber(phoneNumber)) {
                throw new Exception("Invalid phone number: " + phoneNumber);
            }else{
                if(!phoneValidator.validateInternationalPhoneNumber(phoneNumber,phoneNumber.substring(0,3)
                        ,phoneNumber.length())) {
                    throw new Exception("Invalid phone number: " + phoneNumber);
                }
            }
        }

        return true;
    }

}
