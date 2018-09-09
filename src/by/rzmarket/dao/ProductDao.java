package by.rzmarket.dao;

import by.rzmarket.entity.Category;
import by.rzmarket.entity.Discount;
import by.rzmarket.entity.Product;
import by.rzmarket.exception.DaoException;
import by.rzmarket.util.ConnectionManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDao {

    private static final ProductDao INSTANCE = new ProductDao();
    private static final String UPDATE = "UPDATE shop.product SET brand = ? , model = ? , price = ? , description = ? , image = ? , category_id = ? WHERE id = ? ;";
    private static final String ADD = "INSERT INTO shop.product (brand, model, price, category_id, description, image) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_BY_ID =
            "SELECT " +
                    "p.id AS productId, " +
                    "p.brand AS brand, " +
                    "p.model AS model, " +
                    "p.price AS price, " +
                    "p.description AS description, " +
                    "p.image AS  image, " +
                    "c.id AS categoryId, " +
                    "c.name AS categoryName " +
                    "FROM shop.product p " +
                    "LEFT JOIN shop.category c " +
                    "ON p.category_id = c.id " +
                    "WHERE p.id = ? ;";

    public Optional<Product> getById(Integer id) {
        Product product = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                product = Product.builder()
                        .id(resultSet.getInt("productId"))
                        .brand(resultSet.getString("brand"))
                        .model(resultSet.getString("model"))
                        .price(resultSet.getInt("price"))
                        .description(resultSet.getString("description"))
                        .image(resultSet.getString("image"))
                        .category(Category.builder()
                                .id(resultSet.getInt("categoryId"))
                                .name(resultSet.getString("categoryName"))
                                .build())
                        .build();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return Optional.ofNullable(product);
    }

    public Integer add(Product product) {
        Integer id = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getBrand());
            preparedStatement.setString(2, product.getModel());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setInt(4, product.getCategory().getId());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setString(6, product.getImage());
            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            if (keys.next()) {
                id = keys.getInt(1);
                product.setId(id);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return id;
    }

    public boolean update(@NonNull Product product) {
        boolean result;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getBrand());
            preparedStatement.setString(2, product.getModel());
            preparedStatement.setInt(3, product.getPrice());
            preparedStatement.setInt(4, product.getCategory().getId());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setString(6, product.getImage());
            preparedStatement.setInt(7, product.getId());
            preparedStatement.executeUpdate();

            ResultSet keys = preparedStatement.getGeneratedKeys();
            result = keys.next();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public static ProductDao getInstance() {
        return INSTANCE;
    }
}
