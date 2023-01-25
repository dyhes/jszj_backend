package com.heslin.jszj.service;


import com.heslin.jszj.entity.User;
import com.heslin.jszj.model.ResMessage;
import com.heslin.jszj.model.SignInRes;
import com.heslin.jszj.model.UserInfo;
import com.heslin.jszj.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public ResMessage signUp(User user){
        if(userRepository.findUserByName(user.getName())!=null){
            return new ResMessage(false, "user name duplicated");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return new ResMessage(true, "successfully signed up");
    }

    public SignInRes signIn(User user) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword()));
        var real_user = userRepository.findUserByName(user.getName());
        var jwtToken = jwtService.generateToken(real_user);
        return new SignInRes(new ResMessage(true,"successfully signed in"),jwtToken);
    }

    public UserInfo userInfo(Long userid) {
        User user = userRepository.findById(userid).get();
        return new UserInfo(user.getName(),user.getAvatarUrl());
    }


    public ResMessage updateUsername(Long userid, String username) {
        User user = userRepository.findById(userid).get();
        if(userRepository.findUserByName(username)!=null){
            return new ResMessage(false, "username already exist");
        }else{
            user.setName(username);
            userRepository.save(user);
            return new ResMessage(true,"successfully updated username");
        }
    }

    public User getUserById(Long userid) {
        return userRepository.findById(userid).get();
    }

    public User extractUser(String authorization) {
        return getUserById(jwtService.extractUserid(authorization.substring(7)));
    }
}
