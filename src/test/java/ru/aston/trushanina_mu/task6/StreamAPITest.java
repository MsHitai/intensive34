package ru.aston.trushanina_mu.task6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;
import static org.junit.jupiter.api.Assertions.*;

class StreamAPITest {

    private Stream<Person> s1;
    private Stream<Person> s2;

    @BeforeEach
    void setUp() {
        s1 = Stream.of(
                new Person("Ivan", "Ivanov", 20, new Address(City.GOMEL, "Ilicha", "51")),
                new Person("Sergey", "Sergeev", 30, new Address(City.MINSK, "Mira", "11")),
                new Person("Denis", "Jenisov", 40, new Address(City.BREST, "Drugbu", "44")),
                new Person("Alex", "Alexandrov", 50, new Address(City.GRODNO, "Minskaya", "1")),
                new Person("Sveta", "Jvetlova", 60, new Address(City.VITEBSK, "pr. Pobedu", "87")),
                new Person("Tanya", "Tatyanova", 60, new Address(City.MOGILEV, "Ilicha", "111"))
        );

        s2 = Stream.of(
                new Person("Ivan", "Ivanov", 20, new Address(City.GOMEL, "Ilicha", "51")),
                new Person("Sergey", "Sergeev", 30, new Address(City.MINSK, "Mira", "11")),
                new Person("Denis", "Denisov", 40, new Address(City.BREST, "Drugbu", "44")),
                new Person("Alex", "Alexandrov", 50, new Address(City.GRODNO, "Minskaya", "1")),
                new Person("Sveta", "Svetlova", 60, new Address(City.VITEBSK, "pr. Pobedu", "87")),
                new Person("Tanya", "Tatyanova", 60, new Address(City.MOGILEV, "Ilicha", "111"))
        );
    }

    @Test
    @DisplayName("Задано множество фамилий сотрудников. Разработать программу, которая отображает все фамилии, " +
            "начинающиеся на букву «J»")
    void testSortLastName() {
        List<Person> jLastNames = s1.filter(person -> person.getLastName().startsWith("J")).toList();

        assertEquals(jLastNames.size(), 2);

        jLastNames.forEach(person ->  System.out.println(person.getLastName()));
    }

    @Test
    @DisplayName("Соберите все элементы Stream в одну строку через пробел и выведите результат")
    void testCollectingElements() {
        List<String> firstNames = s2.map(Person::getFirstName).toList();

        String joined = firstNames.stream().map(Object::toString).collect(Collectors.joining(" "));

        assertTrue(joined.startsWith("Ivan"));
        assertTrue(joined.endsWith("Tanya"));
        System.out.println(joined);
    }

    @Test
    @DisplayName("Создание Stream объектов и поиск всех объектов, у которых определенное поле равно определенному " +
            "значению")
    void testComparingObjectsInStream() {
        Address address = new Address(City.GOMEL, "Ilicha", "51");

        List<Person> particularAddress = s1.filter(person -> person.getAddress().equals(address)).toList();

        assertEquals(particularAddress.size(), 1);
        assertEquals(particularAddress.get(0).getAddress(), address);
    }

    @Test
    @DisplayName("Сгруппировать слова в Stream по первой букве, посчитать количество слов в каждой группе и вывести " +
            "результаты в виде словаря, где ключ — первая буква слова, а значение — количество слов, " +
            "начинающихся на эту букву.")
    void testGroupingObjectsInStream() {
        Map<String, Long> result = s1.collect(
                Collectors.groupingBy(person -> person.getFirstName().substring(0, 1), Collectors.counting())
        );

        assertEquals(result.size(), 5);
        assertEquals(result.get("S"), 2);
    }

    @Test
    @DisplayName("Сгруппировать числа в Stream по остатку от деления на 3, посчитать сумму чисел в каждой группе и " +
            "вывести результаты")
    void testGroupingNumbers() {
        Map<Integer, Long> oddAge = s1.collect(groupingBy(person -> person.getAge() % 3, Collectors.counting()));

        oddAge.keySet().forEach(age -> {
            System.out.println("Остаток от деления: " + age);
            System.out.println("Количество: " + oddAge.get(age));
        });
    }

    @Test
    @DisplayName("Для любого набора случайно-сгенерированных чисел нужно определить количество парных. Для решения " +
            "задачи использовать средства программного интерфейса Stream API.")
    void testGenerateAndPairNumbers() {
        int size = 20;
        int min = 0;
        int max = 150;

        long count = new Random().ints(size, min, max)
                .boxed()
                .filter(integer -> integer % 2 == 0)
                .count();

        System.out.println("количество парных чисел: " + count);
    }

    @Test
    @DisplayName("Соберите числа в Stream в одно число, перемножив их между собой и выведите результат")
    void gatherAgeAndMultiplyViaStream() {
        long multiplyAge = s1.map(Person::getAge).reduce(1, (a, b) -> a * b);
        System.out.println("Multiplied age of people from first stream: " + multiplyAge);
    }

    @Test
    @DisplayName("Собрать все уникальные элементы Stream в список и отсортировать их")
    void gatherDistinctAndSort() {
        s1 = Stream.of(
                new Person("Ivan", "Ivanov", 20, new Address(City.GOMEL, "Ilicha", "51")),
                new Person("Ivan", "Ivanov", 20, new Address(City.GOMEL, "Ilicha", "51")),
                new Person("Ivan", "Ivanov", 20, new Address(City.GOMEL, "Ilicha", "51")),
                new Person("Ivan", "Ivanov", 20, new Address(City.GOMEL, "Ilicha", "51")),
                new Person("Ivan", "Ivanov", 20, new Address(City.GOMEL, "Ilicha", "51")),
                new Person("Ivan", "Ivanov", 20, new Address(City.GOMEL, "Ilicha", "51")),
                new Person("Ivan", "Ivanov", 20, new Address(City.GOMEL, "Ilicha", "51")),
                new Person("Sergey", "Sergeev", 30, new Address(City.MINSK, "Mira", "11")),
                new Person("Denis", "Jenisov", 40, new Address(City.BREST, "Drugbu", "44")),
                new Person("Alex", "Alexandrov", 50, new Address(City.GRODNO, "Minskaya", "1")),
                new Person("Sveta", "Jvetlova", 60, new Address(City.VITEBSK, "pr. Pobedu", "87")),
                new Person("Tanya", "Tatyanova", 60, new Address(City.MOGILEV, "Ilicha", "111"))
        );
        List<Person> sorted = s1.distinct().sorted(Comparator.comparing(Person::getLastName)).toList();

        assertEquals(sorted.size(), 6);
        assertEquals(sorted.get(0).getLastName(), "Alexandrov");
    }

    @Test
    @DisplayName("Выведите на экран только те элементы списка, которые больше заданного числа")
    void ageMoreThan20() {
        Person sample = new Person(
                "Ivan", "Ivanov", 20, new Address(City.GOMEL, "Ilicha", "51")
        );

        List<Person> result = s1.filter(person -> person.getAge() > 20).toList();

        assertEquals(result.size(), 5);
        assertFalse(result.contains(sample));
    }

    @Test
    @DisplayName("Отсортировать адреса по улице в обратном порядке")
    void sortAddressReversed() {
        List<Address> addresses = s1
                .map(Person::getAddress)
                .sorted(Comparator.comparing(Address::getStreet).reversed())
                .toList();

        assertEquals(addresses.get(0).getStreet(), "pr. Pobedu");
    }
}