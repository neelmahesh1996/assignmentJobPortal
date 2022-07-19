package com.job.payloads;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignInDTO {

    @NotBlank(message = "email should not be blank")
    @Email(message = "please enter valid Email")
    @Size(max = 40)
    private String username;

    @NotBlank(message = "password should not be blank")
    @Size(min = 8,message = "password should minimum 8 character")
    private String password;


}
