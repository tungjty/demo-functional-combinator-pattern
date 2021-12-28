package functionalinterface;

import java.util.function.Supplier;

public class _Supplier {
    public static void main(String[] args) {

        // normal approach
        System.out.println(getDBConnectionUrl());

        // Supplier functional interface approach
        System.out.println(getDBConnectionUrlSupplier.get());
    }

    static String getDBConnectionUrl() {
        return "jdbc://localhost:8080/user";
    }

    static Supplier<String> getDBConnectionUrlSupplier = () -> "jdbc://localhost:8080/user";
}
