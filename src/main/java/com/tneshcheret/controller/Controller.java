package com.tneshcheret.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
    ControllerResultDto execute(HttpServletRequest request, HttpServletResponse response);

}
