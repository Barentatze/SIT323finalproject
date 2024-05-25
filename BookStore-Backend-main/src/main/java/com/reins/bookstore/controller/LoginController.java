package com.reins.bookstore.controller;

import com.reins.bookstore.constants.Messages;
import com.reins.bookstore.models.ApiResponseBase;
import com.reins.bookstore.models.LoginRequest;
import com.reins.bookstore.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@Tag(name = "Login", description = "用户登录相关 API")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping
    @Operation(summary = "登录")
    public ResponseEntity<ApiResponseBase> login(@RequestBody LoginRequest request) {
        if (loginService.login(request)) {
            return ResponseEntity.ok(ApiResponseBase.succeed(Messages.LOGIN_SUCCESS, null));
        }
        return ResponseEntity.ok(ApiResponseBase.fail(Messages.PASSWORD_ERROR, null));
    }
}
