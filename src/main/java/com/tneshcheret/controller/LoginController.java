package com.tneshcheret.controller;

import com.tneshcheret.entity.User;
import com.tneshcheret.service.ServiceException;
import com.tneshcheret.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController implements Controller {

    private UserService userService = new UserService();

    @Override
    public ControllerResultDto execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        User user = userService.getByUserName(userName);

        if (user.getPassword().equals(password)) {
            request.setAttribute("user", user);

            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
            return new ControllerResultDto("profile");
        } else {
            return new ControllerResultDto("error-403");
        }
    }
}
