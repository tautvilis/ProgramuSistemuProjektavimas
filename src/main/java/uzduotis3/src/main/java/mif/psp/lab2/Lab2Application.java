package mif.psp.lab2;

import mif.psp.lab2.validators.EmailValidator;
import mif.psp.lab2.validators.PasswordChecker;
import mif.psp.lab2.validators.PhoneValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({EmailValidator.class, PasswordChecker.class, PhoneValidator.class})
public class Lab2Application {

	public static void main(String[] args) {
		SpringApplication.run(Lab2Application.class, args);
	}

}
