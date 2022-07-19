package com.job.payloads;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class JobDTO {

    @NotBlank(message = "job title should not be blank")
    @Size(max = 255,message = "title should be max 255 characters")
    private String jobTitle;
    @NotBlank(message = "job description should not be blank")
    @Size(max = 1000,message = "job description should be max 1000 characters")
    private String jobDescription;
}
