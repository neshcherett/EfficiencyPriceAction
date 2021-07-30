package com.tneshcheret.dao;

import com.tneshcheret.entity.Region;
import com.tneshcheret.utils.ConnectionPool;
import com.tneshcheret.utils.MySpecialContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegionDao {

    private static final String SELECT_ALL = "select * from region";
    private static final String FIND_BY_ID = "select id_region, name_region from region where id_region = ?";
    private static final String DELETE_BY_ID = "delete from region where id_region = ?";
    private static final String INSERT_SQL = "insert into region (name_region) values ( ?)";

    ConnectionPool connectionPool = MySpecialContext.get();

    public List<Region> findAll() throws DaoException {

        List<Region> regions = new ArrayList<>();

        try (Connection connection = connectionPool.get();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {

            while (resultSet.next()) {
                Region region = new Region();
                region.setId(resultSet.getInt("id_region"));
                region.setName(resultSet.getString("name_region"));
                regions.add(region);
            }
        } catch (SQLException throwables) {
            throw new DaoException("Failed findAll Regions");
        }

        return regions;
    }

    public Region getById(Integer id) throws DaoException {

        try (Connection connection = connectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Region region = new Region();
                region.setId(resultSet.getInt("id_region"));
                region.setName(resultSet.getString("name_region"));

                return region;
            }
        } catch (SQLException throwables) {
            throw new DaoException("Failed getById Region");
        }
        return null;
    }

    public void deleteById(Integer id) throws DaoException {

        try (Connection connection = connectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throw new DaoException("Failed deleteById Region");
        }
    }

    public Integer create(Region region) throws DaoException {

        try (Connection connection = connectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, region.getName());
            return preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException("Failed create Region");
        }
    }
}
