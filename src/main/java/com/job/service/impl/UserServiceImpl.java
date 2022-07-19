package com.job.service.impl;

import com.job.modal.UserEntity;
import com.job.payloads.SignInDTO;
import com.job.payloads.SignUpDTO;
import com.job.payloads.response.ApiResponse;
import com.job.payloads.response.JwtAuthenticationResponse;
import com.job.repository.UserRepo;
import com.job.security.JwtTokenProvider;
import com.job.service.UserService;
import com.job.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Override
    public ApiResponse doRegister(SignUpDTO signUpDTO) {

        try {
            String regexPassword = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$).{8,20}$";
            Pattern pattern = Pattern.compile(regexPassword);
            Matcher matcher = pattern.matcher(signUpDTO.getPassword());

            if (!matcher.matches()) {
                return new ApiResponse(Boolean.FALSE, "password should contain 1 capital,1 numeric,1 uppercase character and length minimum 8");
            }

            if(userRepo.existsByUsername(signUpDTO.getUsername()))
            {
                return new ApiResponse(Boolean.FALSE, "username already exist");
            }

            UserEntity user = new UserEntity();
            user.setUsername(signUpDTO.getUsername());
            //use password encoder
            user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
            System.out.println("ok");
            if (signUpDTO.getIsRecruiter()) {
                user.setRole(AppConstants.ROLE_RECRUITER);
            } else {
                user.setRole(AppConstants.ROLE_CANDIDATE);
            }
            System.out.println("ok");
            userRepo.save(user);
            return new ApiResponse(Boolean.TRUE,"user register successfully");
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            return new ApiResponse(Boolean.FALSE,"something wrong");
        }

    }

    @Override
    public JwtAuthenticationResponse doLogin(SignInDTO signInDTO) {
        try
        {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signInDTO.getUsername(),signInDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generateToken(authentication);
            return new JwtAuthenticationResponse(Boolean.TRUE,"login successful",jwt);
        }
        catch (Exception ex)
        {
            System.out.println(ex);
            return new JwtAuthenticationResponse(Boolean.FALSE,"something wrong",null);
        }

    }

    @Override
    public ApiResponse forgetPassword(String username) {
        return null;
    }

    @Override
    public ApiResponse logoutUser() {
        try {
            SecurityContextHolder.getContext().setAuthentication(null);
            return new ApiResponse(Boolean.TRUE,"user logout");
        }
        catch (Exception ex)
        {
            return new ApiResponse(Boolean.FALSE,"something wrong");
        }
    }
}
