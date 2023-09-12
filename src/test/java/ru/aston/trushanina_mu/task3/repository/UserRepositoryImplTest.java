package ru.aston.trushanina_mu.task3.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.aston.trushanina_mu.task3.model.Order;
import ru.aston.trushanina_mu.task3.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserRepositoryImplTest {
    @Mock
    private UserRepositoryImpl repository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(3L, "Ivan3", "Ivanov3",
                "555", "test@test.ru", 1L);
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(List.of(user));
        List<User> actual = repository.findAll();

        assertEquals(actual.size(), 1);
        assertEquals(actual.get(0), user);
    }

    @Test
    void findEntityById() {
        when(repository.findEntityById(user.getId())).thenReturn(user);
        assertEquals(repository.findEntityById(user.getId()).getLastName(), "Ivanov3");
    }

    @Test
    void delete() {
        repository.delete(user.getId());
        verify(repository, times(1)).delete(user.getId());
    }

    @Test
    void create() {
        repository.create(user);
        verify(repository, times(1)).create(user);
    }

    @Test
    void update() {
        user.setEmail("new@email.ru");
        when(repository.update(user)).thenReturn(user);
        assertEquals(repository.update(user).getEmail(), "new@email.ru");
    }

    @Test
    void joinUsersAndOrders() {
        Order order = new Order(1L,
                LocalDateTime.of(2023, Month.SEPTEMBER, 10, 0, 0, 0), null);
        String output = "Имя пользователя: Ivan3 Дата заказа: 2023-09-10 00:00:00.000000";
        when(repository.joinUsersAndOrders()).thenReturn(List.of(output));
        assertEquals(repository.joinUsersAndOrders().get(0), output);
    }
}