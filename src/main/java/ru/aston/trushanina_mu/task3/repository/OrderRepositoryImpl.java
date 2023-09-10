package ru.aston.trushanina_mu.task3.repository;

import ru.aston.trushanina_mu.task3.DBConnector;
import ru.aston.trushanina_mu.task3.model.Order;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements DaoDataEntityLayer<Order> {

    private static final String SQL_SELECT_ALL_ORDERS = "SELECT * FROM orders";
    private static final String SQL_SELECT_ORDER_ID = "SELECT * FROM orders WHERE id=?";
    private static final String SQL_DELETE_ORDER_ID = "DELETE FROM orders WHERE id=?";
    private static final String SQL_CREATE_ORDER = "INSERT INTO orders (created_on, delivered_on) VALUES (?, ?)";
    private static final String SQL_UPDATE_ORDER = "UPDATE orders SET created_on = ?, delivered_on = ? WHERE id = ?";

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        try (Connection connection = DBConnector.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_ORDERS);
            while (resultSet.next()) {
                orders.add(new Order(resultSet.getLong(1),
                        resultSet.getObject(2, LocalDateTime.class),
                        resultSet.getObject(3, LocalDateTime.class)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }

    @Override
    public Order findEntityById(long id) {
        Order order = null;
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ORDER_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                order = new Order(resultSet.getLong(1),
                        resultSet.getObject(2, LocalDateTime.class),
                        resultSet.getObject(3, LocalDateTime.class));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return order;
    }

    @Override
    public boolean delete(long id) {
        boolean isDeleted = false;
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ORDER_ID)) {
            statement.setLong(1, id);
            if (statement.executeUpdate() > 0) {
                isDeleted = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isDeleted;
    }

    @Override
    public boolean create(Order order) {
        boolean isCreated = false;
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_ORDER)) {
            statement.setObject(1, order.getCreatedOn());
            statement.setObject(2, order.getDeliveredOn());
            if (statement.executeUpdate() > 0) {
                isCreated = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isCreated;
    }

    @Override
    public Order update(Order order) {
        Order resultOrder = null;
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ORDER)) {
            statement.setObject(1, order.getCreatedOn());
            statement.setObject(2, order.getDeliveredOn());
            statement.setLong(3, order.getId());
            if (statement.executeUpdate() > 0) {
                resultOrder = findEntityById(order.getId());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultOrder;
    }
}
