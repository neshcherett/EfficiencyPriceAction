package com.tneshcheret.service;

import com.tneshcheret.dao.DaoException;
import com.tneshcheret.dao.RequestActionDao;
import com.tneshcheret.entity.RequestAction;
import com.tneshcheret.entity.User;

public class RequestActionService {

    RequestActionDao requestActionDao = new RequestActionDao();

    public RequestAction createOrUpdate(RequestAction requestAction) throws ServiceException {
        try {
            return requestActionDao.createOrUpdate(requestAction);
        } catch (DaoException e) {
            throw new ServiceException("failed to create or update request action");
        }
    }

    public RequestAction findOrCreateForUser(User user) throws ServiceException {
        try {
            RequestAction requestAction = requestActionDao.getById(user);

            return requestAction == null ? RequestAction.newBuilder().build() : requestAction;
        } catch (DaoException e) {
            throw new ServiceException("failed to find or create request action for user id " + user.getId());
        }
    }
}
