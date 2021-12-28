package functionalinterface;

import java.util.function.BiFunction;
import java.util.function.Function;

public class _Function {

    public static void main(String[] args) {
        int increment = incrementByOne(0);
        System.out.println(increment);

        // Function
        Integer apply = incrementByOneFunction.apply(1);
        System.out.println(apply);

        // combine 2 Function
        System.out.println(incrementByOneThenMultiByTenFunction.apply(1));

        // BiFunction
        Integer resultBiFunction = incrementByOneAndMultiplyTenBiFunction.apply(4, 10);
        System.out.println(resultBiFunction);
    }


    static Function<Integer, Integer> incrementByOneFunction = number -> number + 1;

    static Function<Integer, Integer> multiplyBy10Function = number -> number * 10;

    static Function<Integer, Integer> incrementByOneThenMultiByTenFunction =
            incrementByOneFunction.andThen(multiplyBy10Function);

    static BiFunction<Integer, Integer, Integer> incrementByOneAndMultiplyTenBiFunction =
                            (num1, num2) -> (num1 + 1) * num2;

    static int incrementByOne(int number) {
        return number + 1;
    }

    static int incrementByOneAndMultiplyTen(int number, int numToMultiply) {
        return (number + 1) * numToMultiply;
    }
}
