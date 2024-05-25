package com.reins.bookstore.service.impl;

import com.reins.bookstore.constants.Messages;
import com.reins.bookstore.dao.UserDAO;
import com.reins.bookstore.entity.User;
import com.reins.bookstore.models.ApiResponseBase;
import com.reins.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

    @Override
    public User getUser(Long userId) {
        return userDAO.getById(userId);
    }

    @Override
    @Transactional
    public ApiResponseBase changeMyPassword(Long userId, String password) {
        userDAO.updateUserPassword(userId, password);
        return ApiResponseBase.succeed(Messages.MODIFY_SUCCEED);
    }
}
