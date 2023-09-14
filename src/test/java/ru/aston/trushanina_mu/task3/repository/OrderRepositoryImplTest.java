package ru.aston.trushanina_mu.task3.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aston.trushanina_mu.task3.model.Order;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderRepositoryImplTest {
    @Mock
    private OrderRepositoryImpl repository;
    private Order order;

    @BeforeEach
    void setUp() {
        order = Order.builder()
                .id(12L)
                .createdOn(LocalDateTime.now())
                .deliveredOn(LocalDateTime.now().plusHours(2))
                .build();
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(List.of(order));
        assertEquals(repository.findAll().size(), 1);
    }

    @Test
    void findEntityById() {
        when(repository.findEntityById(order.getId())).thenReturn(order);

        Order actual = repository.findEntityById(order.getId());

        assertEquals(actual, order);
    }

    @Test
    void delete() {
        repository.delete(12L);
        verify(repository, times(1)).delete(12L);
    }

    @Test
    void create() {
        repository.create(order);
        verify(repository, times(1)).create(order);
    }

    @Test
    void update() {
        order.setDeliveredOn(LocalDateTime.now().plusHours(12));
        when(repository.update(order)).thenReturn(order);
        assertEquals(repository.update(order).getDeliveredOn(), order.getDeliveredOn());
    }
}