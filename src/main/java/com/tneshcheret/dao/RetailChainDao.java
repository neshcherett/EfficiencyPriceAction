package com.tneshcheret.dao;

import com.tneshcheret.entity.Region;
import com.tneshcheret.entity.RetailChain;
import com.tneshcheret.utils.ConnectionPool;
import com.tneshcheret.utils.MySpecialContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RetailChainDao {

    private static final String SELECT_ALL = "select id_retail_Chains, " +
            "name_retail_Chains, " +
            "region_id, " +
            "region.name_region," +
            "commercial_terms " +
            "from retail_chains " +
            "inner join region on retail_Chains.region_id = region.id_region";
    private static final String INSERT_SQL = "insert into retail_chains(" +
            "name_retail_Chains, " +
            "region_id, " +
            "commercial_terms) " +
            "values (?, ?, ?)";
    private static final String SELECT_BY_ID = "select id_retail_Chains," +
            "name_retail_Chains," +
            "region_id," +
            "region.name_region," +
            "commercial_terms " +
            "from retail_chains " +
            "inner join region on retail_Chains.region_id = region.id_region " +
            "where id_retail_Chains = ?";
    private static final String DELETE_BY_ID = "delete from retail_chains where id_retail_Chains = ?";

    ConnectionPool connectionPool = MySpecialContext.get();

    public List<RetailChain> findAll() throws DaoException {

        List<RetailChain> retailChains = new ArrayList<>();

        try (Connection connection = connectionPool.get();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {

            while (resultSet.next()) {
                RetailChain retailChain = new RetailChain();
                retailChain.setCommercialTerms(resultSet.getDouble("commercial_terms"));
                retailChain.setName(resultSet.getString("name_retail_Chains"));
                retailChain.setId(resultSet.getInt("id_retail_Chains"));
                retailChain.setRegion(new Region((resultSet.getInt("region_id")), resultSet.getString("name_region")));
                retailChains.add(retailChain);
            }
        } catch (SQLException throwables) {
            throw new DaoException("Failed findAll RetailChains");
        }

        return retailChains;
    }

    public Integer create(RetailChain retailChain) throws DaoException {

        try (Connection connection = connectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, retailChain.getName());
            preparedStatement.setInt(2, retailChain.getRegion().getId());
            preparedStatement.setDouble(3, retailChain.getCommercialTerms());

            return preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException("Failed create RetailChain");
        }
    }

    public RetailChain getById(Integer id) throws DaoException {

        try (Connection connection = connectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                RetailChain retailChain = new RetailChain();
                retailChain.setId(resultSet.getInt("id_retail_Chains"));
                retailChain.setName(resultSet.getString("name_retail_Chains"));
                retailChain.setRegion(new Region(resultSet.getInt("region_id"), resultSet.getString("name_region")));
                retailChain.setCommercialTerms(resultSet.getDouble("commercial_terms"));

                return retailChain;
            }
        } catch (SQLException throwables) {
            throw new DaoException("Failed getById RetailChain");
        }
        return null;
    }

    public void deleteByID(Integer id) throws DaoException {

        try (Connection connection = connectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throw new DaoException("Failed deleteByID RetailChain");
        }
    }
}
