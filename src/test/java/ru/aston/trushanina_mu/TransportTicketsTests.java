package ru.aston.trushanina_mu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.trushanina_mu.task1.AllTickets;
import ru.aston.trushanina_mu.task1.DailyOrder;
import ru.aston.trushanina_mu.task1.Order;
import ru.aston.trushanina_mu.task1.User;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


class TransportTicketsTests {

    private AllTickets allTickets;

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
        order.setAmount(new BigDecimal("18"));
        order.setUser(user1);

        DailyOrder order1 = new DailyOrder(new BigDecimal("0.02"));
        order1.setAmount(new BigDecimal("2"));
        order1.setUser(user);

        allTickets = new AllTickets(
                List.of(order, order1)
        );
    }

    @Test
    void contextLoads() {
        assertThat(allTickets).isNotNull();
    }

    @Test
    void testGetAmountOk() {
        BigDecimal actualAmount = allTickets.getAmount();

        assertThat(actualAmount, is(new BigDecimal("20.0")));
    }

    @Test
    void testGetSortedOrders() {
        List<Order> actualOrders = allTickets.getSortedOrders();
        DailyOrder order1 = (DailyOrder) actualOrders.get(0);
        User user2 = order1.getUser();

        assertThat(actualOrders.size(), is(2));
        assertThat(user2.getLastName(), is("Abramov"));
    }
}
