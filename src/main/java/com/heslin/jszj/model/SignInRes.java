package com.heslin.jszj.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInRes {
    private ResMessage resMessage;
    private String jwtToken;
}
