package com.tneshcheret.service;


import com.tneshcheret.dao.UserDao;
import com.tneshcheret.entity.User;

public class UserService {

    private UserDao userDao = new UserDao();

    public User findByUserName(String login) throws ServiceException {
        try {
            return userDao.findByLogin(login);
        } catch (Exception e) {
            throw new ServiceException("Failed to find user by login");
        }
    }
}
