package com.example.JWT.service;

import com.example.JWT.model.AuthModel;
import com.example.JWT.repository.AuthRepo;
import com.example.JWT.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthRepo authRepo;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthRepo getAuthRepo() {
        return authRepo;
    }

    public void setAuthRepo(AuthRepo authRepo) {
        this.authRepo = authRepo;
    }

    public JwtUtil getJwtUtil() {
        return jwtUtil;
    }

    public void setJwtUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public AuthModel signIn(AuthModel authUserSigninModel){
        System.out.println("Output :" + authUserSigninModel.getUserID() + authUserSigninModel.getPassword());
        return authRepo.save(authUserSigninModel);
    }

    public String logIn(AuthModel authUserLoginModel){
        AuthModel storedUser = authRepo.findByEmail(authUserLoginModel.getEmail());
        if(storedUser!= null && storedUser.getPassword().equals(authUserLoginModel.getPassword()) && storedUser.getRole().equals(authUserLoginModel.getRole())) {
            System.out.println("Stored User :" + storedUser.getPassword());
            return String.valueOf(jwtUtil.generateToken(authUserLoginModel));
        }
        if(storedUser == null)
            return "No such User exists";
        if(!storedUser.getPassword().equals(authUserLoginModel.getPassword()))
            return "Password is incorrect";
        if(!storedUser.getRole().equals(authUserLoginModel.getRole()))
            return "role doesn't match";
        return "Error";
    }
}
