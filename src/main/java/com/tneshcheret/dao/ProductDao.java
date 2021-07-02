package com.tneshcheret.dao;

import com.tneshcheret.entity.BrandPackage;
import com.tneshcheret.entity.Product;
import com.tneshcheret.utils.PostgresUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private static final String SELECT_ALL = "select id_product, " +
            "name_product,  " +
            "brand_package_id,  " +
            "brand_package.name_brand_package, " +
            "price, " +
            "prime_cost " +
            "from product " +
            "inner join brand_package on product.brand_package_id = brand_package.id_brand_package";
    private static final String INSERT_SQL = "insert into product(" +
            "name_product, " +
            "brand_package_id, " +
            "prime_cost, " +
            "price) " +
            "values (?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "select id_product, " +
            "name_product,  " +
            "brand_package_id,  " +
            "brand_package.name_brand_package, " +
            "price, " +
            "prime_cost " +
            "from product " +
            "inner join brand_package on product.brand_package_id = brand_package.id_brand_package " +
            "where id_product = ?";
    private static final String DELETE_BY_ID = "delete from product where id_product = ?";


    public List<Product> findAll() throws DaoException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = PostgresUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL)
        ) {
            while (resultSet.next()) {
                Product product = new Product();
                product.setName(resultSet.getString("name_product"));
                product.setBrandPackage(new BrandPackage(resultSet.getInt("brand_package_id"), resultSet.getString("name_brand_package")));
                product.setPrimeCost(resultSet.getDouble("prime_cost"));
                product.setPrice(resultSet.getDouble("price"));
                product.setId(resultSet.getInt("id_product"));
                products.add(product);
            }
        } catch (SQLException throwables) {
            throw new DaoException("Failed findAll Products");
        }
        return products;
    }

    public Integer create(Product product) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getBrandPackage().getId());
            preparedStatement.setDouble(3, product.getPrimeCost());
            preparedStatement.setDouble(4, product.getPrice());
            return preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException("Failed create Product");
        }
    }

    public Product getById(Integer id) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id_product"));
                product.setName(resultSet.getString("name_product"));
                product.setBrandPackage(new BrandPackage(resultSet.getInt("brand_package_id"), resultSet.getString("name_brand_package")));
                product.setPrimeCost(resultSet.getDouble("prime_cost"));
                product.setPrice(resultSet.getDouble("price"));
                return product;
            }

        } catch (SQLException throwables) {
            throw new DaoException("Failed getById Product");
        }
        return null;
    }

    public void deleteByID(Integer id) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throw new DaoException("Failed deleteByID Product");
        }
    }
}
