package com.tneshcheret.service;


import com.tneshcheret.dao.UserDao;
import com.tneshcheret.entity.User;

public class UserService {

    private UserDao userDao = new UserDao();

    public User getByUserName(String login) {
        try {
            return userDao.findByLogin(login);
        } catch (Exception e) {//TODO Exception
            System.out.println("Failed to find");
            return null;
        }
    }
}
