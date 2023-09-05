package ru.aston.trushanina_mu.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.trushanina_mu.task2.OrderException;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AllTicketsTest {

    AllTickets allTickets;

    @BeforeEach
    void setUp() {
        User user = new User(
                24,
                "Ivanov",
                "Alex"
        );

        User user1 = new User();
        user1.setLastName("Abramov");

        DailyOrder order = new DailyOrder(new BigDecimal("0.02"));
        order.setAmount(new BigDecimal("-1"));
        order.setUser(user1);

        DailyOrder order1 = new DailyOrder(new BigDecimal("0.02"));
        order1.setAmount(new BigDecimal("2"));
        order1.setUser(user);

        allTickets = new AllTickets(
                List.of(order, order1)
        );
    }

    @Test
    void testGetAmountFailWhenOrderAmountIsLessThanZero() {
        assertThrows(OrderException.class, () -> allTickets.getAmount());
    }
}