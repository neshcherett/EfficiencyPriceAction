package com.tneshcheret.controller;

import com.tneshcheret.entity.Product;
import com.tneshcheret.entity.RequestAction;
import com.tneshcheret.entity.User;
import com.tneshcheret.service.ProductService;
import com.tneshcheret.service.RequestActionService;
import com.tneshcheret.service.ServiceException;
import com.tneshcheret.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddToRequestActionController implements Controller {

    private final UserService userService = new UserService();
    private final ProductService productService = new ProductService();
    private final RequestActionService requestActionService = new RequestActionService();

    @Override
    public ControllerResultDto execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        try {
            String productId = request.getParameter("productId");
            Product product = productService.getByID(Integer.parseInt(productId));

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            request.getSession().setAttribute("userId", null);
            User user = userService.getById(userId);

            RequestAction requestAction = requestActionService.findOrCreateForUser(user);
            requestAction.getProducts().add(product);
            requestActionService.createOrUpdate(requestAction);
            return new ControllerResultDto("products");
        } catch (ServiceException e) {
            return new ControllerResultDto("error-500");
        }
    }
}
