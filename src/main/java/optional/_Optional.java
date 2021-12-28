package optional;

import java.util.Optional;

public class _Optional {

    public static void main(String[] args) {
        Object value = Optional.ofNullable(null).orElseGet(() -> "Default value");
        System.out.println(value);

        Optional.ofNullable("tung.hoang@gmail.com")
                .ifPresent(email -> System.out.println("Sending email to : " + email));

        Optional.ofNullable(null)
                .ifPresentOrElse(
                        email -> System.out.println("Sending email to : " + email),
                        () -> System.out.println("Can not send email to :  null"));
    }
}
