package com.tneshcheret.servlet;

import com.tneshcheret.controller.Controller;
import com.tneshcheret.controller.ControllerFactory;
import com.tneshcheret.controller.ControllerResultDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/efficiency-price-action/*")
public class DispatcherServlet extends HttpServlet {

    private ControllerFactory controllerFactory;

    @Override
    public void init() throws ServletException {
        controllerFactory = new ControllerFactory();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Controller controller = controllerFactory.getController(request);
        try {
            ControllerResultDto result = controller.execute(request, response);
            doForwardOrRedirect(result, request, response);
        } catch (Exception e) {
            throw new ServletException("Cannot execute action", e);
        }
    }

    private void doForwardOrRedirect(ControllerResultDto result, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = String.format("/WEB-INF/jsp/" + result.getView() + ".jsp");
        request.getRequestDispatcher(path).forward(request, response);
    }
}
