package com.tneshcheret.controller;

import com.tneshcheret.service.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
    ControllerResultDto execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

}
