package com.tneshcheret.service;

import com.tneshcheret.dao.DaoException;
import com.tneshcheret.dao.ProductDao;
import com.tneshcheret.entity.Product;

import java.util.List;

public class ProductService {
    ProductDao productDao = new ProductDao();

    public ProductService() {
        this.productDao = new ProductDao();
    }

    public List<Product> findAll() throws DaoException {
        List<Product> products = productDao.findAll();
        return products;
    }

    public Product getByID(Integer id) throws ServiceException {
        try {
            return productDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException("Failed getById");
        }
    }

}
