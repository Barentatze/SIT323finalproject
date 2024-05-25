package com.reins.bookstore.dao.impl;

import com.reins.bookstore.dao.UserDAO;
import com.reins.bookstore.entity.User;
import com.reins.bookstore.entity.UserAuth;
import com.reins.bookstore.repository.UserAuthRepository;
import com.reins.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAOImpl implements UserDAO {
    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserAuth getAuth(String username) {
        return userAuthRepository.findByUsername(username);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Long getUserBalance(Long id) {
        User user = getById(id);
        if (user == null) {
            return null;
        }
        return user.getBalance();
    }

    @Override
    public void updateUserBalance(Long id, Long balance) {
        User user = getById(id);
        if (user != null) {
            user.setBalance(balance);
            userRepository.save(user);
        }
    }

    @Override
    public void updateUserPassword(Long id, String password) {
        UserAuth userAuth = userAuthRepository.findById(id).orElse(null);
        if (userAuth != null) {
            userAuth.setPassword(password);
            userAuthRepository.save(userAuth);
        }
    }
}
