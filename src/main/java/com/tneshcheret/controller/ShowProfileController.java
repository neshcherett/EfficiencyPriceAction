package com.tneshcheret.controller;

import com.tneshcheret.entity.User;
import com.tneshcheret.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowProfileController implements Controller {

    private UserService userService;
    @Override
    public ControllerResultDto execute(HttpServletRequest request, HttpServletResponse response) {
        Integer userId =(Integer) request.getSession().getAttribute("userId");
        User user = null;

        request.setAttribute("login", user.getUserName());
        request.setAttribute("password", user.getPassword());

        return new ControllerResultDto("profile");
    }
}
