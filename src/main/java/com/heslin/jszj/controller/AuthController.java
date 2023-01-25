package com.heslin.jszj.controller;


import com.heslin.jszj.entity.User;
import com.heslin.jszj.model.ResMessage;
import com.heslin.jszj.model.SignInRes;
import com.heslin.jszj.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("sign-up")
    public ResponseEntity<ResMessage> signUp(@RequestBody User user){
        return ResponseEntity.ok(userService.signUp(user));
    }

    @PostMapping("sign-in")
    public SignInRes signIn(@RequestBody User user){
        return userService.signIn(user);
    }
}
