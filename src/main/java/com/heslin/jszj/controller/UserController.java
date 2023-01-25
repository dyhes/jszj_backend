package com.heslin.jszj.controller;

import com.heslin.jszj.model.ResMessage;
import com.heslin.jszj.model.SignInRes;
import com.heslin.jszj.model.UserInfo;
import com.heslin.jszj.service.UserService;
import com.heslin.jszj.utils.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    @GetMapping("info")
    public ResponseEntity<UserInfo> getUserInfo(@RequestHeader("Authorization") String authorization) {
        Long userid = Utils.extractUserid(authorization);
        return ResponseEntity.ok(userService.userInfo(userid));
    }

    @PostMapping("update-username")
    public ResponseEntity<ResMessage> updateUsername(@RequestHeader("Authorization") String authorization, @RequestParam String username){
        Long userid = Utils.extractUserid(authorization);
        return ResponseEntity.ok(userService.updateUsername(userid,username));
    }
}
