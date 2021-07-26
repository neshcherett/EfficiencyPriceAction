package com.tneshcheret.controller;

import com.tneshcheret.entity.RequestAction;
import com.tneshcheret.entity.User;
import com.tneshcheret.service.RequestActionService;
import com.tneshcheret.service.ServiceException;
import com.tneshcheret.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowRequestActionController implements Controller {

    UserService userService = new UserService();
    RequestActionService requestActionService = new RequestActionService();

    @Override
    public ControllerResultDto execute(HttpServletRequest request, HttpServletResponse response) {
        try {

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            User user = userService.getById(userId);
            RequestAction requestAction = requestActionService.findOrCreateForUser(user);
            request.setAttribute("requestAction", requestAction);

            return new ControllerResultDto("requestAction");
        } catch (ServiceException e) {
            return new ControllerResultDto("error-500");
        }

    }
}
