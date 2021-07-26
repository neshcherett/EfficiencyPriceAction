package com.tneshcheret.dao;

import com.tneshcheret.entity.BrandPackage;
import com.tneshcheret.entity.Logistic;
import com.tneshcheret.entity.Region;
import com.tneshcheret.utils.PostgresUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogisticDao {

    private static final String SELECT_ALL = "select id_logistic, " +
            "brand_package_id, " +
            "brand_package.name_brand_package, " +
            "region_id, " +
            "region.name_region, " +
            "delivery_cost " +
            "from logistic " +
            "inner join brand_package on logistic.brand_package_id = brand_package.id_brand_package " +
            "inner join region on logistic.region_id = region.id_region";

    private static final String SELECT_BY_ID = "select id_logistic, " +
            "brand_package_id, " +
            "brand_package.name_brand_package, " +
            "region_id, " +
            "region.name_region, " +
            "delivery_cost " +
            "from logistic " +
            "inner join brand_package on logistic.brand_package_id = brand_package.id_brand_package " +
            "inner join region on logistic.region_id = region.id_region " +
            "where id_logistic = ?";

    private static final String INSERT_SQL = "insert into logistic (region_id, " +
            "brand_package_id, " +
            "delivery_cost) " +
            "values (?, ?, ?)";

    private static final String DELETE_BY_ID = "delete from logistic where id_logistic = ?";

    public List<Logistic> findAll() throws DaoException {
        List<Logistic> logistics = new ArrayList<>();
        try (Connection connection = PostgresUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {

            while (resultSet.next()) {
                Logistic logistic = new Logistic();
                logistic.setBrandPackage(new BrandPackage(resultSet.getInt("brand_package_id"), resultSet.getString("name_brand_package")));
                logistic.setRegion(new Region(resultSet.getInt("region_id"), resultSet.getString("name_region")));
                logistic.setId(resultSet.getInt("id_logistic"));
                logistic.setDeliveryCost(resultSet.getDouble("delivery_cost"));
                logistics.add(logistic);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new DaoException("Failed findAll Logistics");
        }
        return logistics;
    }

    public Logistic getById(Integer id) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Logistic logistic = new Logistic();
                logistic.setId(resultSet.getInt("id_logistic"));
                logistic.setRegion(new Region(resultSet.getInt("region_id"), resultSet.getString("name_region")));
                logistic.setBrandPackage(new BrandPackage(resultSet.getInt("brand_package_id"), resultSet.getString("name_brand_package")));
                logistic.setDeliveryCost(resultSet.getDouble("delivery_cost"));

                return logistic;
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new DaoException("Failed getById Logistic");
        }
        return null;
    }

    public Integer create(Logistic logistic) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, logistic.getRegion().getId());
            preparedStatement.setInt(2, logistic.getBrandPackage().getId());
            preparedStatement.setDouble(3, logistic.getDeliveryCost());

            return preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new DaoException("Failed create Logistic");
        }
    }

    public void deleteById(Integer id) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException throwables) {
            throw new DaoException("Failed deleteById Logistic");
        }
    }
}
