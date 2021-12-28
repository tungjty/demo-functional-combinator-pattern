package combinatorpattern;

import java.time.LocalDate;
import java.util.function.Function;

import static combinatorpattern.CustomerRegistrationValidator.*;

public class Main {
    public static void main(String[] args) {

        Customer customer = new Customer(
                "Alice",
                "alicemigoscode.edu",
                "+07787544545",
                LocalDate.of(2020, 2, 18)
        );

        System.out.println("Normal validation: "
                + new CustomerValidationService().isValidCustomer(customer));

        // Use combinator pattern
        CustomerRegistrationValidator result = isEmailValid()
                                .and(isPhoneNumberValid())
                                .and(isAdult());

        ValidationResult validationResult = result.apply(customer);
        System.out.println(validationResult);

        if(validationResult != ValidationResult.SUCCESS) {
            throw new IllegalStateException(validationResult.name());
        }
    }
}
