package com.reins.bookstore.service;

import com.reins.bookstore.models.LoginRequest;

public interface LoginService {
    boolean login(LoginRequest request);
}
