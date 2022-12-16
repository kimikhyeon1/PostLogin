package com.sparta.board.controller;

import com.sparta.board.dto.LoginRequest;
import com.sparta.board.dto.SignupRequest;
import com.sparta.board.entity.User;
import com.sparta.board.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public User signup(@RequestBody  SignupRequest signupRequest) {
        return userService.signup(signupRequest);
    }

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        userService.login(loginRequest, response);
        return "로그인 하였습니다.";
    }

}
