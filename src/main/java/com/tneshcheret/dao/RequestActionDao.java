package com.tneshcheret.dao;

import com.tneshcheret.entity.*;
import com.tneshcheret.utils.ConnectionPool;
import com.tneshcheret.utils.MySpecialContext;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

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

    private static final String SELECT_BY_ID_USER = "select id_request_action," +
            "retail_chain_id, " +
            "product_id, " +
            "start_date, " +
            "end_date, " +
            "discount, " +
            "method_discount, " +
            "action_cost, " +
            "request_action.id_user " +
            "from request_action " +
            "inner join product on request_action.product_id = product.id_product " +
            "inner join retail_chains on request_action.retail_chain_id = retail_chains.id_retail_chains " +
            "inner join region on retail_chains.region_id = region.id_region " +
            "inner join \"user\" on request_action.id_user = \"user\".id_user " +
            "where request_action.id_user = ?";

    private static final String INSERT_SQL = "insert into request_action( retail_chain_id, " +
            "product_id, " +
            "start_date, " +
            "end_date, " +
            "discount, " +
            "method_discount, " +
            "action_cost, " +
            "id_user) " +
            "values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String DELETE_BY_ID = "delete from request_action where id_request_action = ?";

    ConnectionPool connectionPool = MySpecialContext.get();

    public RequestAction getById(User user) throws DaoException {

        try (Connection connection = connectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_USER)) {

            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            ArrayList<Product> products = new ArrayList<>();

            while (resultSet.next()) {

                int requestActionId = resultSet.getInt("id_request_action");
                RetailChain retailChain = new RetailChainDao().getById(resultSet.getInt("retail_chain_id"));
                LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
                LocalDate endDate = resultSet.getDate("end_date").toLocalDate();
                double discount = resultSet.getDouble("discount");
                MethodGrantingDiscount methodGrantingDiscount = MethodGrantingDiscount.valueOf(resultSet.getString("method_discount"));
                int cost = resultSet.getInt("action_cost");
                Product product = new ProductDao().getById(resultSet.getInt("product_id"));

                products.add(product);
            }

            return RequestAction.newBuilder()
                    .setUser(user)
                    .setProduct(products)
                    .build();
        } catch (SQLException throwables) {
            throw new DaoException("Failed getById RequestAction");
        }
    }

    public RequestAction createOrUpdate(RequestAction requestAction) throws DaoException {

        deleteRequestAction(requestAction);

        try {

            for (Product product : requestAction.getProducts()) {
                try (Connection connection = connectionPool.get();
                     PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {

                    preparedStatement.setInt(1, 1);//TODO убрать заглушку
                    preparedStatement.setInt(2, product.getId());
                    preparedStatement.setDate(3, Date.valueOf(LocalDate.of(2021, 8, 25)));//TODO убрать заглушку
                    preparedStatement.setDate(4, Date.valueOf(LocalDate.of(2021, 8, 25)));//TODO убрать заглушку
                    preparedStatement.setDouble(5, 0.15);//TODO убрать заглушку
                    preparedStatement.setString(6, String.valueOf(MethodGrantingDiscount.ONINVOICE));//TODO убрать заглушку
                    preparedStatement.setInt(7, requestAction.getCostAction());//TODO убрать заглушку
                    preparedStatement.setInt(8, requestAction.getUser().getId());

                    preparedStatement.execute();
                }
            }

            return requestAction;
        } catch (SQLException throwables) {
            throw new DaoException("Failed create RequestAction");
        }
    }

    public void deleteRequestAction(RequestAction requestAction) throws DaoException {

        try (Connection connection = connectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, requestAction.getUser().getId());
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throw new DaoException("Failed deleteById RequestAction");
        }
    }
}
