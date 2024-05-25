package com.reins.bookstore.controller;

import com.reins.bookstore.constants.Messages;
import com.reins.bookstore.entity.User;
import com.reins.bookstore.models.ApiResponseBase;
import com.reins.bookstore.models.ChangePasswordRequest;
import com.reins.bookstore.service.UserService;
import com.reins.bookstore.utils.SessionUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User", description = "用户相关 API")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/me")
    @Operation(summary = "获取\"我\"的信息")
    ResponseEntity<User> getMe() {
        Long userId = SessionUtils.getUserId();
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PutMapping("/me/password")
    @Operation(summary = "修改我的密码")
    ResponseEntity<ApiResponseBase> changeMyPassword(@RequestBody ChangePasswordRequest request) {
        Long userId = SessionUtils.getUserId();
        String password = request.getPassword();
        if (password == null || password.isEmpty()) {
            return ResponseEntity.badRequest().body(ApiResponseBase.fail(Messages.PARAMS_SHOULD_NOT_BE_EMPTY));
        }
        return ResponseEntity.ok(userService.changeMyPassword(userId, request.getPassword()));
    }
}
