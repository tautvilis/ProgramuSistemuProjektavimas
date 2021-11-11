package mif.psp.lab2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String surname;
    private String phonenumber;
    private String email;
    private String address;
    private String password;

    public Account(String name, String surname, String phonenumber, String email, String address, String password) {
        this.name = name;
        this.surname = surname;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
        this.password = password;
    }
}
