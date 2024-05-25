package com.reins.bookstore.models;

import lombok.Data;

@Data
public class LoginRequest {
    String username;
    String password;
}
