package com.tneshcheret.dao;

import com.tneshcheret.entity.User;
import com.tneshcheret.entity.UserRole;
import com.tneshcheret.utils.PostgresUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private static final String SELECT_ALL = "select * from \"user\"";
    private static final String FIND_BY_ID = "select * from \"user\" where id_user = ?";
    private static final String FIND_BY_LOGIN = "select * from \"user\" where login = ?";
    private static final String DELETE_BY_ID = "delete from \"user\" where id_user = ?";
    private static final String INSERT_SQL = "insert into \"user\" (login, password, user_role) values (?,?,?)";

    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = PostgresUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id_user"));
                user.setUserName(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setUserRole(UserRole.valueOf((resultSet.getString("user_role"))));
                users.add(user);
            }
        } catch (SQLException throwables) {
            throw new DaoException("Failed findAll Users");
        }
        return users;
    }

    public User getById(Integer id) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id_user"));
                user.setUserName(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setUserRole(UserRole.valueOf((resultSet.getString("user_role"))));
                return user;
            }
        } catch (SQLException throwables) {
            throw new DaoException("Failed getById User");
        }
        return null;
    }

    public User findByLogin(String login) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id_user"));
                user.setUserName(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setUserRole(UserRole.valueOf((resultSet.getString("user_role"))));
                return user;
            }
        } catch (SQLException throwables) {
            throw new DaoException("Failed findByLogin User");
        }
        return null;
    }

    public void deleteById(Integer id) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throw new DaoException("Failed deleteById User");
        }
    }

    public Integer create(User user) throws DaoException {
        try (Connection connection = PostgresUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, String.valueOf(user.getUserRole()));
            return preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throw new DaoException("Failed create User");
        }
    }
}
