package ru.aston.trushanina_mu.task3.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import ru.aston.trushanina_mu.task3.model.Order;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureTestDatabase
class OrderRepositoryImplTest {

    private OrderRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository = new OrderRepositoryImpl();
    }

    @Test
    void findAll() {
        List<Order> orders = repository.findAll();
        assertEquals(orders.size(), 0);
    }

    @Test
    void findEntityById() {
        Order order = Order.builder().id(1L).createdOn(LocalDateTime.now()).build();
        assertTrue(repository.create(order));
        Order actual = repository.findEntityById(1L);
        assertEquals(actual.getCreatedOn(), order.getCreatedOn());
    }

    @Test
    void delete() {
        assertTrue(repository.delete(1L));
    }

    @Test
    void create() {
        Order order = Order.builder().createdOn(LocalDateTime.now()).build();
        assertTrue(repository.create(order));
    }

    @Test
    void update() {
        Order order = Order.builder()
                .id(12L)
                .createdOn(LocalDateTime.now())
                .deliveredOn(LocalDateTime.now().plusHours(2))
                .build();
        assertEquals(repository.update(order).getDeliveredOn(), order.getDeliveredOn());
    }
}