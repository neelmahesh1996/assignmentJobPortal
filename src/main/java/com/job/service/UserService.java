package com.job.service;

import com.job.payloads.SignInDTO;
import com.job.payloads.SignUpDTO;
import com.job.payloads.response.ApiResponse;
import com.job.payloads.response.JwtAuthenticationResponse;

public interface UserService {
    ApiResponse doRegister(SignUpDTO signUpDTO);
    JwtAuthenticationResponse doLogin(SignInDTO signInDTO);
    ApiResponse forgetPassword(String username);

    ApiResponse logoutUser();
}
