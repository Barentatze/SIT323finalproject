package com.reins.bookstore.service.impl;

import com.reins.bookstore.dao.UserDAO;
import com.reins.bookstore.entity.UserAuth;
import com.reins.bookstore.models.LoginRequest;
import com.reins.bookstore.service.LoginService;
import com.reins.bookstore.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserDAO userDAO;

    @Override
    public boolean login(LoginRequest request) {
        UserAuth auth = userDAO.getAuth(request.getUsername());
        boolean succeed = auth != null && Objects.equals(auth.getPassword(), request.getPassword());
        if (succeed) {
            SessionUtils.setSession(auth);
        }
        return succeed;
    }
}
