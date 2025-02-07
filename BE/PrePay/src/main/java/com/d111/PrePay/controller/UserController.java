package com.d111.PrePay.controller;

import com.d111.PrePay.dto.request.UserCreateReq;
import com.d111.PrePay.dto.request.UserLoginReq;
import com.d111.PrePay.dto.respond.UserLoginRes;
import com.d111.PrePay.service.UserService;
import com.d111.PrePay.dto.request.UserSignUpReq;
import com.d111.PrePay.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody UserSignUpReq userSignUpReq) {
        Map<String, String> response = new HashMap<>();
        response.put("message", userService.userSignUp(userSignUpReq));
        return ResponseEntity.ok(response);
    }
}


//    @PostMapping("/login")
//    public ResponseEntity<UserLoginRes> login(@RequestBody UserLoginReq request) {
//        return ResponseEntity.ok(userService.login(request));
//    }
//}
