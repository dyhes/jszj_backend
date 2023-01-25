package com.heslin.jszj.utils;

import com.heslin.jszj.entity.User;
import com.heslin.jszj.service.JwtService;
import com.heslin.jszj.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Utils {
    static private final JwtService jwtService = new JwtService();

    static public Long extractUserid(String authorization) {
        return jwtService.extractUserid(authorization.substring(7));
    }
}
