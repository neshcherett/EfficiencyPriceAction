package com.tneshcheret.dao;

import com.tneshcheret.entity.MethodGrantingDiscount;
import com.tneshcheret.entity.RequestAction;
import com.tneshcheret.utils.PostgresUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequestActionDao {

    private static final String SELECT_ALL = "select id_request_action," +
            "retail_chain_id, " +
            "product_id, " +
            "start_date, " +
            "end_date, " +
            "discount, " +
            "method_discount, " +
            "action_cost " +
            "from request_action " +
            "inner join product on request_action.product_id = product.id_product " +
            "inner join retail_chains on request_action.retail_chain_id = retail_chains.id_retail_chains " +
            "inner join region on retail_chains.region_id = region.id_region";

    private static final String SELECT_BY_ID = "select id_request_action," +
            "retail_chain_id, " +
            "product_id, " +
            "start_date, " +
            "end_date, " +
            "discount, " +
            "method_discount, " +
            "action_cost " +
            "from request_action " +
            "inner join product on request_action.product_id = product.id_product " +
            "inner join retail_chains on request_action.retail_chain_id = retail_chains.id_retail_chains " +
            "inner join region on retail_chains.region_id = region.id_region " +
            "where id_request_action = ?";

    private static final String INSERT_SQL = "insert into request_action( retail_chain_id, " +
            "product_id, " +
            "start_date, " +
            "end_date, " +
            "discount, " +
            "method_discount, " +
            "action_cost) " +
            "values (?, ?, ?, ?, ?, ?, ?)";

    private static final String DELETE_BY_ID = "delete from request_action where id_request_action = ?";

    public List<RequestAction> findAll() throws DaoException {
        List<RequestAction> requestActions = new ArrayList<>();
        try (Connection connection = PostgresUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {
            while (resultSet.next()) {
                RequestAction requestAction = new RequestAction();
                requestAction.setId(resultSet.getInt("id_request_action"));
                requestAction.setRetailChain(new RetailChainDao().getById(resultSet.getInt("retail_chain_id")));
                requestAction.setProduct(new ProductDao().getById(resultSet.getInt("product_id")));
                requestAction.setStartDate(resultSet.getDate("start_date").toLocalDate());
                requestAction.setEndDate(resultSet.getDate("end_date").toLocalDate());
                requestAction.setDiscount(resultSet.getDouble("discount"));
                requestAction.setMethodGrantingDiscount(MethodGrantingDiscount.valueOf(resultSet.getString("method_discount")));
                requestAction.setCostAction(resultSet.getInt("action_cost"));
                requestActions.add(requestAction);
            }
        } catch (SQLException throwables) {
            throw new DaoException("Failed findAll RequestActions");
        }
        return requestActions;
    }

    public RequestAction getById(Integer id) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                RequestAction requestAction = new RequestAction();
                requestAction.setId(resultSet.getInt("id_request_action"));
                requestAction.setRetailChain(new RetailChainDao().getById(resultSet.getInt("retail_chain_id")));
                requestAction.setProduct(new ProductDao().getById(resultSet.getInt("product_id")));
                requestAction.setStartDate(resultSet.getDate("start_date").toLocalDate());
                requestAction.setEndDate(resultSet.getDate("end_date").toLocalDate());
                requestAction.setDiscount(resultSet.getDouble("discount"));
                requestAction.setMethodGrantingDiscount(MethodGrantingDiscount.valueOf(resultSet.getString("method_discount")));
                requestAction.setCostAction(resultSet.getInt("action_cost"));
                return requestAction;
            }
        } catch (SQLException throwables) {
            throw new DaoException("Failed getById RequestAction");
        }
        return null;
    }

    public Integer create(RequestAction requestAction) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, requestAction.getRetailChain().getId());
            preparedStatement.setInt(2, requestAction.getProduct().getId());
            preparedStatement.setDate(3, Date.valueOf(requestAction.getStartDate()));
            preparedStatement.setDate(4, Date.valueOf(requestAction.getEndDate()));
            preparedStatement.setDouble(5, requestAction.getDiscount());
            preparedStatement.setString(6, String.valueOf(requestAction.getMethodGrantingDiscount()));
            preparedStatement.setInt(7, requestAction.getCostAction());
            return preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException("Failed create RequestAction");
        }
    }

    public void deleteById(Integer id) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throw new DaoException("Failed deleteById RequestAction");
        }
    }
}
