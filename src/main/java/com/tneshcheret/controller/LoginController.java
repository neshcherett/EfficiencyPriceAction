package com.tneshcheret.controller;

import com.tneshcheret.entity.User;
import com.tneshcheret.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController implements Controller {

    private UserService userService = new UserService();

    @Override
    public ControllerResultDto execute(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("login");
        String password = request.getParameter("password");
        User user = userService.getByUserName(userName);

        if (user.getPassword().equals(password)) {
            request.setAttribute("user", user);
            return new ControllerResultDto("profile");
        } else {
            return new ControllerResultDto("error-403");
        }
    }
}
