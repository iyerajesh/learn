package com.xylia.java.learn;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.xylia.java.learn.model.Customer;
import com.xylia.java.learn.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LearnApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Test
    public void convertListToSet() {

        List<Integer> sourceList = Arrays.asList(0, 1, 2, 3, 4);
        Set<Integer> sourceSet = new HashSet<>(sourceList);

        assertThat(sourceSet).hasSize(5);
    }

    @Test
    public void testIfInterface() {
        assertThat(Runnable.class).isInterface();
    }


    @Test
    public void testIfMapIsNotEmpty() {
        Map<String, String> myTestMap = new HashMap<>();
        ;
        myTestMap.put("key", "value");

        assertThat(myTestMap).containsKey("key");
    }

    @Test
    public void testIfOptionalStringIsNotEmpty() {
        final String myTestString = "testString";
        Optional<String> stringOptional = Optional.ofNullable(myTestString);

        assertThat(stringOptional).isNotEmpty();
    }

    @Test
    public void testBiMapInverse() {

        Map<String, String> capitalMap = new HashMap<>();
        capitalMap.put("New Delhi", "India");
        capitalMap.put("Moscow", "Russia");
        capitalMap.put("Washington DC", "USA");

        final BiMap<String, String> biCapitalMap = HashBiMap.create(capitalMap);
        assertThat(biCapitalMap.inverse().get("India")).isEqualTo("New Delhi");
    }

    @Test
    public void testIfStringIsPalindrome() {
        final String testString = "madam";
        StringBuffer forward = new StringBuffer(testString);
        StringBuffer reverse = forward.reverse();

        assertThat(forward.equals(reverse)).isTrue();
    }

    @Test
    public void testIfStringsWork() {
        final List<String> myList = Arrays.asList("test1", "test2", "test3");

        assertThat(myList.stream()
                .filter(s -> s.equals("test1"))
                .collect(Collectors.toList())).hasSize(1);
    }


    @Test
    public void testIfPalindromeFromIntStream() {
        final String testString = "madam";

        assertThat(IntStream.range(0, testString.length() / 2)
                .noneMatch(i -> testString.charAt(i) != testString.charAt(testString.length() - i - 1))).isTrue();
    }

//    @Test
//    public void testIfCustomersAreGreaterThan100Points() {
//
//        Customer sarah = new CustomerBuilder().withName("sarah").withPoints(150).build();
//        Customer danielle = new CustomerBuilder().withName("danielle").withPoints(200).build();
//        Customer raj = new CustomerBuilder().withName("raj").withPoints(80).build();
//
//        List<Customer> customerList = Arrays.asList(sarah, danielle, raj);
//
//        assertThat(customerList.stream().filter(c -> c.getPoints() < 100).collect(Collectors.toList()))
//                .hasSize(1);
//    }

    @Test
    public void testIfCustomersAreLessThan100Points() {
        Customer sarah = new Customer.CustomerBuilder().withName("sarah").withPoints(150).build();
        Customer raj = new Customer.CustomerBuilder().withName("Raj").withPoints(90).build();
        Customer danielle = new Customer.CustomerBuilder().withName("Danielle").withPoints(95).build();

        List<Customer> customerList = Arrays.asList(sarah, raj, danielle);

        assertThat(customerList.stream().filter(customer -> customer.getPoints() < 100).collect(Collectors.toList()))
                .hasSize(2);
    }

