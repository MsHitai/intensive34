package ru.aston.trushanina_mu.task3.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.aston.trushanina_mu.task3.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserRepositoryImplTest {

    private UserRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        repository = new UserRepositoryImpl();
    }

    @Test
    void findAll() {
        List<User> userList = repository.findAll();
        assertEquals(userList.get(0).getFirstName(), "Pasha");
    }

    @Test
    void findEntityById() {
        User user = new User(3L, "Ivan3", "Ivanov3",
                "555", "test@test.ru", 1L);
        assertEquals(repository.findEntityById(user.getId()).getLastName(), "Ivanov3");
    }

    @Test
    void delete() {
        User user = new User(3L, "Ivan3", "Ivanov3",
                "555", "test@test.ru", 1L);
        assertTrue(repository.delete(user.getId()));
    }

    @Test
    void create() {
        User user = new User(3L, "Ivan3", "Ivanov3",
                "555", "test@test.ru", 1L);
        assertTrue(repository.create(user));
    }

    @Test
    void update() {
        User user = new User(3L, "Ivan3", "Ivanov3",
                "555", "test@test.ru", 1L);
        assertEquals(repository.update(user).getFirstName(), "Ivan3");
    }

    @Test
    void joinUsersAndOrders() {
        String output = "Имя пользователя: Pasha Дата заказа: 2023-09-10 00:00:00.000000";
        assertEquals(repository.joinUsersAndOrders().get(0), output);
    }
}