package com.job.controller;

import com.job.payloads.SignInDTO;
import com.job.payloads.SignUpDTO;
import com.job.payloads.response.ApiResponse;
import com.job.payloads.response.JwtAuthenticationResponse;
import com.job.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpDTO signUpDTO, BindingResult result)
    {
        if(result.hasErrors())
        {
            return ResponseEntity.badRequest().body(new ApiResponse(Boolean.FALSE,result.getAllErrors().get(0).getDefaultMessage()));
        }
        //do register
        return ResponseEntity.ok().body(userService.doRegister(signUpDTO));
    }
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> singIn(@Valid @RequestBody SignInDTO signInDTO, BindingResult result)
    {

        if(result.hasErrors())
        {
            return ResponseEntity.badRequest().body(new JwtAuthenticationResponse(Boolean.FALSE,result.getAllErrors().get(0).getDefaultMessage(),null));
        }

        //do login

        return ResponseEntity.ok().body(userService.doLogin(signInDTO));
    }

    @PostMapping("/forgetPassword/{username}")
    public ResponseEntity<ApiResponse> forgetPasswordRequest(@PathVariable("username") String username)
    {

        //do forget password

        return null;
    }
    @GetMapping("/logout")
    public ResponseEntity<ApiResponse> logoutUser()
    {
        return ResponseEntity.ok().body(userService.logoutUser());
    }

}