//    @Test
//    public void identifyEmployeesAndFriends() {
//
//        String[] employeesInput = {
//                "1,Richard,Engineering",
//                "2,Erlich,HR",
//                "3,Monica,Business",
//                "4,Dinesh,Engineering",
//                "6,Carla,Engineering"
//        };
//
//        List<Employee> employeeList = Arrays.stream(employeesInput).map(e -> new Employee(Integer.parseInt(e.split(",")[0]),
//                e.split(",")[1], e.split(",")[2]))
//                .collect(Collectors.toList());
//
//        String[] friendshipsInput = {
//                "1,2",
//                "1,3",
//                "2,4"
//        };
//
//        List<Friendships> friendshipsList = Arrays.stream(friendshipsInput).map(friend -> new Friendships(friend))
//                .collect(Collectors.toList());
//
//        assertThat(employeeList.stream().
//                filter(employee -> friendshipsList.contains(employee)).
//                collect(Collectors.toList())).isNotEmpty();
//    }


    @Test
    public void identifyEmployeesAndFriends() {
        String[] employeeInput = {
                "1,Richard,Engineering",
                "2,Erlich,HR",
                "3,Monica,Business",
                "4,Dinesh,Engineering",
                "6,Carla,Engineering"
        };

        List<Employee> employeeList = Arrays.stream(employeeInput)
                .map(employee -> new Employee(
                        Integer.parseInt(employee.split(",")[0]), employee.split(",")[1], employee.split(",")[2])).collect(Collectors.toList());

        assertThat(employeeList).hasSize(5);
    }

    @Test
    public void testIfNumberIsDivisibleBy7() {

        int[] intArray = IntStream.range(0, 100).toArray();

        for (int i : intArray)
            if (i % 7 == 0) {
                System.out.println("Divisible by 7:" + i);

                boolean even = (i & 0) == 0;
                boolean odd = (i & 0) != 0;
            }
    }

    @Test
    public void testStrings() {
        Stream.of("d1", "d2", "d3", "d4")
                .filter(s -> {
                    System.out.println("The string is:" + s);
                    return true;
                }).forEach(s -> System.out.print(s));
    }


    @Test
    public void streamSupplierTest() {
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("a1", "b1", "c1", "d1")
                        .filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(s -> true);
        streamSupplier.get().noneMatch(s -> true);
    }


    @Test
    public void testGroupingBy() {

        class Person {
            String name;
            int age;

            Person(String name, int age) {
                this.name = name;
                this.age = age;
            }

            public String toString() {
                return name;
            }
        }

        List<Person> personList = Arrays.asList(new Person("Raj", 45), new Person("Reena", 47));

        Map<Integer, String> personMap = personList.stream()
                .collect(Collectors.toMap(person -> person.age, person -> person.name));

        Map<Integer, List<Person>> personListByAge = personList.stream()
                .collect(Collectors.groupingBy(person -> person.age));

        personListByAge.forEach((age, name) -> System.out.println("Age is: " + age + "Name is: " + name));
    }


    @Test
    public void testStreamFunctions() {

        List<String> myList = Arrays.asList("a1", "a2", "a3", "c1", "c2", "c3");

        myList.stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);

        Stream.of("a1", "a2", "a3")
                .findFirst()
                .ifPresent(System.out::println);

        Arrays.stream(new int[]{1, 2, 3})
                .map(n -> n * 2 + 1)
                .average()
                .ifPresent(System.out::println);

        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);
    }

    @Test
    public void codingSolution1() {
        int[] intArray = new int[]{10, 15, 3, 7};
        int[] intArray1 = new int[]{10, 15, 3, 7};
        final int k = 17;

        for (int i : intArray) {
            for (int j : intArray1) {
                if (i + j == k)
                    System.out.println("The two numbers that match are: " + i + " " + j);
            }
        }
    }

    @Test
    public void streamSupplierTest1() {

        Supplier<Stream<String>> streamSupplier = () -> Stream.of("a1", "a2", "a3", "a4", "c1", "c2")
                .filter(s -> s.startsWith("c"));

        streamSupplier.get().anyMatch(s -> true);
        streamSupplier.get().anyMatch(s -> true);
    }


    @Test
    public void flatMapTest() {

        List<Foo> fooList = new ArrayList<>();

        IntStream.range(1, 4)
                .forEach(i -> fooList.add(new Foo("Foo" + i)));

        fooList.stream()
                .forEach(foo -> IntStream.range(1, 4)
                        .forEach(i -> foo.barList.add(new Bar("Bar" + i + " " + foo.name))));

        fooList.stream()
                .flatMap(foo -> foo.barList.stream())
                .forEach(System.out::println);
    }

    private class Foo {
        private String name;
        private List<Bar> barList = new ArrayList<>();

        Foo(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private class Bar {
        private String name;

        Bar(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }


}

