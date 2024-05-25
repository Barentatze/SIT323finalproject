package com.reins.bookstore.dao;

import com.reins.bookstore.entity.User;
import com.reins.bookstore.entity.UserAuth;

public interface UserDAO {
    UserAuth getAuth(String username);

    User getById(Long id);

    Long getUserBalance(Long id);

    void updateUserBalance(Long id, Long balance);

    void updateUserPassword(Long id, String password);
}
