package functionalinterface;

import java.util.function.Predicate;

public class _Predicate {

    public static void main(String[] args) {

        // imperative approach
        System.out.println(isValidPhoneNumber("084-555111"));
        System.out.println(isValidPhoneNumber("084-001"));
        System.out.println(isValidPhoneNumber("074-001222333"));

        // Predicate functional interface approach
        System.out.println("test 1 = " + isValidPhonePredicate.test("084-555111"));
        System.out.println("test 2 = " + isValidPhonePredicate.test("084-001"));
        System.out.println("test 3 = " + isValidPhonePredicate.test("074-001222333"));
        System.out.println("test 4 = " + isValidPhonePredicate
                                        .and(isPhoneContain9Predicate)
                                        .test("084-000000"));

        System.out.println("test 5 = " + isValidPhonePredicate
                                        .or(isPhoneContain9Predicate)
                                        .test("084-00"));

    }

    static Predicate<String> isValidPhonePredicate = phoneNumber ->
                    phoneNumber.startsWith("08") && phoneNumber.length() >= 9;

    static Predicate<String> isPhoneContain9Predicate = phoneNumber ->
            phoneNumber.contains("9");

    static boolean isValidPhoneNumber (String phoneNumber) {
        return phoneNumber.startsWith("08") && phoneNumber.length() >= 9;
    }
}
