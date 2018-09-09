package by.rzmarket.dao;

import by.rzmarket.entity.Role;
import by.rzmarket.entity.User;
import by.rzmarket.exception.DaoException;
import by.rzmarket.util.ConnectionManager;
import by.rzmarket.validator.LoginValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDao {

    private static final UserDao INSTANCE = new UserDao();
    private static final String GET_ALL = "SELECT first_name, last_name, email, phone_number FROM shop.\"user\"";
    private static final String ADD =
            "INSERT INTO shop.\"user\" (role, first_name, last_name, email, password, phone_number) " +
                    "VALUES (?, ?, ?, ?, ?, ?);";
    private static final String GET_BY_ID =
            "SELECT " +
                    "u.id AS id, " +
                    "u.role AS role, " +
                    "u.first_name AS firstName, " +
                    "u.last_name AS lastName, " +
                    "u.email AS email, " +
                    "u.password AS password, " +
                    "u.phone_number AS number " +
                    "FROM shop.\"user\" u " +
                    "WHERE u.id = ? ;";
    private static final String UPDATE =
            "UPDATE shop.\"user\" " +
                    "SET first_name = ?, " +
                    "last_name = ?, " +
                    "email = ? , " +
                    "phone_number = ? , " +
                    "role = ? , " +
                    "password = ? " +
                    "WHERE id = ? ;";
    private static final String IS_VALID = "SELECT password FROM shop.\"user\" WHERE email = ? ;";

    public boolean isValid(User user) {
        boolean result = false;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(IS_VALID)) {
            preparedStatement.setString(1, user.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                result = resultSet.getString(1).equals(user.getPassword());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public boolean update(User user) {
        boolean result = false;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getNumber());
            preparedStatement.setString(5, user.getRole().getName());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setInt(7, user.getId());
            result = preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public Optional<User> getById(Integer id) {
        User user = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                user = User.builder()
                        .id(resultSet.getInt("id"))
                        .role(Role.getByName(resultSet.getString("role")))
                        .firstName(resultSet.getString("firstName"))
                        .lastName(resultSet.getString("lastName"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .number(resultSet.getString("number"))
                        .build();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(user);
    }

    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                users.add(User.builder()
                        .firstName(resultSet.getString(1))
                        .lastName(resultSet.getString(2))
                        .email(resultSet.getString(3))
                        .number(resultSet.getString(4))
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }

    public Integer add(@NonNull User user) {
        Integer id = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getRole().getName());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getNumber());
            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                id = keys.getInt(1);
                user.setId(id);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return id;
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
