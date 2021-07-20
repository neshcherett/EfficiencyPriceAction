package com.tneshcheret.controller;

import com.tneshcheret.dao.DaoException;
import com.tneshcheret.entity.Product;
import com.tneshcheret.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowAllProductController implements Controller {

    private ProductService productService = new ProductService();

    @Override
    public ControllerResultDto execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Product> products = productService.findAll();
            request.setAttribute("products", products);
            return new ControllerResultDto("products");
        } catch (DaoException e) {
            return null;
        }
    }
}
