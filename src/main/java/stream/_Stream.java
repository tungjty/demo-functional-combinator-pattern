package stream;

import java.util.*;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static stream._Stream.Gender.*;

public class _Stream {
    public static void main(String[] args) {

        List<Person> people = List.of(
                new Person("John", 20, MALE),
                new Person("Maria", 30, FEMALE),
                new Person("Aisha", 40, FEMALE),
                new Person("Bison", 50, MALE),
                new Person("Alice", 60, FEMALE),
                new Person("Steve", 70, MALE)
        );

        // map (transformation)
        Set<Gender> genderSet = people.stream()
                .map(person -> person.gender)
                .collect(Collectors.toSet());
        System.out.println(genderSet);

        // sort
        List<Person> sorted = people.stream()
                .sorted(Comparator.comparing(Person::getAge)
                                            .thenComparing(Person::getName)
                                            .reversed())
                .collect(Collectors.toList());
        sorted.forEach(System.out::println);

        // Max
        System.out.println("..........");
        people.stream()
                .max(Comparator.comparing(Person::getAge))
                //.ifPresent(System.out::println);
                .ifPresentOrElse(
                        System.out::println,
                                () -> System.out.println("Can not find max Age"));

        // Group
        System.out.println("..........");

        Map<Gender, List<Person>> groupByGender = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        groupByGender.forEach( (gender, peoples) -> {
                System.out.println(gender);
                peoples.forEach(System.out::println);
        });

        //....................................................................

        Function<Person, String> personStringFunction = person -> person.name;
        ToIntFunction<String> stringToIntFunction = String::length;
        IntConsumer println = System.out::println;

        people.stream()
                .map(personStringFunction)
                .mapToInt(stringToIntFunction)
                .forEach(println);

        // or can be re-written
//        people.stream()
//                .map(person -> person.name)
//                .mapToInt(name -> name.length())
//                .forEach(System.out::println);
        //....................................................................

        // allMatch
        boolean isAllFemale = people.stream()
                .allMatch(person -> FEMALE.equals(person.gender));
        boolean anyFemale = people.stream()
                .anyMatch(person -> FEMALE.equals(person.gender));
        boolean noneUNKNOWN = people.stream()
                .noneMatch(person -> UNKNOWN.equals(person.gender));
        System.out.println("All people are female? : " + isAllFemale);
        System.out.println("Any female? : " + anyFemale);
        System.out.println("None of Unknown? : " + noneUNKNOWN);

        //....................................................................

        // flatMap
        System.out.println("..........");

        List<String> letters1 = Arrays.asList("a", "b");
        List<String> letters2 = Arrays.asList("b", "c", "d");
        List<String> letters3 = Arrays.asList("e", "f");
        List<List<String>> listOfLetters = Arrays.asList(letters1, letters2, letters3);

        Function<List<String>, Stream<? extends String>> stream = List::stream;
        List<String> flatList = listOfLetters.stream()
                .flatMap(stream)
                .collect(Collectors.toList());
        System.out.println(flatList);

        System.out.println("..........");
        // - Lấy ra tên của người nữ có tuổi cao nhất:
        // 1. Lọc ra danh sách nũ
        // 2. Chọn ra người có tuổi cao nhất
        // 3. Lấy tên
        Optional<String> oldestFemale = people.stream()
                .filter(person -> person.getGender().equals(FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);
        oldestFemale.ifPresentOrElse(
                name -> System.out.println("Oldest female name : " + name),
                () ->System.out.println("Can not find the oldest female name...")
        );

    }

    static class Person {
        private final String name;
        private final int age;
        private final Gender gender;

        public Person(String name, int age, Gender gender) {
            this.name = name;
            this.age = age;
            this.gender = gender;
        }

        public String getName() {
            return name;
        }

        public Gender getGender() {
            return gender;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", gender=" + gender +
                    '}';
        }
    }

    enum Gender {
        MALE, FEMALE, UNKNOWN;
    }
}


