package ru.aston.trushanina_mu.task3.repository;

import org.springframework.stereotype.Repository;
import ru.aston.trushanina_mu.task3.DBConnector;
import ru.aston.trushanina_mu.task3.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements DaoDataEntityLayer<User> {

    private static final String SQL_SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_SELECT_USER_ID = "SELECT * FROM users WHERE id=?";
    private static final String SQL_DELETE_USER_ID = "DELETE FROM users WHERE id=?";
    private static final String SQL_CREATE_USER = "INSERT INTO users (first_name, last_name, telephone, email, order_id)" +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_USER = "UPDATE users " +
            "SET first_name = ?, last_name = ?, telephone = ?, email = ?, order_id = ?" +
            "WHERE id = ?";

    private static final String SQL_SELECT_USERS_JOIN_ORDERS
            = "SELECT users.first_name, o.created_on FROM users JOIN orders o on o.Id = users.order_id";

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = DBConnector.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_USERS);
            while (resultSet.next()) {
                users.add(new User(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getLong(6)));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    @Override
    public User findEntityById(long id) {
        User user = null;
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USER_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getLong(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getString(5), resultSet.getLong(6));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public boolean delete(long id) {
        boolean isDelete = false;
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER_ID)) {
            statement.setLong(1, id);
            if (statement.executeUpdate() > 0) {
                isDelete = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isDelete;
    }

    @Override
    public boolean create(User user) {
        boolean isCreated = false;
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getTelephone());
            statement.setString(4, user.getEmail());
            statement.setLong(5, user.getOrderId());
            if (statement.executeUpdate() > 0) {
                isCreated = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isCreated;
    }

    @Override
    public User update(User user) {
        User resultUser = null;
        try (Connection connection = DBConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getTelephone());
            statement.setString(4, user.getEmail());
            statement.setLong(5, user.getOrderId());
            if (statement.executeUpdate() > 0) {
                resultUser = findEntityById(user.getId());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return resultUser;
    }

    public List<String> joinUsersAndOrders() {
        List<String> listJoin = new ArrayList<>();
        try (Connection connection = DBConnector.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_USERS_JOIN_ORDERS);
            while (resultSet.next()) {
                listJoin.add("Имя пользователя: " + resultSet.getString(1)
                        + " Дата заказа: " + resultSet.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listJoin;
    }
}
