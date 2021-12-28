package functionalinterface;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class _Consumer {

    public static void main(String[] args) {
        // imperative approach
        greetCustomer(new Customer("Maria", "045-9091234"));

        // Consumer functional interface approach
        greetCustomerConsumer.accept(new Customer("John", "045-8085555"));

        // NiConsumer functional interface approach
        greetCustomerBiConsumer.accept(new Customer("David", "045-8085555"), true);
        greetCustomerBiConsumer.accept(new Customer("Tùng Hoàng", "045-8085555"), false);
    }

    static Consumer<Customer> greetCustomerConsumer = (customer) ->
            System.out.println("Hello " + customer.name
                    + "! Thanks for registering phone number: "
                    + customer.phoneNumber);

    static BiConsumer<Customer, Boolean> greetCustomerBiConsumer = (customer, showPhoneNumber) ->
            System.out.println("Hello " + customer.name
                    + "! Thanks for registering phone number: "
                    + (showPhoneNumber ? customer.phoneNumber : " ***********"));

    static void greetCustomer(Customer customer) {
        System.out.println("Hello " + customer.name
                + "! Thanks for registering phone number: "
                + customer.phoneNumber);
    }

    static class Customer {
        private final String name;
        private final String phoneNumber;

        public Customer(String name, String phoneNumber) {
            this.name = name;
            this.phoneNumber = phoneNumber;
        }
    }
}
