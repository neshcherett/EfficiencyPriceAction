package com.tneshcheret.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPageController implements Controller {

    private final String VIEW_NAME;

    public ShowPageController(String view_name) {
        VIEW_NAME = view_name;
    }

    @Override
    public ControllerResultDto execute(HttpServletRequest request, HttpServletResponse response) {
        return new ControllerResultDto(VIEW_NAME);
    }

}
