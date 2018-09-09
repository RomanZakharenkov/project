package by.rzmarket.dao;

import by.rzmarket.entity.Product;
import by.rzmarket.exception.DaoException;
import by.rzmarket.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StorageDao {

    private static final StorageDao INSTANCE = new StorageDao();
    private static final String IS_PRESENT_PRODUCT = "SELECT \"count\" FROM shop.storage WHERE product_id = ? ;";
    private static final String UPDATE_COUNT_BY_PRODUCT = "UPDATE shop.storage SET \"count\" = ? WHERE product_id = ? ;";
    private static final String ADD_PRODUCT_AND_COUNT = "INSERT INTO shop.storage (product_id, \"count\") VALUES (?, ?)";
    private static final String GET_COUNT_BY_PRODUCT =
            "SELECT " +
                    " \"count\" AS count " +
                    "FROM shop.storage " +
                    "WHERE product_id = ? ;";

    public Integer getCountByProduct(Product product) {
        Integer count = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_COUNT_BY_PRODUCT)) {
            preparedStatement.setInt(1, product.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return count;
    }

    private boolean isPresentProduct(Product product) {
        boolean result;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(IS_PRESENT_PRODUCT)) {
            preparedStatement.setInt(1, product.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            result = resultSet != null;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public boolean addCountByProduct(Product product, Integer count) {
        boolean result = false;
        if (isPresentProduct(product)) {
            try (Connection connection = ConnectionManager.get();
                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COUNT_BY_PRODUCT, RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, getCountByProduct(product) + count);
                preparedStatement.setInt(2, product.getId());
                Integer value = preparedStatement.executeUpdate();
                result = value != null;
                //Сравнить с DiscountDao
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        } else {
            try (Connection connection = ConnectionManager.get();
                 PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT_AND_COUNT)) {
                preparedStatement.setInt(1, product.getId());
                preparedStatement.setInt(2, count);
                Integer value = preparedStatement.executeUpdate();
                result = value != 0;
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
        return result;
    }

    public boolean decreaseCountByProduct(Product product, Integer count) {
        boolean result = false;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COUNT_BY_PRODUCT)) {
            preparedStatement.setInt(1, getCountByProduct(product) - count);
            //Где валидация на остаток и уменьшение
            preparedStatement.setInt(2, product.getId());
            Integer value = preparedStatement.executeUpdate();
            result = value != null;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public static StorageDao getInstance() {
        return INSTANCE;
    }
}
