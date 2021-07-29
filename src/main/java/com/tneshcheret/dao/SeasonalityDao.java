package com.tneshcheret.dao;

import com.tneshcheret.entity.BrandPackage;
import com.tneshcheret.entity.Seasonality;
import com.tneshcheret.utils.ConnectionPool;
import com.tneshcheret.utils.MySpecialContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeasonalityDao {

    private static final String SELECT_ALL = "select id_seasonality, " +
            "month," +
            "brand_package_id," +
            "brand_package.name_brand_package," +
            "coefficient " +
            "from seasonality" +
            " join brand_package on seasonality.brand_package_id = brand_package.id_brand_package";

    private static final String FIND_BY_ID = "select id_seasonality, " +
            "month," +
            "brand_package_id," +
            "brand_package.name_brand_package," +
            "coefficient " +
            "from seasonality" +
            " join brand_package on seasonality.brand_package_id = brand_package.id_brand_package " +
            "where id_seasonality = ?";

    private static final String DELETE_BY_ID = "delete from seasonality where id_seasonality = ?";
    private static final String INSERT_SQL = "insert into seasonality (month, brand_package_id, coefficient) VALUES (?, ?, ?)";

    public List<Seasonality> findAll() throws DaoException {

        ConnectionPool connectionPool = MySpecialContext.get();

        List<Seasonality> seasonalities = new ArrayList<>();

        try (Connection connection = connectionPool.get();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {

            while (resultSet.next()) {
                Seasonality seasonality = new Seasonality();
                seasonality.setId(resultSet.getInt("id_seasonality"));
                seasonality.setMonthNumber(resultSet.getInt("month"));
                seasonality.setBrandPackage(new BrandPackage(resultSet.getInt("brand_package_id"), resultSet.getString("name_brand_package")));
                seasonality.setCoefficient(resultSet.getDouble("coefficient"));
                seasonalities.add(seasonality);
            }
        } catch (SQLException throwables) {
            throw new DaoException("Failed findAll Seasonality");
        }

        return seasonalities;
    }

    public Seasonality getById(Integer id) throws DaoException {

        ConnectionPool connectionPool = MySpecialContext.get();

        try (Connection connection = connectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Seasonality seasonality = new Seasonality();
                seasonality.setId(resultSet.getInt("id_seasonality"));
                seasonality.setMonthNumber(resultSet.getInt("month"));
                seasonality.setBrandPackage(new BrandPackage(resultSet.getInt("brand_package_id"), resultSet.getString("name_brand_package")));
                seasonality.setCoefficient(resultSet.getDouble("coefficient"));

                return seasonality;
            }
        } catch (SQLException throwables) {
            throw new DaoException("Failed getById Seasonality");
        }
        return null;
    }

    public void deleteById(Integer id) throws DaoException {


        ConnectionPool connectionPool = MySpecialContext.get();

        try (Connection connection = connectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throw new DaoException("Failed deleteById Seasonality");
        }
    }

    public Integer create(Seasonality seasonality) throws DaoException {


        ConnectionPool connectionPool = MySpecialContext.get();

        try (Connection connection = connectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, seasonality.getMonthNumber());
            preparedStatement.setInt(2, seasonality.getBrandPackage().getId());
            preparedStatement.setDouble(3, seasonality.getCoefficient());

            return preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException("Failed create Seasonality");
        }
    }
}
