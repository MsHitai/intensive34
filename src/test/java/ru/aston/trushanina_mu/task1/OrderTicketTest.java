package ru.aston.trushanina_mu.task1;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTicketTest {

    @Test
    void testGetDiscountOk() {
        OrderTicket orderTicket = new OrderTicket(
                new BigDecimal("1.0"),
                new BigDecimal("10"),
                new User(),
                1,
                Route.TRAMWAY
        );

        BigDecimal expected = new BigDecimal("1.06");

        BigDecimal actual = orderTicket.getDiscount();

        assertEquals(expected, actual);
    }

    @Test
    void collectionsTest() {
        List<String> test = new ArrayList<>();
        test.add("test");

        List<String> test2 = new LinkedList<>(test);
        test2.add("test2");

        assertEquals(test2.size(), 2);
        assertEquals(test.size(), 1);
    }

    @Test
    void mapTest() {
        Map<Integer, String> test = new HashMap<>();
        test.put(1, "test");

        Map<Integer, String> test2 = new LinkedHashMap<>(test);
        test2.put(2, "test2");

        assertEquals(test2.size(), 2);
        assertEquals(test.size(), 1);
    }

    @Test
    void setTest() {
        Set<String> test = new TreeSet<>();
        test.add("test");

        Set<String> test2 = new HashSet<>(test);
        test2.add("test2");

        assertEquals(test2.size(), 2);
        assertEquals(test.size(), 1);
    }

    @Test
    void removeCollectionsTest() {
        List<String> test = new ArrayList<>();
        test.add("test");

        List<String> test2 = new LinkedList<>(test);
        test2.add("test2");

        assertEquals(test2.size(), 2);
        assertEquals(test.size(), 1);

        test.remove(0);
        test2.remove(0);

        assertEquals(test2.size(), 1);
        assertEquals(test.size(), 0);
    }
}