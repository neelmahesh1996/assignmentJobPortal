package com.job.payloads;

import com.job.utils.AppConstants;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class PageDTO {
    @Min(value =AppConstants.CURRENT_PAGE)
    private int pageNo;
    @Min(value =1)
    @Max(value = AppConstants.PAGE_SIZE)
    private int pageSize;
}
