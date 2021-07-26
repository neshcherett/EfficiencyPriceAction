package com.tneshcheret.service;


import com.tneshcheret.dao.UserDao;
import com.tneshcheret.entity.User;

public class UserService {

    private UserDao userDao = new UserDao();

    public User getByUserName(String login) throws ServiceException {
        try {
            return userDao.getByLogin(login);
        } catch (Exception e) {
            throw new ServiceException("Failed to find user by login");
        }
    }

    public User getById(Integer id) throws ServiceException {
        try {
            return userDao.getById(id);
        } catch (Exception e) {
            throw new ServiceException("Failed to find user by id");
        }
    }

}
