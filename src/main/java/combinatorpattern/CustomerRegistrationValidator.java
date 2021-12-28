package combinatorpattern;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;

import static combinatorpattern.CustomerRegistrationValidator.*;
import static combinatorpattern.CustomerRegistrationValidator.ValidationResult.*;

public interface CustomerRegistrationValidator
                extends Function<Customer, ValidationResult> {

    static CustomerRegistrationValidator isEmailValid() {
        return customer -> {
            System.out.println("running email validation...");
            return customer.getEmail().contains("@") ?
                    SUCCESS : EMAIL_NOT_VALID;
        };
    }

    static CustomerRegistrationValidator isPhoneNumberValid() {
        return customer -> {
            System.out.println("running phone number validation...");
            return customer.getPhone().startsWith("+0") ?
                    SUCCESS : PHONE_NUMBER_NOT_VALID;
        };
    }

    static CustomerRegistrationValidator isAdult() {
        return customer -> {
            System.out.println("running adult validation...");
            return Period.between(customer.getDob(), LocalDate.now()).getYears() >= 16 ?
                    SUCCESS : NOT_AN_ADULT;
        };
    }

    default CustomerRegistrationValidator and(CustomerRegistrationValidator other) {
        return customer -> {
            ValidationResult result = this.apply(customer);
            return result.equals(SUCCESS) ? other.apply(customer) : result;
        };

    }

    enum ValidationResult {
        SUCCESS,
        PHONE_NUMBER_NOT_VALID,
        EMAIL_NOT_VALID,
        NOT_AN_ADULT
    }

}
