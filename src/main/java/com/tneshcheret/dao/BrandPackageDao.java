package com.tneshcheret.dao;

import com.tneshcheret.entity.BrandPackage;
import com.tneshcheret.utils.PostgresUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandPackageDao {

    private static final String SELECT_ALL = "select * from brand_package";
    private static final String FIND_BY_ID = "select id_brand_package, name_brand_package from brand_package where id_brand_package = ?";
    private static final String DELETE_BY_ID = "delete from brand_package where id_brand_package = ?";
    private static final String INSERT_SQL = "insert into brand_package (name_brand_package) values ( ?)";

    public List<BrandPackage> findAll() throws DaoException {
        List<BrandPackage> brandPackages = new ArrayList<>();
        try (Connection connection = PostgresUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {
            while (resultSet.next()) {
                BrandPackage brandPackage = new BrandPackage();
                brandPackage.setId(resultSet.getInt("id_brand_package"));
                brandPackage.setName(resultSet.getString("name_brand_package"));
                brandPackages.add(brandPackage);
            }
        } catch (SQLException throwables) {
            throw new DaoException("Failed findAll BrandPackages");
        }
        return brandPackages;
    }

    public BrandPackage getById(Integer id) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                BrandPackage brandPackage = new BrandPackage();
                brandPackage.setName(resultSet.getString("name_brand_package"));
                brandPackage.setId(resultSet.getInt("id_brand_package"));
                return brandPackage;
            }
        } catch (SQLException throwables) {
            throw new DaoException("Failed getById BrandPackage");
        }
        return null;
    }

    public void deleteById(Integer id) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throw new DaoException("Failed deleteById BrandPackage");
        }
    }

    public Integer create(BrandPackage brandPackage) throws DaoException{
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, brandPackage.getName());
            return preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException("Failed create BrandPackage");
        }
    }
}
